package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by HIAPAD on 2019/11/19.
 */
@Data
@Accessors(chain = true)
public class Book {
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String content;

}
