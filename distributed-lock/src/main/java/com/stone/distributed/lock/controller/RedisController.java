package com.stone.distributed.lock.controller;

import com.stone.distributed.lock.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanxiu
 * @date 2020/12/15
 */
@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping("/redis/lock")
    public String lock() {
        redisService.lock();
        return "success";
    }

}
