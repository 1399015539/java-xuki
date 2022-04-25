package com.zsy.xuki.service.impl;

import com.zsy.xuki.entity.Store;
import com.zsy.xuki.mapper.StoreMapper;
import com.zsy.xuki.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int insert(Store store) {
        if (store != null) {
            return storeMapper.insert(store);
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        if (id != null) {
            return storeMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int edit(Store store) {
        if (store != null && store.getId() != null) {
            return storeMapper.updateByPrimaryKey(store);
        }
        return 0;
    }

    @Override
    public Store selectById(Integer id) {
        if (id != null) {
            return storeMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<Store> selectAll(Map<String, Object> queryMap) {
        return storeMapper.selectAll(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return storeMapper.getTotal(queryMap);
    }

}
