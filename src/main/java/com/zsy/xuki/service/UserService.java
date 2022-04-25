package com.zsy.xuki.service;

import com.zsy.xuki.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    public int insert(User user);

    int delete(Integer id);

    int edit(User user);

    User selectById(Integer id);

    User selectByName(String name);

    List<User> selectAll(Map<String, Object> queryMap);

    List<User> likeSearch(String name);

    Integer getTotal(Map<String, Object> queryMap);
}
