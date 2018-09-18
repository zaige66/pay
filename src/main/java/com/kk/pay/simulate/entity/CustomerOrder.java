package com.kk.pay.simulate.entity;

/**
 * @author kangxuan
 * @date 2018/9/17 20:13
 * @description
 */
public class CustomerOrder {
    private String createTime;
    private String payTime;
    private Product product;
    private String orderId;
    private int num;
    private int state;

    public CustomerOrder(String createTime, Product productName, String orderId, int num) {
        this.createTime = createTime;
        this.product = productName;
        this.orderId = orderId;
        this.num = num;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
