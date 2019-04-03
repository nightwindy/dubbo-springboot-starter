package com.windy.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Activate(group = Constants.CONSUMER, order = -110000)
public class ConsumerInvokeStaticsFilter extends StaticsFilter {
    public static Map<String, AtomicLong> statics = new ConcurrentHashMap<>();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        increase(invocation.getClass(), invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
