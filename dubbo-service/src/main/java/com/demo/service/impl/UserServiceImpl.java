package com.demo.service.impl;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

@Service(timeout=3000, retries=0)
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello() {
        return "hello dubbo! I love you~";
    }


    @Override
    public User findById(int id) {
        User user = new User(1, "zhangsan", 26);
        // 利用线程模拟超时
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

}
