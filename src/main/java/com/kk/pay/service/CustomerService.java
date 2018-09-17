package com.kk.pay.service;

import com.kk.pay.entity.CustomerEntity;
import com.kk.pay.entity.OrderInfoEntity;
import com.kk.pay.entity.RetMessage;

/**
 * @author kangxuan
 * @date 2018/9/15 15:16
 * @description
 */
public interface CustomerService {
    /**
     * 添加订单
     * @param entity
     * @return
     */
    RetMessage addRecord(CustomerEntity entity,Double money,String customerOrderId);
}
