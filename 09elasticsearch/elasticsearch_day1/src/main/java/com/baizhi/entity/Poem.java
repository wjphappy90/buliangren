package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Accessors(chain =true)
@AllArgsConstructor
@NoArgsConstructor
@Document(type = "poem",indexName = "poem")
public class Poem {

    @Id
    private String id;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String href;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String authordes;

    @Field(type = FieldType.Keyword)
    private String origin;

    @Field(type = FieldType.Keyword)
    private String imagePath;

    @Field(type = FieldType.Nested)
    private Category category = new Category();
}
