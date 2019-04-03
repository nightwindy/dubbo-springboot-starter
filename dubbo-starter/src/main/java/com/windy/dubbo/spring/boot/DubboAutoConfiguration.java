package com.windy.dubbo.spring.boot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.windy.dubbo.spring.boot.endpoint.DubboEndpoint;
import com.windy.dubbo.spring.boot.endpoint.DubboOperationEndpoint;
import com.windy.dubbo.spring.boot.health.DubboHealthIndicator;
import com.windy.dubbo.spring.boot.metrics.DubboMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private DubboProperties properties;
  @Autowired
  private Environment env;

  @Bean
  @ConditionalOnMissingBean
  public ApplicationConfig dubboApplicationConfig() {
    ApplicationConfig appConfig = new ApplicationConfig();
    appConfig.setName(this.properties.getApplication().getName());
    appConfig.setOwner(this.properties.getApplication().getOwner());
    appConfig.setOrganization(this.properties.getApplication().getOrganization());
    return appConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public ProtocolConfig dubboProtocolConfig() {
    ProtocolConfig protocolConfig = new ProtocolConfig();
    protocolConfig.setName(this.properties.getProtocol().getName());
    protocolConfig.setPort(this.properties.getProtocol().getPort());
    protocolConfig.setThreads(this.properties.getProtocol().getThreads());
    return protocolConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public RegistryConfig dubboRegistryConfig() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setAddress(this.properties.getRegistry().getAddress());
    return registryConfig;
  }

  /*@Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "dubbo", name = "monitor")
  public MonitorConfig dubboMonitorConfig() {
    MonitorConfig monitorConfig = new MonitorConfig();
    monitorConfig.setAddress(properties.getMonitor());
    return monitorConfig;
  }
*/
  @Bean
  public DubboOperationEndpoint dubboOperationEndpoint() {
    return new DubboOperationEndpoint();
  }

  @Bean
  public DubboHealthIndicator dubboHealthIndicator() {
    return new DubboHealthIndicator();
  }

  @Bean
  public DubboEndpoint dubboEndpoint() {
    return new DubboEndpoint();
  }

  @Bean
  public DubboMetrics dubboConsumerMetrics() {
    return new DubboMetrics();
  }

}
