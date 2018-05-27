import com.pku.netlab.service.HelloWorldService;

import java.sql.Timestamp;

/******************************
 * User: yuan
 * Date: 18-5-22 下午4:07
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/

public class Consumer {
    public static void main(String[] args) {
        DubboConsumerConfig config = new DubboConsumerConfig();
        config.config();
        while (true) {
            HelloWorldService service = config.getHelloWorldService();
            String result = service.sayHello(String.valueOf(System.currentTimeMillis()));
            System.out.println(result);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
