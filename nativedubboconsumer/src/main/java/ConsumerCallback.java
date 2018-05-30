import com.alibaba.dubbo.rpc.RpcContext;
import com.pku.netlab.service.CallbackListener;
import com.pku.netlab.service.CallbackService;
import com.pku.netlab.service.HelloWorldService;

import java.io.Serializable;

/******************************
 * User: yuan
 * Date: 18-5-28 下午10:28
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/

public class ConsumerCallback implements Serializable{
    public static void main(String[] args) {
        DubboConsumerConfig config = new DubboConsumerConfig();
        config.config();
        System.out.println("@@@Finish config");

        CallbackService callbackService = config.getCallbackService();
//            System.out.println(callbackService.test("consumer"));

        CallbackListener listener = new CallbackListener() {
            @Override
            public void changed(String msg) {
                System.out.println("callback1: " + msg);
            }
        };
        callbackService.addListener("First", listener);

        callbackService.addListener("Second", new CallbackListener() {
            @Override
            public void changed(String msg) {
                System.out.println("callback2: " + msg);
            }
        });
        callbackService.addListener("Third", new CallbackListener() {
            @Override
            public void changed(String msg) {
                System.out.println("callback3: " + msg);
            }
        });
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
