package com.windy.dubbo.spring.boot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.windy.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@ConditionalOnBean(annotation = EnableDubboConfiguration.class)
@AutoConfigureAfter(DubboAutoConfiguration.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboProviderAutoConfiguration  {
  @Autowired
  private ApplicationContext applicationContext;
  @Autowired
  private DubboProperties properties;
  @Autowired
  private ApplicationConfig applicationConfig;
  @Autowired
  private ProtocolConfig protocolConfig;
  @Autowired
  private RegistryConfig registryConfig;

  public DubboProviderAutoConfiguration() {
  }

  @PostConstruct
  public void init() throws Exception {
    Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(Service.class);
    Iterator var2 = beans.entrySet().iterator();

    while(var2.hasNext()) {
      Map.Entry<String, Object> entry = (Map.Entry)var2.next();
      this.initProviderBean((String)entry.getKey(), entry.getValue());
    }

  }

  public void initProviderBean(String beanName, Object bean) throws Exception {
    Service service = (Service)this.applicationContext.findAnnotationOnBean(beanName, Service.class);
    ServiceBean<Object> serviceConfig = new ServiceBean(service);
    if (Void.TYPE.equals(service.interfaceClass()) && "".equals(service.interfaceName())) {
      if (bean.getClass().getInterfaces().length <= 0) {
        throw new IllegalStateException("Failed to export remote service class " + bean.getClass().getName() + ", cause: The @Service undefined interfaceClass or interfaceName, and the service class unimplemented any interfaces.");
      }

      serviceConfig.setInterface(bean.getClass().getInterfaces()[0]);
    }

    String version = service.version();
    if (version == null || "".equals(version)) {
      version = this.properties.getProvider().getVersion();
    }

    if (version != null && !"".equals(version)) {
      serviceConfig.setVersion(version);
    }

    String group = service.group();
    if (group == null || "".equals(group)) {
      group = this.properties.getProvider().getGroup();
    }

    if (group != null && !"".equals(group)) {
      serviceConfig.setGroup(group);
    }

    serviceConfig.setApplicationContext(this.applicationContext);
    serviceConfig.setApplication(this.applicationConfig);
    serviceConfig.setProtocol(this.protocolConfig);
    serviceConfig.setRegistry(this.registryConfig);
    serviceConfig.afterPropertiesSet();
    serviceConfig.setRef(bean);
    serviceConfig.export();
  }
}
