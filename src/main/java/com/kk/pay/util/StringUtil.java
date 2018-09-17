package com.kk.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author kangxuan
 * @date 2018/9/17 18:59
 * @description
 */
public class StringUtil {
    /**
     * 获取订单号，获取规则：
     *                  先获取当前日期精确到毫米，再随机四位随机数
     * @return
     */
    public static String getOrderId(){
        StringBuilder retVal = new StringBuilder();
        retVal.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        retVal.append(1000 + new Random().nextInt(8999));
        return retVal.toString();
    }
}
