import com.alibaba.dubbo.rpc.RpcContext;
import com.pku.netlab.service.HelloWorldService;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConsumerAsync {
    public static void main(String args[]) throws IOException {
        DubboConsumerConfig config = new DubboConsumerConfig();
        config.config();
        HelloWorldService helloWorldService = config.getHelloWorldService();
        long start = System.currentTimeMillis();
        Future<String> f = RpcContext.getContext().asyncCall(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return helloWorldService.asyncSayHello("consumer1");
            }
        });
        Future<String> f1 = RpcContext.getContext().asyncCall(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return helloWorldService.asyncSayHello("consumer2");
            }
        });

        try {
            System.out.println(f.get());
            System.out.println(f1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("elapse: " + (System.currentTimeMillis() - start));
        System.in.read();

    }
}
