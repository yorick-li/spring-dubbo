package com.demo.service;

import com.demo.pojo.User;

public interface IUserService {
    String sayHello();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(int id);
}
