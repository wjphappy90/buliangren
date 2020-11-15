package com.baizhi.test;

import com.baizhi.dao.PoemDAO;
import com.baizhi.elastic.repository.PoemRepository;
import com.baizhi.entity.Poem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPoem {

    @Autowired
    private PoemRepository poemRepository;

    @Autowired
    private PoemDAO poemDAO;


    @Test
    public void test() throws IOException {
       List<Poem> all = poemDAO.findAll();
       poemRepository.saveAll(all);



    }

}
