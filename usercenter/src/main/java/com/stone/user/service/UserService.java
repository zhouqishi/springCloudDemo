package com.stone.user.service;

import com.alibaba.fastjson.JSONObject;
import com.stone.user.mapper.StoneUserMapper;
import com.stone.user.model.StoneUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanxiu
 * @date 2020/10/30
 */
@Component
@Slf4j
public class UserService {

    @Autowired
    private StoneUserMapper userMapper;

    @Transactional
    public StoneUser selectById(Integer id, String userName) {
        StoneUser userDO = userMapper.selectByPrimaryKey(id);
        log.info("UserService.selectById result = {}", JSONObject.toJSON(userDO));
        return userDO;
    }

    @Transactional
    public Integer insert(String userName) {
        StoneUser userDO = new StoneUser();
        userDO.setUserName(userName);
        Integer id = userMapper.insert(userDO);
        log.info("UserService.insert result = {}", JSONObject.toJSON(id));
        return userDO.getId();
    }

}
