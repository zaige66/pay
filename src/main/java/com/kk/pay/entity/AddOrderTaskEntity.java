package com.kk.pay.entity;

/**
 * Created by kangxuan
 * on 2018/9/25 7:39.
 *
 * @Description:
 */
public class AddOrderTaskEntity {
    /**
     * 封装商户信息
     */
    private CustomerEntity entity;
    /**
     * 订单金额
     */
    private Double money;
    /**
     * 商户订单号
     */
    private String customerOrderId;
    /**
     * 平台生成订单号
     */
    private String orderId;

    public AddOrderTaskEntity(CustomerEntity entity, Double money, String customerOrderId, String orderId) {
        this.entity = entity;
        this.money = money;
        this.customerOrderId = customerOrderId;
        this.orderId = orderId;
    }

    public CustomerEntity getEntity() {
        return entity;
    }

    public void setEntity(CustomerEntity entity) {
        this.entity = entity;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
