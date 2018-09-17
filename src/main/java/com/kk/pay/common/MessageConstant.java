package com.kk.pay.common;

/**
 * Created by kangxuan
 * on 2018/9/14 22:53.
 *
 * @author 94426
 * @Description: 信息常量
 */
public class MessageConstant {
    public static final String LOGIN_ERROR = "登录失败";
    public static final String LOGIN_SUCCESS = "登录成功";

    public static final String ERROR_UNKNOWN = "未知错误";

    // 付款
    public static final String ORDER_MISS = "该订单不存在";
    public static final String ORDER_ISPAY = "该订单已支付";
    public static final String ORDER_ISFAIL = "该订单已失效";
    public static final String ORDER_PAYFAIL = "支付失败";
    public static final String ORDER_SUCCESS = "支付成功";

    // 添加订单
    public static final String  PARAM_ERROR = "参数错误";
    public static final String  MONEY_ERROR = "订单金额错误";
    public static final String  CUSTOMER_INFO_ERROR = "商户信息错误";
    public static final String  ADD_ORDER_ERROR = "添加订单失败";
    public static final String  ADD_ORDER_SUCCESS= "添加订单成功";
}
