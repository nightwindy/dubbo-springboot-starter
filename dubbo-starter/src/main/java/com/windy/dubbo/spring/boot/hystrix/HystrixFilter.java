package com.windy.dubbo.spring.boot.hystrix;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.netflix.hystrix.HystrixCommand;
import com.windy.dubbo.spring.boot.hystrix.config.SetterFactory;

@Activate(group = Constants.CONSUMER,order = -110000)
public class HystrixFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        String methodName = invocation.getMethodName();
        String interfaceName = invoker.getInterface().getName();
        //获取相关熔断配置
        HystrixCommand.Setter setter = SetterFactory.create(interfaceName, methodName, url);
        //获取降级方法
        String fallback = url.getMethodParameter(methodName, "fallback");

        DubboCommand command = new DubboCommand(setter, invoker, invocation, fallback);
        Result result = command.execute();
        return result;
    }

}
