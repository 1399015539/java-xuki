package com.zsy.xuki.mapper;

import com.zsy.xuki.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String name);

    List<User> selectAll(Map<String, Object> queryMap);

    List<User> likeSearch(String name);

    int updateByPrimaryKey(User record);

    Integer getTotal(Map<String, Object> queryMap);
}
