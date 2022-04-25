package com.zsy.xuki.service.impl;

import com.zsy.xuki.entity.Log;
import com.zsy.xuki.mapper.LogMapper;
import com.zsy.xuki.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public int insert(Log log) {
        if (log != null) {
            return logMapper.insert(log);
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        if (id != null) {
            return logMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int edit(Log log) {
        if (log != null && log.getId() != null) {
            return logMapper.updateByPrimaryKey(log);
        }
        return 0;
    }

    @Override
    public Log selectById(Integer id) {
        if (id != null) {
            return logMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<Log> selectAll(Map<String, Object> queryMap) {
        return logMapper.selectAll(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return logMapper.getTotal(queryMap);
    }

}
