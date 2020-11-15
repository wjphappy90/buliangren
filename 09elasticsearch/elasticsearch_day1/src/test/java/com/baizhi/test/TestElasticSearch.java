package com.baizhi.test;

import com.baizhi.entity.Poem;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestElasticSearch {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void test(){

        SearchQuery searchQuery   = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.terms("author_aggs").field("author").size(100))
                .build();
        //执行搜索结果
        AggregatedPage<Poem> page = elasticsearchTemplate.queryForPage(searchQuery, Poem.class);

        //获取聚合结果
        Aggregations aggregations = page.getAggregations();


        //获取指定聚合结果
        StringTerms stringTerms = aggregations.get("author_aggs");

        //获取聚合数量
        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println("bucket.getKey() = " + bucket.getKey());
            System.out.println("bucket.getDocCount() = " + bucket.getDocCount());
        }


    }
}
