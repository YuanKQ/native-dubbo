/******************************
 * User: yuan
 * Date: 18-5-27 下午8:24
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/
package com.pku.netlab.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pku.netlab.service.HelloWorldService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext
                .getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();

//        System.out.println(name + " request service.");
//        return "Hello, " + name;
    }
}