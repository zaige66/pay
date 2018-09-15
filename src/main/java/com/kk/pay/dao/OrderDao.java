package com.kk.pay.dao;

import com.kk.pay.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("SELECT a.*,b.redirectUrl as customer.redirectUrl from order_record a\n" +
            "INNER JOIN customer b\n" +
            "on a.customerId = b.customerId\n" +
            "where orderId = #{orderId}")
    OrderInfoEntity selectOrderInfoByOrderId(String orderId);

    @Select("update order_record set state = #{state} where keyid = keyid and state = #{oldState}")
    int updatestateByKeyid(int keyid,int newState,int oldState);
}
