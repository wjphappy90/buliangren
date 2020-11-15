package com.baizhi.service;

import com.baizhi.entity.Emp;

import java.util.List;

/**
 * Created by HIAPAD on 2019/11/12.
 */
public interface EmpService {

    List<Emp> findAll(Integer page, Integer rows);

    Long findTotalCounts();

    void save(Emp emp);

    void update(Emp emp);

    void delete(String id);

    List<Emp> findSearch(String searchField, String searchString, String searchOper, Integer page, Integer rows);

    Long findTotalCountsSearch(String searchField, String searchString, String searchOper);
}
