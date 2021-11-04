package com.demo.service.impl;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

// 重试次数为：n，则会进入服务的方法：n+1 次
@Service(timeout=3000, retries=3, version = "v1.0", weight = 200)
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello() {
//        return "hello dubbo! I love you~" + "! The service provider's version is: v1.0";
//        return "1....";
//        return "2....";
        return "3....";
    }


    int i = 1;
    @Override
    public User findById(int id) {
        // 启动一个线程计数：重试次数
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + i++ + "次重试");
                }
            }
        ).start();

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
