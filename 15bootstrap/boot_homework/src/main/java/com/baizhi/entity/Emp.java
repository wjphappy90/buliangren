package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Data
@Accessors(chain = true)
public class Emp {
    private String id;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bir;

    private Dept  dept;//部门关系属性
}
