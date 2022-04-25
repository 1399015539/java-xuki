package com.zsy.xuki.mapper;

import com.zsy.xuki.entity.LiteratureData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface LiteratureDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LiteratureData record);

    LiteratureData selectByPrimaryKey(Integer id);

    List<LiteratureData> selectAll(Map<String, Object> queryMap);

    int updateByPrimaryKey(LiteratureData record);

    Integer getTotal(Map<String, Object> queryMap);
}
