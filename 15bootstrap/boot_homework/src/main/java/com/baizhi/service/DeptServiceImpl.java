package com.baizhi.service;

import com.baizhi.dao.DeptDAO;
import com.baizhi.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {



    @Autowired
    private DeptDAO deptDAO;

    @Override
    public void save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        deptDAO.save(dept);
    }

    @Override
    public List<Dept> findAll() {
        return deptDAO.findAll();
    }

}
