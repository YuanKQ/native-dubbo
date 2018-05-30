/******************************
 * User: yuan
 * Date: 18-5-29 上午9:18
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/
package com.pku.netlab.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pku.netlab.service.CallbackListener;
import com.pku.netlab.service.CallbackService;
import org.apache.log4j.Logger;

import java.awt.print.Printable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallbackServiceImpl implements CallbackService{
    @Override
    public void sum(int[] nums, CallbackListener listener) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        listener.getSumResult(sum);
    }

    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();
    static final Logger logger = Logger.getLogger(CallbackServiceImpl.class);

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged());
    }

    public CallbackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                        System.out.println("CallableImpl");
//                        System.out.println("size of listeners: " + listeners.size());
                        for (Map.Entry<String, CallbackListener> entry: listeners.entrySet())
                            try {
                                entry.getValue().changed(getChanged());
                            } catch (Throwable t) {
                              listeners.remove(entry.getKey());
                            }
                        Thread.sleep(5000);
                    } catch (Throwable t) {

                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private String getChanged() {
        return "Change: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String test(String name) {
        return "Test " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();

    }
}
