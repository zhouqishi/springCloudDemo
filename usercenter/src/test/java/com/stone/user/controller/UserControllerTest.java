package com.stone.user.controller;

import com.stone.user.UserApplication;
import com.stone.user.domain.UserVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.URL;
import static org.junit.Assert.*;

/**
 * @author yuanxiu
 * @date 2020/10/26
 *
 * demo
 * 单元测试相关jar版本
 * spring-boot-test 2.2.5.RELEASE
 * spring-test 5.2.4.RELEASE
 * junit 4.12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication .class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private URL base;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    @Test
    public void get() {
        ResponseEntity<UserVO> responseEntity = this.restTemplate.getForEntity(
                this.base.toString() + "/user/get", UserVO.class, "");
        System.out.println(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
    }
}