package com.baizhi.service;

import com.baizhi.dao.PoemDAO;
import com.baizhi.elastic.repository.PoemRepository;
import com.baizhi.entity.Poem;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PoemServiceImpl implements PoemService {

    @Autowired
    private PoemDAO poemDAO;

    @Autowired
    private PoemRepository poemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public Long findTotalCounts() {
        return poemDAO.findTotalCounts();
    }

    @Override
    public List<Poem> findByPage(Integer page, Integer size) {
        int start = (page - 1) * size;
        return poemDAO.findByPage(start, size);
    }

    @Override
    public Page<Poem> findAll(Integer page, Integer size) {
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("poem")
                .withTypes("poem")
                .withQuery(QueryBuilders.matchAllQuery())
                .withPageable(PageRequest.of((page - 1), size))
                .build();
        Page<Poem> poemPage = poemRepository.search(build);
        return poemPage;
    }

    @Override
    public List<Poem> findByKeywords(String content, String type, String author) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        QueryBuilder queryBuilder;
        //是否存在搜索条件
        if (StringUtils.isEmpty(content)) {
            queryBuilder = QueryBuilders.matchAllQuery();
            nativeSearchQueryBuilder.withQuery(queryBuilder);
        } else {
            HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                    .preTags("<span style='color:red'>")
                    .postTags("</span>")
                    .requireFieldMatch(false);
            queryBuilder = QueryBuilders.queryStringQuery(content).analyzer("ik_max_word").field("name").field("content").field("type").field("author");
            nativeSearchQueryBuilder.withQuery(queryBuilder);
            nativeSearchQueryBuilder.withHighlightFields(field);
        }

        //是否根据类型过滤
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        if (!StringUtils.isEmpty(type)) {
            filter.add(QueryBuilders.termQuery("type", type));
        }
        //是否根据作者过滤
        if (!StringUtils.isEmpty(author)) {
            filter.add(QueryBuilders.termQuery("author", author));
        }

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder
                .withPageable(PageRequest.of(0, 21))
                .withFilter(boolQueryBuilder)
                .build();


        AggregatedPage<Poem> poems = elasticsearchTemplate.queryForPage(nativeSearchQuery, Poem.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                SearchHits responseHits = response.getHits();
                SearchHit[] searchHits = responseHits.getHits();
                List<Poem> poems = new ArrayList<>();
                for (SearchHit searchHit : searchHits) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                    Poem poem = new Poem();
                    poem.setId(searchHit.getId());
                    poem.setName(sourceAsMap.get("name").toString());
                    if (highlightFields.containsKey("name")) {
                        poem.setName(highlightFields.get("name").fragments()[0].toString());
                    }
                    poem.setAuthor(sourceAsMap.get("author").toString());
                    if (highlightFields.containsKey("author")) {
                        poem.setAuthor(highlightFields.get("author").fragments()[0].toString());
                    }
                    poem.setAuthordes(sourceAsMap.get("authordes").toString());
                    if (highlightFields.containsKey("authordes")) {
                        poem.setAuthordes(highlightFields.get("authordes").fragments()[0].toString());
                    }
                    poem.getCategory().setName(sourceAsMap.get("category").toString());
                    if (highlightFields.containsKey("category")) {
                        poem.getCategory().setName(highlightFields.get("category").fragments()[0].toString());
                    }
                    poem.setContent(sourceAsMap.get("content").toString());
                    if (highlightFields.containsKey("content")) {
                        poem.setContent(highlightFields.get("content").fragments()[0].toString());
                    }
                    poem.setHref(sourceAsMap.get("href").toString());
                    poem.setType(sourceAsMap.get("type").toString());
                    poem.setImagePath(sourceAsMap.get("imagePath").toString());
                    if (highlightFields.containsKey("type")) {
                        poem.setType(highlightFields.get("type").fragments()[0].toString());
                    }
                    poems.add(poem);
                }
                return new AggregatedPageImpl<T>((List<T>) poems);
            }
        });
        return poems.getContent();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Poem> findAll() {
        return poemDAO.findAll();
    }
}
