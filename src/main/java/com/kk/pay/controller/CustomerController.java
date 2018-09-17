package com.kk.pay.controller;

import com.kk.pay.entity.CustomerEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangxuan
 * @date 2018/9/15 15:15
 * @description
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 商户提交订单
     * @param entity 封装商户信息
     * @param money 订单金额
     * @param customerOrderId 订单号
     * @return
     */
    @PostMapping("add/order")
    @ResponseBody
    public RetMessage addOrder(CustomerEntity entity,Double money,String customerOrderId){
        RetMessage retMessage = customerService.addRecord(entity, money, customerOrderId);
        return retMessage;
    }
}
