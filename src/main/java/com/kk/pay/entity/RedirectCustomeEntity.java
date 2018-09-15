package com.kk.pay.entity;

/**
 * Created by kangxuan
 * on 2018/9/15 7:49.
 *
 * @Description: 封装回调商户的信息
 */
public class RedirectCustomeEntity {
    /**
     * 订单id，用户回调完后修改订单状态
     */
    private int keyid;

    /**
     * 回调地址
     */
    private String redirectUrl;

    /**
     * 回调订单号
     */
    private String customerOrderId;

    /**
     * 支付状态
     */
    private int state;

    /**
     * 说明信息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RedirectCustomeEntity(int keyid, String redirectUrl, String customerOrderId, int state) {
        this.keyid = keyid;
        this.redirectUrl = redirectUrl;
        this.customerOrderId = customerOrderId;
        this.state = state;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getKeyid() {
        return keyid;
    }

    public void setKeyid(int keyid) {
        this.keyid = keyid;
    }
}
