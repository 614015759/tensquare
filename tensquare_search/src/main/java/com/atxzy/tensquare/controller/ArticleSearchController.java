package com.atxzy.tensquare.controller;

import com.atxzy.tensquare.entity.PageResult;
import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.pojo.Article;
import com.atxzy.tensquare.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"添加成功！");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{keyword}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keyword,@PathVariable int page,@PathVariable int size){
        Page<Article> byTitleorContent = articleSearchService.findByTitleorContent(keyword,  page, size);
        PageResult<Article> articlePageResult = new PageResult<>(byTitleorContent.getTotalElements(), byTitleorContent.getContent());
        return new Result(true,StatusCode.OK,"查询成功",articlePageResult);
    }
}

