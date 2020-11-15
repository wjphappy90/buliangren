package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Created by HIAPAD on 2019/11/20.
 */

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
//操作ES注解
@Document(indexName = "dangdang",type = "book")
public class Book {
    @Id
    private String id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Date)
    private Date pubdate;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Keyword)
    private String author;




}
