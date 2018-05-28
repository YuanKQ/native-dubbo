import com.alibaba.dubbo.rpc.RpcContext;
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
            /**客户端: 获取provider端的sayHello()
             * String result = service.sayHello(String.valueOf(System.currentTimeMillis()));
             */
            for (int i = 0; i < 5; i ++){
                String parm = String.valueOf(System.currentTimeMillis());
                RpcContext.getContext().setAttachment(parm, "parm"+i);
                String result = service.getParm();
                System.out.println(result);
                System.out.println("==================");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
