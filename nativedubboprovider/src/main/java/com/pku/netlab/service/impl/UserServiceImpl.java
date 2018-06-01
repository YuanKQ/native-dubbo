package com.pku.netlab.service.impl;

import com.pku.netlab.pojo.User;
import com.pku.netlab.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            User user = new User();
            user.setAge(i);
            user.setName("username_" + i);
            user.setId(Long.parseLong("0000" + i));
            list.add(user);
        }
        return list;
    }

    @Override
    public User changeInfo(User user) {
        String mail = "e-mail: " + user.getName() + "@mail.com";
        List<String> infos = user.getInfos();
        infos.add(mail);
        user.setInfos(infos);
        return user;
    }
}
