package com.kk.pay.simulate.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.kk.pay.common.KeyConstant;
import com.kk.pay.common.MessageConstant;
import com.kk.pay.entity.PayUserEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.simulate.entity.CustomerOrder;
import com.kk.pay.simulate.entity.Product;
import com.kk.pay.simulate.entity.SimulateCustomerDB;
import com.kk.pay.util.HttpRequestUtil;
import com.kk.pay.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangxuan
 * @date 2018/9/17 19:50
 * @description 模拟商户应用
 */
@Controller
@RequestMapping("simulate")
public class SimulateCustomerController {

    private static String URL = "http://127.0.0.1:8080/customer/add/order";
    private static String CUSTOMERID = "1001";
    private static String PRIVATE_KEY = "ABCDEFGHIJKLMN";


    /**
     * 模拟 商户端用户登陆，用户名密码直接写死，登陆成功后存入 session 中
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public RetMessage login(String userName, String password, HttpServletRequest request){
        if (KeyConstant.CUSTOMER_USERNAME.equals(userName) && KeyConstant.CUSTOMER_PASSWORD.equals(password)){
            PayUserEntity entity = new PayUserEntity();
            entity.setUsername(userName);
            entity.setPassword(password);
            request.getSession().setAttribute(KeyConstant.CUSTOMER_USER,entity);
            return new RetMessage(200, MessageConstant.LOGIN_SUCCESS);
        }
        return new RetMessage(0, MessageConstant.LOGIN_ERROR);
    }

    @PostMapping("buy")
    @ResponseBody
    public RetMessage buy(int productId,int num){
        Product product = SimulateCustomerDB.getProduct(productId);
        if (product == null){
            return new RetMessage(0,"无该商品");
        }
        if (product.getStore() < num){
            return new RetMessage(0,"库存不足");
        }

        // 创建订单
        String orderId = productId + "_" + StringUtil.getOrderId();
        SimulateCustomerDB.addOrder(new CustomerOrder(new SimpleDateFormat(KeyConstant.YEAR_MONTH_DAY_HOUR_MIN_SEC).format(new Date())
        ,product.getName(),orderId,num));
        // 请求第三方
        String url = URL;
        Map<String,Object> param = new HashMap<>();
        param.put("customerOrderId",orderId);
        param.put("money",product.getPrice());
        param.put("customerId",CUSTOMERID);
        param.put("privateKey",PRIVATE_KEY);
        String s = HttpRequestUtil.sendPost(url, param);
        System.out.println("请求第三方支付结果：" + s);

        return null;
    }
}
