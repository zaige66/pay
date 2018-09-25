package com.kk.pay;

import com.kk.pay.dao.OrderDao;
import com.kk.pay.service.impl.BasicCustomeService;
import com.kk.pay.task.CustomerAddOrderTask;
import com.kk.pay.task.CustomerRedirectTask;
import com.kk.pay.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 启动任务线程
		CustomerRedirectTask customerRedirectTask = new CustomerRedirectTask(run.getBean(OrderDao.class));
		new Thread(customerRedirectTask).start();
		CustomerAddOrderTask customerAddOrderTask = new CustomerAddOrderTask(run.getBean(BasicCustomeService.class));
		new Thread(customerAddOrderTask).start();


		LogUtil.logger.info("服务启动成功");
	}
}
