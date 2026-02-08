package com.gzb.mybatistest.service;

import com.gzb.mybatistest.mapper.UserMapper;
import com.gzb.mybatistest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 22:05
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public List<User> list(){
        return userMapper.list();
    }
}
