package com.kk.pay;

import com.kk.pay.dao.OrderDao;
import com.kk.pay.task.CustomerRedirectTask;
import com.kk.pay.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * @author kangxuan
 */
@SpringBootApplication
public class PayApplication extends WebApplicationObjectSupport {
	@Autowired
	private static PayApplication payApplication;

	public static void main(String[] args) {
		LogUtil.logger.info("服务启动...");
		ConfigurableApplicationContext run = SpringApplication.run(PayApplication.class, args);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 启动线程
		CustomerRedirectTask customerRedirectTask = new CustomerRedirectTask(run.getBean(OrderDao.class));
		new Thread(customerRedirectTask).start();

		LogUtil.logger.info("服务启动成功");
	}
}
