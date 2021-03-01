package com.stone.distributed.lock.redis;

import com.alibaba.fastjson.JSONObject;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 使用redission 实现简单的分布式锁
 *
 * @author yuanxiu
 * @date 2020/12/17
 */
public class SimpleRedission {

    public static String redisPath = "redis://127.0.0.1:6379";

    public static void main(String[] args) throws Exception {
        // 获取redissonClient
        RedissonClient redissonClient = getRedissonClient();

        testLock(redissonClient);
        // testBloomFilter(redissonClient);
        // testBucket(redissonClient);

    }

    public static void testBucket(RedissonClient redissonClient) throws Exception {
        RBucket<RedisObj> bucket = redissonClient.getBucket("/redission/distributed/bucket");
        RedisObj obj = new RedisObj();
        obj.setId("1");
        obj.setName("redis 对象");
        bucket.set(obj);
        System.out.println(JSONObject.toJSONString(bucket.get()));
    }


    /**
     * 难道要产生全局唯一id ？
     * @param redissonClient
     * @throws Exception
     */
    public static void testLock(RedissonClient redissonClient) throws Exception {
        for (int i=0; i<5; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取锁对象实例（无法保证是按线程的顺序获取到）
                        RLock rLock = redissonClient.getLock("/redission/distributed/lock");
                        // 尝试获取锁, 模拟同一个线程id
                        rLock.lockAsync(100).sync();
                        System.out.println("fetch lock");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("unlock");
                        rLock.unlockAsync(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        }
    }

    public static void testBloomFilter(RedissonClient redissonClient) throws Exception {
        // 获取锁对象实例（无法保证是按线程的顺序获取到）
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("/redission/distributed/bloomFilter");
        bloomFilter.tryInit(1000000, 0.03);
        // 尝试添加
        bloomFilter.add("1234567890");
        bloomFilter.add("1234567891");
        System.out.println(bloomFilter.contains("1234567890"));
        System.out.println(bloomFilter.contains("1234567892"));
    }

    public static RedissonClient getRedissonClient() {
        // 1.构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress(redisPath).setDatabase(0);
        // 2.构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
