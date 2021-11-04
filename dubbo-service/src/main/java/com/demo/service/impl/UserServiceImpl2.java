package com.demo.service.impl;

import com.demo.pojo.User;
import com.demo.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

// 重试次数为：n，则会进入服务的方法：n+1 次
/**
 * 多版本：常用于 灰度发布
 *   如：A,B,C 三个服务消费者，消费：D服务提供者，如图：
 *      A ---> D
 *      B ---> D
 *      C ---> D
 *   现在由于种种原因，D服务需要升级，
 *   开发完成后 D服务升级为 D2服务，
 *   为了让用户的体验好，先让一部分用户去体验 D2 服务，如图：
 *      A ---> D
 *      B ---> D
 *      C ---> D2
 *   用户反馈好了以后，在进行全部升级
 *   （这种做法，本质上，还是为了：用户体验好 ）
 */
@Service(timeout=3000, retries=3, version = "v2.0")
public class UserServiceImpl2 implements IUserService {

    @Override
    public String sayHello() {
        return "hello dubbo! I love you~" + "! The service provider's version is: v2.0";
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
