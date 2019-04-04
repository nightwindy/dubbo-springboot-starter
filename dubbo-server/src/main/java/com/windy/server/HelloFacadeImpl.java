/**  
 * All rights Reserved, Designed By www.maihaoche.com
 * 
 * @Package com.mhc.magotan.server
 * @author: 三帝（sandi@maihaoche.com）
 * @date: 2018-07-12 15:47:11
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved. 
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 */ 
package com.windy.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.windy.api.HelloFacade;

import java.util.concurrent.TimeUnit;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@org.springframework.stereotype.Service("helloFacadeImpl")
public class HelloFacadeImpl implements HelloFacade {
	/**
	 * <p> 测试Hello World </p>
	 *  
	 * @param world 输入字符串
	 * @return APIResult<String>
	 */
	@Override
	public String hello(String world)  {
		return "we r in the same"+world;
	}


	@Override
	public String echoWithTimeOut() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello World";
	}

	@Override
	public String echoWithException() {
		return String.format("%s", 10 / 0);
	}
}
