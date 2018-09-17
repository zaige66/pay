package com.kk.pay.task;

import com.kk.pay.common.KeyConstant;
import com.kk.pay.dao.OrderDao;
import com.kk.pay.entity.RedirectCustomeEntity;
import com.kk.pay.util.HttpRequestUtil;
import com.kk.pay.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kangxuan
 * on 2018/9/15 8:21.
 *
 * @Description: 处理 用户 支付完订单后回调商家接口
 */
public class CustomerRedirectTask implements Runnable{
    private OrderDao orderDao;
    private static BlockingQueue<RedirectCustomeEntity> tasks = new ArrayBlockingQueue<RedirectCustomeEntity>(10000);

    public CustomerRedirectTask(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * 添加回调任务
     * @param entity
     */
    public static void addTask(RedirectCustomeEntity entity){
        try {
            tasks.add(entity);
        }catch (Exception e){
            LogUtil.logger.error(String.format("添加回调任务出现异常 ：%s",e.getMessage()));
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true){
            try {
                RedirectCustomeEntity take = tasks.take();

                take = null;
                if (take == null){
                    continue;
                }
                // 处理任务
                Map<String,Object> param = new HashMap<>();
                param.put(KeyConstant.ORDERID,take.getCustomerOrderId());
                param.put(KeyConstant.STATE,take.getState());
                param.put(KeyConstant.MESSAGE,take.getMessage() == null ? "" : take.getMessage());
                String s = HttpRequestUtil.sendPost(take.getRedirectUrl(), param);

                if (s != null && KeyConstant.CUSTOMER_RET_SUCCESS.equals(s)){
                    try {
                        // 更新订单状态信息
                        int i = orderDao.updateStateByKeyid(take.getKeyid(), 2, 1);
                        if (i < 1){
                            // 记订单状态更新有问题，要记录 //TODO
                        }
                    }catch (Exception e){
                        LogUtil.logger.error(String.format("回调商户成功，更新订单状态出现异常 ：%s",e.getMessage()));
                        e.printStackTrace();
                    }

                }
            } catch (InterruptedException e) {
                LogUtil.logger.error(String.format("获取回调任务出现异常 ：%s",e.getMessage()));
                e.printStackTrace();
            }catch (Exception e){
                LogUtil.logger.error(String.format("回调商户出现异常 ：%s",e.getMessage()));
                e.printStackTrace();
            }
        }
    }
}
