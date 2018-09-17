package com.kk.pay.common;

/**
 * Created by kangxuan
 * on 2018/9/14 22:55.
 *
 * @Description:
 */
public class KeyConstant {
    // session
    public static final String USER = "user";

    // 订单状态
    public static final int PAY_NOTPAY = 0;
    public static final int PAY_ISPAY = 1;
    public static final int PAY_ISSUCCESS = 2;
    public static final int PAY_ISFAIL = 3;

    // 返回状态
    public static final int STATE_200 = 200;


    // 请求
    public static final String ORDERID = "orderId";
    public static final String STATE = "state";
    public static final String MESSAGE = "message";
    // 商户返回字段
    public static final String CUSTOMER_RET_SUCCESS = "SUCCESS";
    public static final String CUSTOMER_RET_ERROR = "ERROR";

    // 日期
    public static final String YEAR_MONTH_DAY_HOUR_MIN_SEC = "yyyy-MM-dd HH:mm:ss";

    // 模拟商户逻辑
    public static final String CUSTOMER_USER = "customer_user";
    public static final String CUSTOMER_USERNAME = "test";
    public static final String CUSTOMER_PASSWORD = "123";
}
