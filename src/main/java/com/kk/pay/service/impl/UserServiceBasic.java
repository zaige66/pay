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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kangxuan
 * on 2018/9/14 22:12.
 *
 * @Description: 处理用户相关逻辑
 */
@Service
public class UserServiceBasic implements UserService {
    @Autowired
    private PayUserDao payUserDao;
    @Autowired
    private OrderDao orderDao;

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
                RetMessage handle = handle(entity, orderInfoEntity);
                if (handle.getState() == KeyConstant.STATE_200){
                    // 加入回调商户队列
                    RedirectCustomeEntity redirectCustomeEntity = new RedirectCustomeEntity(orderInfoEntity.getKeyid(),
                            orderInfoEntity.getCustomer().getRedirectUrl(),
                            orderInfoEntity.getCustomerOrderId(), 1);


                }
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
    private RetMessage handle(PayUserEntity entity,OrderInfoEntity orderInfoEntity) throws Exception {
        // 减少玩家余额
        entity.setMoney(orderInfoEntity.getMoney());
        int i = payUserDao.updateUserMoney(entity);

        if (i < 1){
            return new RetMessage(0,MessageConstant.ORDER_PAYFAIL);
        }

        // 修改订单状态
        int retNum = orderDao.updatestateByKeyid(orderInfoEntity.getKeyid(), 1, 0);
        if (retNum < 1){
            throw new Exception(MessageConstant.ORDER_PAYFAIL);
        }

        // 支付成功
        return new RetMessage(200,MessageConstant.ORDER_SUCCESS);
    }
}
