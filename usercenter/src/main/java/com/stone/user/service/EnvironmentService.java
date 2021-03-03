package com.stone.user.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author yuanxiu
 * @date 2021/1/22
 */
@Service
public class EnvironmentService implements InitializingBean {

    @Autowired
    private Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(environment.getProperty("server.port"));
    }
}
