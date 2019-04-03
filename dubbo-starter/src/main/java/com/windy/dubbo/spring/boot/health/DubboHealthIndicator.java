package com.windy.dubbo.spring.boot.health;

import com.windy.dubbo.spring.boot.endpoint.DubboOperationEndpoint;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.windy.dubbo.spring.boot.DubboConsumerAutoConfiguration;
import com.windy.dubbo.spring.boot.bean.ClassIdBean;
import com.windy.dubbo.spring.boot.listener.ConsumerSubscribeListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
public class DubboHealthIndicator extends AbstractHealthIndicator implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    if (!ConsumerSubscribeListener.subscribedInterfaces.isEmpty()) {
      for (Class clazz : ConsumerSubscribeListener.subscribedInterfaces) {
        EchoService echoService = (EchoService) applicationContext.getBean(clazz);
        echoService.$echo("Hello");
        builder.withDetail(clazz.getCanonicalName(), true);
      }
    }
    if (DubboOperationEndpoint.OFFLINE) {
      builder.down().withDetail("providers", "offline");
    } else {
      builder.up();
    }
  }
}
