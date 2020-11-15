package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by HIAPAD on 2019/11/12.
 */
public interface BaseDAO<T> {


     void save(T t);
     void update(T t);
     void delete(String id);
     T find(String id);
     List<T> findAll();
     List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
     Long findTotalCounts();
}
