package com.kk.pay.controller;

import com.kk.pay.common.KeyConstant;
import com.kk.pay.common.MessageConstant;
import com.kk.pay.entity.PayUserEntity;
import com.kk.pay.entity.RetMessage;
import com.kk.pay.service.UserService;
import com.kk.pay.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangxuan
 * on 2018/9/14 22:48.
 *
 * @Description:
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public RetMessage login(PayUserEntity entity, HttpServletRequest request){
        LogUtil.logger.info(String.format("login : username==%s , password==$s "),entity.getUsername(),entity.getPassword());

        PayUserEntity login = userService.login(entity.getUsername(), entity.getPassword());

        if (login == null) {
            return new RetMessage(0, MessageConstant.LOGIN_ERROR);
        }else {
            request.getSession().setAttribute(KeyConstant.USER,login);
            return new RetMessage(200,MessageConstant.LOGIN_SUCCESS,login);
        }
    }

    @PostMapping("pay")
    @ResponseBody
    public RetMessage pay(){

        return null;
    }
}
