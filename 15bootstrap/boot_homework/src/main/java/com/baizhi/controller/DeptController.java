package com.baizhi.controller;

import com.baizhi.entity.Dept;
import com.baizhi.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Controller
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;



    //添加部门
    @RequestMapping("save")
    public String save(Dept dept){
        deptService.save(dept);
        return "redirect:/back/dept/list.jsp";
    }

    //查询所有部门
    @RequestMapping("findAllDepts")
    public String findAllDepts(Model model){
        List<Dept> depts = deptService.findAll();
        model.addAttribute("depts",depts);
        return "back/dept/list";
    }

    //查询所有部门返回是html 代码
    @RequestMapping("findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        List<Dept> depts = deptService.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        //构建select代码
        depts.forEach(dept->{
            sb.append("<option value='"+dept.getId()+"'>"+dept.getName()+"</option>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(sb.toString());
    }

}
