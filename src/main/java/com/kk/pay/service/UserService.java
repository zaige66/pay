package com.kk.pay.service;

import com.kk.pay.entity.PayUserEntity;
import com.kk.pay.entity.RetMessage;

/**
 * Created by kangxuan
 * on 2018/9/14 23:48.
 *
 * @Description:
 */
public interface UserService {
    public PayUserEntity login(String username, String password);

    public RetMessage pay(PayUserEntity entity,String orderId);

}
