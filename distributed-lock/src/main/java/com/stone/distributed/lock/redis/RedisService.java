package com.stone.distributed.lock.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanxiu
 * @date 2020/12/15
 */
@Service
public class RedisService {

    @Autowired
    RedissonClient redissonClient;

    public void lock() {
        RLock rLock = redissonClient.getLock("/lock/distributed/redis");
        rLock.lock();
        System.out.println("get lock");
        try {
            // do something
            TimeUnit.SECONDS.sleep(30);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("unlock");
            rLock.unlock();
        }
    }

}
