package com.kk.pay.service;

import com.kk.pay.entity.PayUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kangxuan
 * on 2018/9/14 23:07.
 *
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void login() throws Exception {
        PayUserEntity admin = userService.login("admin", "123456");
        System.out.println(admin);
    }

}