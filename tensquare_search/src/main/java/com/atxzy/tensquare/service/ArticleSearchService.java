package com.atxzy.tensquare.service;

import com.atxzy.tensquare.dao.ArticleSearchDao;
import com.atxzy.tensquare.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 检索
     */
    public Page<Article> findByTitleorContent(String keyWord,int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return articleSearchDao.findByTitleOrContentLike(keyWord,keyWord,pageRequest);
    }

    /**
     * 增加文章
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

}
