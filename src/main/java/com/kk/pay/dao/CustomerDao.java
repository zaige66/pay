package com.kk.pay.dao;

import com.kk.pay.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author kangxuan
 * @date 2018/9/17 18:36
 * @description
 */
@Mapper
@Repository
public interface CustomerDao {
    /**
     * 根据商户id与私有key 查询商户信息
     * @param cusId
     * @param privateKey
     * @return
     */
    @Select("select * from  customer where customerId = #{customerId} and privateKey = #{privateKey}")
    CustomerEntity selectCustomerWithIdAndPrivateKey(@Param("customerId")int cusId, @Param("privateKey") String privateKey);
}
