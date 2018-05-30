import com.pku.netlab.service.CallbackListener;

import java.io.Serializable;

/******************************
 * User: yuan
 * Date: 18-5-29 下午9:25
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/

public class CallbackListenerImpl implements CallbackListener ,Serializable {
    @Override
    public void changed(String msg) {
        System.out.println("First callback: " + msg);
    }
}
