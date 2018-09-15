package com.kk.pay.service;

import com.kk.pay.entity.CustomerEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author kangxuan
 * @date 2018/9/15 15:16
 * @description
 */
public interface CustomerService {
    /**
     * 根据商户id与私有key 查询商户信息
     * @param cusId
     * @param privateKey
     * @return
     */
    @Select("select * from  customer where customerId = #{customerId} and privateKey = #{privateKey}")
    CustomerEntity selectCustomerWithIdandPrivateKey(@Param("customerId")int cusId,@Param("privateKey") String privateKey);

}
