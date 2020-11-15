package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Data
@Accessors(chain = true)
public class Dept {
    private String id;
    private String name;

}
