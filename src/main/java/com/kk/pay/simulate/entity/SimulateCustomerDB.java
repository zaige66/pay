package com.kk.pay.simulate.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kangxuan
 * @date 2018/9/17 20:06
 * @description 模拟 商户 的数据库
 */

public class SimulateCustomerDB {
    /**
     * 商品列表
     */
    public static List<Product> products = new ArrayList<>();

    /**
     * 订单列表
     */
    public static List<CustomerOrder> orders = new ArrayList<>();

    static {
        products.add(new Product(1,"苹果4s",499,10));
        products.add(new Product(2,"苹果5s",599,10));
        products.add(new Product(3,"苹果6s",699,10));
        products.add(new Product(3,"苹果7s",799,10));
    }


    /**
     * 获取商品信息
     * @param productId
     * @return
     */
    public static Product getProduct(int productId){
        for (Product product : products) {
            if (productId == product.getProductId()){
                return product;
            }
        }
        return null;
    }

    public static void addOrder(CustomerOrder order){
        orders.add(order);
    }

}
