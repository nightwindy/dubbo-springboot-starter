package com.windy.dubbo.spring.boot;

import com.alibaba.dubbo.config.MethodConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
  private DubboProperties.Application application;
  private DubboProperties.Registry registry;
  private DubboProperties.Protocol protocol;
  private DubboProperties.Provider provider;
  private DubboProperties.Consumer consumer;
  /**
   * dubbo monitor address
   */
  private String monitor;

  public String getMonitor() {
    return monitor;
  }

  public void setMonitor(String monitor) {
    this.monitor = monitor;
  }

  public DubboProperties() {
  }

  public DubboProperties.Registry getRegistry() {
    return this.registry;
  }

  public void setRegistry(DubboProperties.Registry registry) {
    this.registry = registry;
  }

  public DubboProperties.Protocol getProtocol() {
    return this.protocol;
  }

  public void setProtocol(DubboProperties.Protocol protocol) {
    this.protocol = protocol;
  }

  public DubboProperties.Provider getProvider() {
    return this.provider;
  }

  public void setProvider(DubboProperties.Provider provider) {
    this.provider = provider;
  }


  @Override
  public String toString() {
    return "DubboProperties [appname=" + this.application.getName() + ", registry=" + this.registry + ", protocol=" + this.protocol.getName() + ", port=" + this.protocol.getPort() + ", threads=" + this.protocol.getThreads() + ", version=" + this.provider.getVersion() + ", group=" + this.provider.getGroup() + "]";
  }

  public DubboProperties.Application getApplication() {
    return this.application;
  }

  public void setApplication(DubboProperties.Application application) {
    this.application = application;
  }

  public DubboProperties.Consumer getConsumer() {
    return this.consumer;
  }

  public void setConsumer(DubboProperties.Consumer consumer) {
    this.consumer = consumer;
  }

  public static class Protocol {
    private String name = "dubbo";
    private Integer port = 20880;
    private int threads = 200;

    public Protocol() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getPort() {
      return this.port;
    }

    public void setPort(Integer port) {
      this.port = port;
    }

    public int getThreads() {
      return this.threads;
    }

    public void setThreads(int threads) {
      this.threads = threads;
    }
  }

  public static class Consumer {
    private String version = "";
    private Integer timeout = 30000;
    private String group = "";
    private Integer retries;
    private Boolean check;

    public Consumer() {
    }

    public String getVersion() {
      return this.version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public Integer getTimeout() {
      return this.timeout;
    }

    public void setTimeout(Integer timeout) {
      this.timeout = timeout;
    }

    public String getGroup() {
      return this.group;
    }

    public void setGroup(String group) {
      this.group = group;
    }

    public Integer getRetries() {
      return this.retries;
    }

    public void setRetries(Integer retries) {
      this.retries = retries;
    }

    public Boolean getCheck() {
      return this.check;
    }

    public void setCheck(Boolean check) {
      this.check = check;
    }
  }

  public static class Provider {
    private String version = "";
    private Integer timeout = 30000;
    private String group = "";

    public Provider() {
    }

    public String getVersion() {
      return this.version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public Integer getTimeout() {
      return this.timeout;
    }

    public void setTimeout(Integer timeout) {
      this.timeout = timeout;
    }

    public String getGroup() {
      return this.group;
    }

    public void setGroup(String group) {
      this.group = group;
    }
  }

  public static class Registry {
    private String address = "";

    public Registry() {
    }

    public String getAddress() {
      return this.address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
  }

  public static class Application {
    private String name;
    private String owner;
    private String organization;

    public Application() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getOwner() {
      return this.owner;
    }

    public void setOwner(String owner) {
      this.owner = owner;
    }

    public String getOrganization() {
      return this.organization;
    }

    public void setOrganization(String organization) {
      this.organization = organization;
    }
  }
}
