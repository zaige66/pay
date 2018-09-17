package com.kk.pay.simulate.entity;

/**
 * @author kangxuan
 * @date 2018/9/17 20:11
 * @description
 */
public class Product {
    private int productId;
    private String name;
    private int price;
    /**
     * 库存
     */
    private int store;

    public Product(int productId, String name, int price, int store) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
}
