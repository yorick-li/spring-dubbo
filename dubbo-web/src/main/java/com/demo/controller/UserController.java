package com.demo.controller;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // 引用服务提供者
    @Reference
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
    @RequestMapping("/findUser")
    public User findById(int id) {
        return userService.findById(id);
    }

}
