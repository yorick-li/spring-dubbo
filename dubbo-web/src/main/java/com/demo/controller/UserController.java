package com.demo.controller;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    // 引用服务提供者
    @Reference(version = "v1.0", loadbalance = "consistenthash")
//    @Autowired
    private IUserService userService;

    @RequestMapping("/sayHello")
    public String sayHello() {
        return userService.sayHello();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    int i = 1;
    @RequestMapping("/findUser")
    public User findById(int id) {
        // 启动一个线程计时：超时时间
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (i<10) {
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println(i++);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        return userService.findById(id);
    }

}
