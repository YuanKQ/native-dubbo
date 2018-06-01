import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.pku.netlab.service.CallbackService;
import com.pku.netlab.service.HelloWorldService;
import com.pku.netlab.service.UserService;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

/******************************
 * User: yuan
 * Date: 18-5-22 下午4:07
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/

public class DubboConsumerConfig {
    private HelloWorldService helloWorldService;
    private CallbackService callbackService;
    private UserService userService;
    static Logger logger = Logger.getLogger(DubboConsumerConfig.class);
    public HelloWorldService getHelloWorldService() {
        return helloWorldService;
    }

    public CallbackService getCallbackService() {
        return callbackService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void config() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hello-world-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("redis://127.0.0.1:6379");
        registryConfig.setUsername("requirepass");
        registryConfig.setPassword("p@ssword4requirepass");
        registryConfig.setTimeout(50000);

//        registryConfig.setClient("curator");  // 待定

        ReferenceConfig<HelloWorldService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(HelloWorldService.class);
        referenceConfig.setTimeout(10000);
        this.helloWorldService = referenceConfig.get();

        ReferenceConfig<CallbackService> callbackServiceReferenceConfig = new ReferenceConfig<>();
        callbackServiceReferenceConfig.setApplication(applicationConfig);
        callbackServiceReferenceConfig.setRegistry(registryConfig);
        callbackServiceReferenceConfig.setInterface(CallbackService.class);
        this.callbackService = callbackServiceReferenceConfig.get();

        ReferenceConfig<UserService> userServiceReferenceConfig = new ReferenceConfig<>();
        userServiceReferenceConfig.setApplication(applicationConfig);
        userServiceReferenceConfig.setRegistry(registryConfig);
        userServiceReferenceConfig.setInterface(UserService.class);
        this.userService = userServiceReferenceConfig.get();
    }
}
