package com.stone.distributed.lock.redis;

import java.io.Serializable;

/**
 * @author yuanxiu
 * @date 2020/12/27
 */
public class RedisObj implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
