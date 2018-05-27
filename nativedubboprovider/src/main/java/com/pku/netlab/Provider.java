/******************************
 * User: yuan
 * Date: 18-5-27 下午8:22
 * Email: kq_yuan@outlook.com
 *
 * Description:
 *
 ******************************/
package com.pku.netlab;

import org.apache.log4j.Logger;

public class Provider {
    final static Logger logger = Logger.getLogger(Provider.class);
    public static void main(String[] args) {

        try {
            Class.forName("com.pku.netlab.DubboProviderConfig");
            logger.info("success create class");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
