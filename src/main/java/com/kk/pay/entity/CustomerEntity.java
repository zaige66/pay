package com.kk.pay.entity;

/**
 * Created by kangxuan
 * on 2018/9/14 22:46.
 *
 * @Description: 商户实体类
 */
public class CustomerEntity {
    private int customerId;
    private String customerName;
    private String privateKey;
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
