package com.kk.pay.service.impl;

import com.kk.pay.common.KeyConstant;
import com.kk.pay.common.MessageConstant;
import com.kk.pay.dao.CustomerDao;
import com.kk.pay.dao.OrderDao;
import com.kk.pay.entity.CustomerEntity;
import com.kk.pay.entity.OrderInfoEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.service.CustomerService;
import com.kk.pay.util.LogUtil;
import com.kk.pay.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangxuan
 * @date 2018/9/17 18:46
 * @description
 */
@Service
public class BasicCustomeService implements CustomerService {
    @Value("${user_pay_url}")
    private String user_pay_url;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderDao orderDao;
    /**
     * 添加订单
     * @param entity
     * @return
     */
    @Override
    public RetMessage addRecord(CustomerEntity entity,Double money,String customerOrderId) {
        if (entity == null || "".equals(customerOrderId)) {
            return new RetMessage(0, MessageConstant.PARAM_ERROR);
        }
        if (money == null || money < 0){
            return new RetMessage(0, MessageConstant.MONEY_ERROR);
        }
        // 获取商户信息
        int customerId = entity.getCustomerId();
        String privateKey = entity.getPrivateKey();
        CustomerEntity customerEntity = customerDao.selectCustomerWithIdAndPrivateKey(customerId, privateKey);
        if (null == customerEntity){
            return new RetMessage(0, MessageConstant.CUSTOMER_INFO_ERROR);
        }
        // 创建订单信息
        OrderInfoEntity order = new OrderInfoEntity();
        order.setMoney(money);
        order.setCreateDate(new SimpleDateFormat(KeyConstant.YEAR_MONTH_DAY_HOUR_MIN_SEC).format(new Date()));
        String orderId = StringUtil.getOrderId();
        order.setOrderId(orderId);
        order.setCustomerOrderId(customerOrderId);
        order.setCustomer(entity);
        LogUtil.print("[customer_add_pay]:" + order.toString());
        /**
         * 这里通过设置商户 id 与 订单号为主键 防止商户重复提交同一订单
         */
        int i = orderDao.insertOrder(order);
        if (i < 1){
            return new RetMessage(0,MessageConstant.ADD_ORDER_ERROR);
        }
        // 生成支付地址
        String url = user_pay_url + "?orderId=" + orderId;
        Map<String,Object> retMap = new HashMap<>(16);
        retMap.put("url",url);
        RetMessage retMessage = new RetMessage(200, MessageConstant.ADD_ORDER_SUCCESS, retMap);
        LogUtil.print("[customer_add_pay ret]:" + retMessage);
        return retMessage;
    }
}
