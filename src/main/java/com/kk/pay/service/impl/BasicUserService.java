package com.kk.pay.service.impl;

import com.kk.pay.common.KeyConstant;
import com.kk.pay.common.MessageConstant;
import com.kk.pay.dao.OrderDao;
import com.kk.pay.dao.PayUserDao;
import com.kk.pay.entity.OrderInfoEntity;
import com.kk.pay.entity.PayUserEntity;
import com.kk.pay.entity.RedirectCustomeEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.service.UserService;
import com.kk.pay.task.CustomerRedirectTask;
import com.kk.pay.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kangxuan
 * on 2018/9/14 22:12.
 *
 * @Description: 处理用户相关逻辑
 */
@Service
public class BasicUserService implements UserService {
    @Autowired
    private PayUserDao payUserDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BasicUserService basicUserService;

    /**
     * 登录
     * @param username 用户名
     * @param password  密码
     * @return 成功返回用户信息，失败返回空
     */
    @Override
    public PayUserEntity login(String username, String password){
        if (username == null || password == null) {
            return null;
        }

        try {
            PayUserEntity payUserEntity = payUserDao.selectUserInfoByUsername(username);
            if (payUserEntity != null) {
                if (payUserEntity.getPassword().equals(password)){
                    return payUserEntity;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付
     * @param orderId
     * @return
     */
    @Override
    public RetMessage pay(PayUserEntity entity, String orderId) {
        // TODO
        /**
         * 该方法还未处理并发的情况，最简单的处理方式应该是在该方法上加上锁，
         * 但是如果要做成微服务，就得考虑分布式锁
         *
         * 还有一种就是在数据库写存储过程，让这些操作在一次 数据库交互 中完成
         */
        if (null == entity){
            return new RetMessage(0, MessageConstant.ORDER_PAYFAIL);
        }

        LogUtil.logger.info(String.format("pay : orderId==%s , uid==$s ",orderId,entity.getUid()));
        // 查询该订单信息
        OrderInfoEntity orderInfoEntity = orderDao.selectOrderInfoByOrderId(orderId);
        if (orderInfoEntity == null) {
            return new RetMessage(0, MessageConstant.ORDER_MISS);
        }

        int state = orderInfoEntity.getState();
        switch (state){
            case KeyConstant.PAY_ISPAY:
                return new RetMessage(0, MessageConstant.ORDER_ISPAY);
            case KeyConstant.PAY_ISSUCCESS:
                return new RetMessage(0, MessageConstant.ORDER_ISPAY);
            case KeyConstant.PAY_ISFAIL:
                return new RetMessage(0, MessageConstant.ORDER_ISFAIL);
            default:
                break;
        }

        if (state == KeyConstant.PAY_NOTPAY){
            // 处理订单
            try {
                /**
                 * 此处若直接调用handle 方法，handle方法上的事务注解不会生效，需要使用spring的代理类来执行
                 * 具体原因：https://blog.csdn.net/zaige66/article/details/81674845
                 */
                RetMessage handle = basicUserService.handle(entity, orderInfoEntity);
                if (handle.getState() == KeyConstant.STATE_200){
                    // 加入回调商户队列
                    RedirectCustomeEntity redirectCustomeEntity = new RedirectCustomeEntity(orderInfoEntity.getKeyid(),
                            orderInfoEntity.getCustomer().getRedirectUrl(),
                            orderInfoEntity.getCustomerOrderId(), 1);

                    CustomerRedirectTask.addTask(redirectCustomeEntity);
                }
                return handle;
            } catch (Exception e) {
                e.printStackTrace();
                return new RetMessage(0,e.getMessage());
            }
        }

        return new RetMessage(0, MessageConstant.ERROR_UNKNOWN);
    }

    /**
     * 处理 订单,扣款
     * @param orderInfoEntity
     * @return
     */
    @Transactional
    public RetMessage handle(PayUserEntity entity,OrderInfoEntity orderInfoEntity){
        // 减少玩家余额
        entity.setMoney(orderInfoEntity.getMoney());
        int i = payUserDao.updateUserMoney(entity);

        if (i < 1){
            return new RetMessage(0,MessageConstant.ORDER_PAYFAIL);
        }

        // 修改订单状态为已支付
        String payTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int retNum = orderDao.updateOrderToIsPay(orderInfoEntity.getKeyid(), entity.getUid(),payTime);
        if (retNum < 1){
            throw new RuntimeException(MessageConstant.ORDER_PAYFAIL);
        }

        // 支付成功
        return new RetMessage(KeyConstant.STATE_200,MessageConstant.ORDER_SUCCESS);
    }
}
