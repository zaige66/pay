package com.kk.pay.dao;

import com.kk.pay.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by kangxuan
 * on 2018/9/14 23:50.
 *
 * @Description:
 */
@Repository
@Mapper
public interface OrderDao {
    /**
     * 根据订单号查询订单信息
     * @param orderId
     * @return
     */
    @Select("SELECT a.*,b.redirectUrl as `customer.redirectUrl` from order_record a\n" +
            "INNER JOIN customer b\n" +
            "on a.customerId = b.customerId \n" +
            "where orderId = #{orderId}")
    OrderInfoEntity selectOrderInfoByOrderId(String orderId);

    /**
     * 修改订单状态
     * @param keyid 订单唯一id
     * @param newState 新的状态
     * @param oldState 原状态
     * @return
     */
    @Update("update order_record set state = #{newState} where keyid = #{keyid} and state = #{oldState}")
    int updateStateByKeyid(@Param("keyid") int keyid, @Param("newState")int newState, @Param("oldState")int oldState);

    /**
     * 修改订单状态为已支付
     * @param keyid
     * @param uid
     * @return
     */
    @Update("update order_record  set state = 1 ,uid = #{uid},payTime = #{payTime} where keyid = #{keyid} and state = 0")
    int updateOrderToIsPay(@Param("keyid") int keyid,@Param("uid") int uid,@Param("payTime")String payTime);

    /**
     * 插入订单记录
     * @param entity
     * @return
     */
    @Insert("insert into order_record (orderId,customerId,customerOrderId,money,createDate) " +
            "values (#{orderId},#{customer.customerId},#{customerOrderId},#{money},#{createDate})")
    int insertOrder(OrderInfoEntity entity);
}
