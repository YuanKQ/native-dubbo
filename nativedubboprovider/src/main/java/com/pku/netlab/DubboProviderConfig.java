/******************************
 * User: yuan
 * Date: 18-5-27 下午8:27
 * Email: kq_yuan@outlook.com
 *
 * Description: Provider端配置函数
 *
 ******************************/
package com.pku.netlab;

import com.alibaba.dubbo.config.*;
import com.pku.netlab.service.CallbackService;
import com.pku.netlab.service.HelloWorldService;
import com.pku.netlab.service.UserService;
import com.pku.netlab.service.impl.CallbackServiceImpl;
import com.pku.netlab.service.impl.HelloWorldServiceImpl;
import com.pku.netlab.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class DubboProviderConfig {
    static{
        HelloWorldService helloWorldService = new HelloWorldServiceImpl();
        CallbackService callbackService = new CallbackServiceImpl();
        UserService userService = new UserServiceImpl();
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
        service.setTimeout(10000);
//        service.setExecutes(10);  // 服务提供者每服务每方法最大可并行执行请求数, 0表示不限制
//        service.setVersion("1.0.0");
        service.export();

        /************************
         * callbackservice配置
         ************************/
        // 回调函数addListener配置.
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
        // 回调函数sum配置
        MethodConfig sumMethodConfig = new MethodConfig();
        sumMethodConfig.setName("sum");
        List<ArgumentConfig> argumentConfigs1 = new ArrayList<>();
        // 这里的argument可以重复利用上文的arg
//        ArgumentConfig arg1 = new ArgumentConfig();
//        arg1.setIndex(1);
//        arg1.setCallback(true);
//        argumentConfigs1.add(arg1);
        argumentConfigs1.add(arg);
        sumMethodConfig.setArguments(argumentConfigs1);
        methodConfigs.add(sumMethodConfig);

        ServiceConfig<CallbackService> callbackConfig = new ServiceConfig<>();
        /* 提供callback服务实例数量
           >=Consumer调用callbackService.addListener的次数, 否则出现:
           java.lang.IllegalStateException: interface com.pku.netlab.service.CallbackListener `s callback instances num exceed providers limit :2 ,current num: 3. The new callback service will not work !!! you can cancle the callback service which exported before. channel :NettyChannel [channel=[id: 0x006379eb, /219.223.196.9:43346 => /219.223.196.9:20899]]3
           Ref: https://github.com/apache/incubator-dubbo/issues/473
         */
        callbackConfig.setCallbacks(100);
        callbackConfig.setApplication(applicationConfig);
        callbackConfig.setRegistry(registryConfig);
        callbackConfig.setProtocol(protocolConfig);
        callbackConfig.setInterface(CallbackService.class);
        callbackConfig.setRef(callbackService);
        callbackConfig.setMethods(methodConfigs);
        callbackConfig.export();


        ServiceConfig<UserService> userServiceConfig = new ServiceConfig<>();
        userServiceConfig.setApplication(applicationConfig);
        userServiceConfig.setRegistry(registryConfig);
        userServiceConfig.setProtocol(protocolConfig);
        userServiceConfig.setInterface(UserService.class);
        userServiceConfig.setRef(userService);
        userServiceConfig.export();

        logger.info("The configuration of provider has finished.");

        // 暴露及注册服务
    }
}
