package com.zsy.xuki.service;

import com.zsy.xuki.entity.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LogService {
    int insert(Log log);
    int delete(Integer id);
    int edit(Log log);
    Log selectById(Integer id);
    List<Log> selectAll(Map<String, Object> queryMap);
    Integer getTotal(Map<String, Object> queryMap);
}
