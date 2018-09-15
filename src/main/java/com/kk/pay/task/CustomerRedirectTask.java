package com.kk.pay.task;

import com.kk.pay.entity.RedirectCustomeEntity;

/**
 * Created by kangxuan
 * on 2018/9/15 8:21.
 *
 * @Description:
 */
public class CustomerRedirectTask implements Runnable{
    private RedirectCustomeEntity redirectCustomeEntity;

    public CustomerRedirectTask(RedirectCustomeEntity redirectCustomeEntity) {
        this.redirectCustomeEntity = redirectCustomeEntity;
    }

    @Override
    public void run() {
        // TODO
    }
}
