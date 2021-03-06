package com.windy.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Activate(group = Constants.PROVIDER, order = -110000)
public class ProviderInvokeStaticsFilter extends StaticsFilter {

    @SuppressWarnings("Duplicates")
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        increase(invoker.getInterface(), invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
