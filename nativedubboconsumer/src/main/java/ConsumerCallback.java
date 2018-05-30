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

        // 每5000ms更新msg
        CallbackListener listener = new CallbackListener() {
            @Override
            public void changed(String msg) {
                System.out.println("callback1: " + msg);
            }

            @Override
            public void getSumResult(int sum) {}
        };
        callbackService.addListener("First", listener);

        callbackService.addListener("Second", new CallbackListener() {
            @Override
            public void getSumResult(int sum) {
            }

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

            @Override
            public void getSumResult(int sum) {
            }
        });

        // 求和任务
        int[] nums = {1,10, 11, 21, 32};
        callbackService.sum(nums, new CallbackListener() {
            @Override
            public void changed(String msg) {
            }

            @Override
            public void getSumResult(int sum) {
                System.out.println("The first sum is : " + sum);
            }
        });
        int[] nums1 = {21,110, 131, 21, 32};
        callbackService.sum(nums1, new CallbackListener() {
            @Override
            public void changed(String msg) {
            }

            @Override
            public void getSumResult(int sum) {
                System.out.println("The second sum is : " + sum);
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
