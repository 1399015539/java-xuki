package com.zsy.xuki.service;

import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.entity.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LiteratureDataService {

    //通过文献id查询文献
    public LiteratureData selectById(Integer id);

    public int insert(LiteratureData literature);

    public int edit(LiteratureData literature);

    public int delete(Integer id);

    List<LiteratureData> selectAll(Map<String, Object> queryMap);

    Integer getTotal(Map<String, Object> queryMap);
}
