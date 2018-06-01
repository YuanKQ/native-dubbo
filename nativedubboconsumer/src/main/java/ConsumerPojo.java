import com.alibaba.dubbo.rpc.RpcContext;
import com.pku.netlab.pojo.User;
import com.pku.netlab.service.UserService;
import org.codehaus.jackson.map.JsonSerializer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConsumerPojo {
    public static void main(String args[]) throws IOException {
        DubboConsumerConfig config = new DubboConsumerConfig();
        config.config();
        UserService userService = config.getUserService();
        Future<List<User>> f = RpcContext.getContext().asyncCall(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return userService.queryAll();
            }
        });
        List<User> users = null;
        try {
            users = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (User user: users) {
            printUser(user);
            System.out.println("----------------");
        }

        User user = new User();
        user.setName("hello world.");
        user.setId(1);
        user.setAge(18);
        user = userService.changeInfo(user);
        printUser(user);

        System.in.read();
    }

    static void printUser(User u){
        System.out.println("Name: " + u.getName());
        System.out.println("ID  : " + u.getId());
        System.out.println("Age : " + u.getAge());
        System.out.println("Info: ");
        for (String info: u.getInfos())
            System.out.println(info);
        ;
    }
}
