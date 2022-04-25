package com.zsy.xuki.service.impl;

import com.zsy.xuki.entity.User;
import com.zsy.xuki.mapper.UserMapper;
import com.zsy.xuki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        if (user != null) {
            return userMapper.insert(user);
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        if (id != null) {
            return userMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int edit(User user) {
        if (user != null && user.getId() != null) {
            return userMapper.updateByPrimaryKey(user);
        }
        return 0;
    }

    @Override
    public User selectById(Integer id) {
        if (id != null) {
            return userMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public User selectByName(String name) {
        if (name != null) {
            return userMapper.selectByUserName(name);
        }
        return null;
    }

    @Override
    public List<User> selectAll(Map<String, Object> queryMap) {
        return userMapper.selectAll(queryMap);
    }

    @Override
    public List<User> likeSearch(String name) {
        if (name != null) {
            return userMapper.likeSearch(name);
        }
        Map<String, Object> queryMap = new HashMap<>();
        return userMapper.selectAll(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return userMapper.getTotal(queryMap);
    }
}
