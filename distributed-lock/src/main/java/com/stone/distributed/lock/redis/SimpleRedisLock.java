package com.stone.distributed.lock.redis;

import redis.clients.jedis.Jedis;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 使用jedis 实现简单的分布式锁
 * @author yuanxiu
 * @date 2020/12/17
 */
public class SimpleRedisLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 获取分布式锁(加锁代码)
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean getDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁(解锁代码)
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String path = "/lock/distributed/jedis";
        String requestId = Long.toString(Thread.currentThread().getId());
        int time = (int) TimeUnit.SECONDS.toMillis(30);
        boolean flag = getDistributedLock(jedis, path, requestId, time);
        if (flag) {
            System.out.println("getDistributedLock success");
            try {
                System.out.println("do something");
                TimeUnit.SECONDS.sleep(15);
            } catch (Exception e) {

            } finally {
                System.out.println("releaseDistributedLock");
                releaseDistributedLock(jedis, path, requestId);
            }
        } else {
            System.out.println("getDistributedLock fail");
        }
    }

}
