package com.zsy.xuki.mapper;

import com.zsy.xuki.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Log record);

    List<Log> selectAll(Map<String, Object> queryMap);

    Integer getTotal(Map<String, Object> queryMap);
}
