package com.baizhi.service;

import com.baizhi.dao.DeptDAO;
import com.baizhi.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {



    @Autowired
    private DeptDAO deptDAO;
    @Override
    public List<Dept> findAll() {
        return deptDAO.findAll();
    }

}
