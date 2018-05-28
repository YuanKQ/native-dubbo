/******************************
 * User: yuan
 * Date: 18-5-27 下午8:27
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/
package com.pku.netlab;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.pku.netlab.service.HelloWorldService;
import com.pku.netlab.service.impl.HelloWorldServiceImpl;
import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

public class DubboProviderConfig {
    static{
        HelloWorldService helloWorldService = new HelloWorldServiceImpl();
//        Logger logger = Logger.getLogger(DubboProviderConfig.class);
//          PropertyConfigurator.configure ("/home/yuan/Code/IdeaProjects/NativeDubbo/nativedubboprovider/src/main/resources/log4j.properties");
//          BasicConfigurator.configure();

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hello-world-provider");

        RegistryConfig registryConfig = new RegistryConfig();
          registryConfig.setAddress("redis://127.0.0.1:6379");
          registryConfig.setUsername("requirepass");
          registryConfig.setPassword("p@ssword4requirepass");
//        registryConfig.setAddress("zookeeper://219.223.196.9:2111");
//          registryConfig.setClient("curator");  // 待定

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
//        protocolConfig.setHost("127.0.0.1");
        protocolConfig.setPort(20899);
//        protocolConfig.setThreads(20);


        ServiceConfig<HelloWorldService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(HelloWorldService.class);
        service.setRef(helloWorldService);
//        service.setExecutes(10);  // 服务提供者每服务每方法最大可并行执行请求数, 0表示不限制
//        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
    }
}
