package com.windy.dubbo.spring.boot.endpoint;

import com.windy.dubbo.spring.boot.DubboProperties;
import com.windy.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.windy.dubbo.spring.boot.listener.ConsumerInvokeStaticsFilter;
import com.windy.dubbo.spring.boot.listener.ConsumerSubscribeListener;
import com.windy.dubbo.spring.boot.listener.ProviderExportListener;
import com.windy.dubbo.spring.boot.listener.ProviderInvokeStaticsFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Component
public class DubboEndpoint extends AbstractEndpoint implements ApplicationContextAware {
    private DubboProperties dubboProperties;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setDubboProperties(DubboProperties dubboProperties) {
        this.dubboProperties = dubboProperties;
    }

    public DubboEndpoint() {
        super("dubbo", false, true);
    }

    @Override
    public Object invoke() {
        Map<String, Object> info = new HashMap<String, Object>();
        Boolean serverMode = false;
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(EnableDubboConfiguration.class);
        if (beanNames != null && beanNames.length > 0) {
            serverMode = true;
        }
        if (serverMode) {
            info.put("server", true);
            info.put("port", dubboProperties.getProtocol().getPort());
        }
        info.put("app", dubboProperties.getApplication().getName());
        info.put("registry", dubboProperties.getRegistry());
        info.put("protocol", dubboProperties.getProtocol());
        //published services
        Map<String, Map<String, Long>> publishedInterfaceList = new HashMap<String, Map<String, Long>>();
        Set<Class> publishedInterfaces = ProviderExportListener.exportedInterfaces;
        for (Class clazz : publishedInterfaces) {
            String interfaceClassCanonicalName = clazz.getCanonicalName();
            if (!interfaceClassCanonicalName.equals("void")) {
                Map<String, Long> methodNames = new HashMap<String, Long>();
                for (Method method : clazz.getMethods()) {
                    methodNames.put(method.getName(), ProviderInvokeStaticsFilter.getValue(clazz, method.getName()));
                }
                publishedInterfaceList.put(interfaceClassCanonicalName, methodNames);
            }
        }
        if (!publishedInterfaceList.isEmpty()) {
            info.put("publishedInterfaces", publishedInterfaceList);
        }
        //subscribed services
        Set<Class> subscribedInterfaces = ConsumerSubscribeListener.subscribedInterfaces;
        if (!subscribedInterfaces.isEmpty()) {
            try {
                Map<String, Map<String, Long>> subscribedInterfaceList = new HashMap<String, Map<String, Long>>();
                for (Class clazz : subscribedInterfaces) {
                    Map<String, Long> methodNames = new HashMap<String, Long>();
                    for (Method method : clazz.getMethods()) {
                        methodNames.put(method.getName(), ConsumerInvokeStaticsFilter.getValue(clazz, method.getName()));
                    }
                    subscribedInterfaceList.put(clazz.getCanonicalName(), methodNames);
                }
                info.put("subscribedInterfaces", subscribedInterfaceList);
            } catch (Exception ignore) {

            }
            info.put("connections", ConsumerSubscribeListener.connections);
        }
        return info;
    }
}
