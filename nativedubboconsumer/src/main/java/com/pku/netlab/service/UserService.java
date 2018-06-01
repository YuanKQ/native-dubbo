package com.pku.netlab.service;

import com.pku.netlab.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> queryAll();
    public User changeInfo(User user);
}
