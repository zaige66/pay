package com.kk.pay.service.impl;

import com.kk.pay.common.MessageConstant;
import com.kk.pay.entity.AddOrderTaskEntity;
import com.kk.pay.entity.CustomerEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.service.CustomerService;
import com.kk.pay.task.CustomerAddOrderTask;
import com.kk.pay.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kangxuan
 * on 2018/9/25 7:36.
 *
 * @Description: 异步处理商户提交订单
 */
@Service
public class AsyncCustomerService implements CustomerService {
    @Autowired
    private BasicCustomeService basicCustomeService;
    /**
     * 异步处理商户提交订单，存在一些的问题
     *      缺点：
        *      正常流程这么走是没问题的，但是如果商户重复提交了，任务队列处理时会出现异常，
     *          订单信息插入失败（或者正常执行数据库插入时失败），我们是无法告知商户该订单是添加失败的
     *      优点：
     *         商户提交订单后，很快就能得到支付的url，然后返回给用户去支付，
     *          在用户获取到二维码，然后点击支付，这几步的时间应该是足够处理订单添加的线程去处理该订单了，
     *          这样能加快响应速度。
     *
     * @param entity
     * @param money
     * @param customerOrderId
     * @return
     */
    @Override
    public RetMessage addRecord(CustomerEntity entity, Double money, String customerOrderId) {
        // 创建订单号
        String orderId = StringUtil.getOrderId();

        // 添加任务到到队列
        CustomerAddOrderTask.addTask(new AddOrderTaskEntity(entity,money,customerOrderId, orderId));

        // 返回 信息给商户
        String returnURL = basicCustomeService.getReturnURL(orderId);
        Map<String,Object> retMap = new HashMap<>(16);
        retMap.put("url",returnURL);

        return new RetMessage(200, MessageConstant.ADD_ORDER_SUCCESS,retMap);
    }
}
