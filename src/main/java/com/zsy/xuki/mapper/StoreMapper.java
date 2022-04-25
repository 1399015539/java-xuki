package com.zsy.xuki.mapper;

import com.zsy.xuki.entity.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    Store selectByPrimaryKey(Integer id);

    List<Store> selectAll(Map<String, Object> queryMap);

    int updateByPrimaryKey(Store record);

    Integer getTotal(Map<String, Object> queryMap);
}
