package com.kk.pay.entity;

/**
 * Created by kangxuan
 * on 2018/9/14 22:10.
 *
 * @Description: 封装返回信息
 */
public class RetMessage {
    /**
     * 状态信息
     */
    private  int state;
    /**
     * 说明信息
     */
    private String message;

    /**
     * 返回信息
     */
    private Object data;

    public RetMessage() {
    }

    public RetMessage(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public RetMessage(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
