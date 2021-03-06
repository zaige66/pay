package com.kk.pay.util;


import com.kk.pay.PayApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kangxuan
 * on 2018/9/14 22:58.
 *
 * @Description:
 */
public class LogUtil {
    public static Logger logger = LoggerFactory.getLogger(PayApplication.class);

    public static void print(Object object){
        logger.info(object.toString());
    }

    public static void error(Object object){
        logger.error(object.toString());
    }
}
