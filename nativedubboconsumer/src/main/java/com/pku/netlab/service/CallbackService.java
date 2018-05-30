/******************************
 * User: yuan
 * Date: 18-5-28 下午10:26
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/

package com.pku.netlab.service;

public interface CallbackService {
    void addListener(String key, CallbackListener listener);
    String test(String name);
}
