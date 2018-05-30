/******************************
 * User: yuan
 * Date: 18-5-27 下午8:27
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/
package com.pku.netlab;

import com.alibaba.dubbo.config.*;
import com.pku.netlab.service.CallbackService;
import com.pku.netlab.service.HelloWorldService;
import com.pku.netlab.service.impl.CallbackServiceImpl;
import com.pku.netlab.service.impl.HelloWorldServiceImpl;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class DubboProviderConfig {
    static{
        HelloWorldService helloWorldService = new HelloWorldServiceImpl();
        CallbackService callbackService = new CallbackServiceImpl();
        Logger logger = Logger.getLogger(DubboProviderConfig.class);

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hello-world-provider");

        RegistryConfig registryConfig = new RegistryConfig();
          registryConfig.setAddress("redis://127.0.0.1:6379");
          registryConfig.setUsername("requirepass");
          registryConfig.setPassword("p@ssword4requirepass");
//        registryConfig.setAddress("zookeeper://219.223.196.9:2111");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20899);

        // hellworldservice配置
        ServiceConfig<HelloWorldService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(HelloWorldService.class);
        service.setRef(helloWorldService);
//        service.setExecutes(10);  // 服务提供者每服务每方法最大可并行执行请求数, 0表示不限制
//        service.setVersion("1.0.0");
        service.export();

        // callbackservice配置
        List<MethodConfig> methodConfigs = new ArrayList<>();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("addListener");
        List<ArgumentConfig> argumentConfigs = new ArrayList<>();
        ArgumentConfig arg = new ArgumentConfig();
        arg.setIndex(1);
        arg.setCallback(true);
        argumentConfigs.add(arg);
        methodConfig.setArguments(argumentConfigs);
        methodConfigs.add(methodConfig);

        ServiceConfig<CallbackService> callbackConfig = new ServiceConfig<>();
        callbackConfig.setApplication(applicationConfig);
        callbackConfig.setRegistry(registryConfig);
        callbackConfig.setProtocol(protocolConfig);
        callbackConfig.setInterface(CallbackService.class);
        callbackConfig.setRef(callbackService);
        callbackConfig.setMethods(methodConfigs);
        callbackConfig.export();

        logger.info("The configuration of provider has finished.");

        // 暴露及注册服务
    }
}
