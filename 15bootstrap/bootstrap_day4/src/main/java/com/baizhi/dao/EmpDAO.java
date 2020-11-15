package com.baizhi.dao;

import com.baizhi.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by HIAPAD on 2019/11/12.
 */
public interface EmpDAO extends BaseDAO<Emp> {

    List<Emp> findSearch(@Param("searchField") String searchField, @Param("searchString") String searchString, @Param("searchOper") String searchOper, @Param("start") Integer page, @Param("rows") Integer rows);

    Long findTotalCountsSearch(@Param("searchField") String searchField, @Param("searchString") String searchString, @Param("searchOper") String searchOper);
}
