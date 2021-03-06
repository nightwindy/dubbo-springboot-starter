package com.windy.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Activate
public class ConsumerSubscribeListener extends InvokerListenerAdapter {
  public static Set<Class> subscribedInterfaces = new HashSet<>();
  public static Map<String, Set<String>> connections = new HashMap<>();

  @Override
  public void referred(Invoker<?> invoker) throws RpcException {
    Class<?> subscribeInterface = invoker.getInterface();
    subscribedInterfaces.add(subscribeInterface);
    String subscribeInterfaceCanonicalName = subscribeInterface.getCanonicalName();
    if (!connections.containsKey(subscribeInterfaceCanonicalName)) {
      connections.put(subscribeInterfaceCanonicalName, new HashSet<>());
    }
    connections.get(subscribeInterfaceCanonicalName).add(invoker.getUrl().toString());
  }

  @Override
  public void destroyed(Invoker<?> invoker) {
    Class<?> subscribedInterface = invoker.getInterface();
    subscribedInterfaces.remove(subscribedInterface);
    String subscribedInterfaceCanonicalName = subscribedInterface.getCanonicalName();
    if (connections.containsKey(subscribedInterfaceCanonicalName)) {
      connections.get(subscribedInterfaceCanonicalName).remove(invoker.getUrl().toString());
    }
  }
}
