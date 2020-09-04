package com.stone.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stone.user.domain.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanxiu
 * @date 2020/9/3
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "defaultGet")
    public UserVO get() {
        UserVO userVO = new UserVO();
        userVO.setUsername("anonymous");
        userVO.setPassword("");
        return userVO;
    }

    public UserVO defaultGet() {
        return null;
    }

}
