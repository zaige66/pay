package com.kk.pay.task;

import com.kk.pay.entity.AddOrderTaskEntity;
import com.kk.pay.service.impl.BasicCustomeService;
import com.kk.pay.util.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kangxuan
 * on 2018/9/25 7:38.
 *
 * @Description:
 */
public class CustomerAddOrderTask implements Runnable{
    private BasicCustomeService basicCustomeService;

    public CustomerAddOrderTask(BasicCustomeService basicCustomeService) {
        this.basicCustomeService = basicCustomeService;
    }

    private static BlockingQueue<AddOrderTaskEntity> addOrderTask = new ArrayBlockingQueue<AddOrderTaskEntity>(1000);

    /**
     * 添加任务
     */
    public static void addTask(AddOrderTaskEntity task){
        try {
            addOrderTask.add(task);
        }catch (Exception e){
            LogUtil.logger.error(String.format("商户添加订单，加入任务队列异常 ：%s",e.getMessage()));
            e.printStackTrace();
        }
    }


    /**
     * 处理任务
     */
    @Override
    public void run() {
        while (true){
            try {
                // 获取任务信息
                AddOrderTaskEntity take = addOrderTask.take();
                // 添加订单信息到数据库
                basicCustomeService.addRecord(take.getEntity(),take.getMoney(),take.getCustomerOrderId(),take.getOrderId());

            } catch (InterruptedException e) {
                LogUtil.logger.error(String.format("商户添加订单，处理任务队列异常 ：%s",e.getMessage()));
                e.printStackTrace();
            }
        }
    }
}
