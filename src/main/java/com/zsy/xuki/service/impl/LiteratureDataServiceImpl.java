package com.zsy.xuki.service.impl;

import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.mapper.LiteratureDataMapper;
import com.zsy.xuki.service.LiteratureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiteratureDataServiceImpl implements LiteratureDataService {

    @Autowired
    private LiteratureDataMapper literatureDataMapper;

    @Override
    public LiteratureData selectById(Integer id) {
        return literatureDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(LiteratureData literature) {
        if (literature != null) {
            return literatureDataMapper.insert(literature);
        }
        return 0;
    }

    @Override
    public int edit(LiteratureData literature) {
        if (literature != null && literature.getId() != null) {
            return literatureDataMapper.updateByPrimaryKey(literature);
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        if (id != null) {
            return literatureDataMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public List<LiteratureData> selectAll(Map<String, Object> queryMap) {
        return literatureDataMapper.selectAll(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return literatureDataMapper.getTotal(queryMap);
    }

}
