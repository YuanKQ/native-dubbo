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
import java.util.Map;

public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext
                .getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();

//        System.out.println(name + " request service.");
//        return "Hello, " + name;
    }

    @Override
    public String getParm() {
        Map<String, String> parmMap = RpcContext.getContext().getAttachments();
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + ", request from consumer: " + RpcContext
                .getContext().getRemoteAddress());
        for(Map.Entry<String, String> entry: parmMap.entrySet())
            System.out.println("[" + entry.getKey() + "]: " + entry.getValue());
        System.out.println("==================");
        return "Provider has received param.";
    }

    @Override
    public String asyncSayHello(String name) {
        long start = System.currentTimeMillis();
        System.out.println("Received from: " + name + RpcContext.getContext().getRemoteAddress());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Service cost: " + (System.currentTimeMillis() - start));
        return "Hello. " + name;
    }
}