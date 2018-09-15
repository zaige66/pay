package com.kk.pay.dao;

import com.kk.pay.entity.PayUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by kangxuan
 * on 2018/9/14 22:06.
 *
 * @Description:
 */
@Mapper
@Repository
public interface PayUserDao {
    /**
     * 根据用户名查询用户信息
     */
    @Select("select * from pay_user where username = #{username}")
    PayUserEntity selectUserInfoByUsername(String username);

    /**
     * 更新用户余额
     */
    @Update("update pay_user set money = money - #{money} where uid = #{uid} and (money - #{money}) >= 0")
    int updateUserMoney(PayUserEntity entity);
}
