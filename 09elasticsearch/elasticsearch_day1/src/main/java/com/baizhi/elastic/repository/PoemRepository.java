package com.baizhi.elastic.repository;


import com.baizhi.entity.Poem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PoemRepository extends ElasticsearchRepository<Poem,String> {
}
