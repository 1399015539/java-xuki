package com.zsy.xuki.service;

import com.zsy.xuki.entity.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {
    int insert(Store store);
    int delete(Integer id);
    int edit(Store store);
    Store selectById(Integer id);

    List<Store> selectAll(Map<String, Object> queryMap);

    Integer getTotal(Map<String, Object> queryMap);
}
