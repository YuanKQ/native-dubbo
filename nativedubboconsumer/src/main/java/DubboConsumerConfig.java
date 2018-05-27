import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.pku.netlab.service.HelloWorldService;
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
    static Logger logger = Logger.getLogger(ZooKeeper.class);
    public HelloWorldService getHelloWorldService() {
        return helloWorldService;
    }

    public void config() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hello-world-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("redis://127.0.0.1:6379");
        registryConfig.setUsername("requirepass");
        registryConfig.setPassword("requirepass@redis");
//        registryConfig.setClient("curator");  // 待定

        ReferenceConfig<HelloWorldService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(HelloWorldService.class);

        this.helloWorldService = referenceConfig.get();
    }
}
