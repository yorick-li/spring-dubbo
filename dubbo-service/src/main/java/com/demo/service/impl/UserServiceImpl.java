package com.demo.service.impl;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

//@Service
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello() {
        return "hello dubbo! I love you~";
    }

    @Override
    public User findById(int id) {
        User user = new User(1, "zhangsan", 26);
        return user;
    }

}
