package com.kk.pay.entity;

/**
 * Created by kangxuan
 * on 2018/9/14 22:44.
 *
 * @Description: 订单信息实体类
 */
public class OrderInfoEntity {
    private int keyid;
    private String orderId;
    private CustomerEntity customer;
    private String customerOrderId;
    private int uid;
    private double money;
    private int state;
    private String createDate;
    private String payTime;

    public int getKeyid() {
        return keyid;
    }

    public void setKeyid(int keyid) {
        this.keyid = keyid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "OrderInfoEntity{" +
                "keyid=" + keyid +
                ", orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", customerOrderId='" + customerOrderId + '\'' +
                ", uid=" + uid +
                ", money=" + money +
                ", state=" + state +
                ", createDate='" + createDate + '\'' +
                ", payTime='" + payTime + '\'' +
                '}';
    }
}
