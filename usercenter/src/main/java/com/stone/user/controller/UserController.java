package com.stone.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stone.user.model.StoneUser;
import com.stone.user.domain.UserVO;
import com.stone.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public StoneUser getUser(Integer id) {
        return userService.selectById(id, "张三");
    }

    /**
     * 方便测试，这里使用get
     * @param userName
     * @return
     */
    @GetMapping("/insertUser")
    public Integer insertUser(String userName) {
        return userService.insert(userName);
    }

}
