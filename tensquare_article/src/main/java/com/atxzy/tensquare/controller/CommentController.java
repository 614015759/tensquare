package com.atxzy.tensquare.controller;

import com.atxzy.tensquare.entity.PageResult;
import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.pojo.Comment;
import com.atxzy.tensquare.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"添加成功！");

    }

    /**
     * 根据articleId查询列表
     */
    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String articleId){

        List<Comment> byArticleid = commentService.findByArticleid(articleId);
        return new Result(true,StatusCode.OK,"查询成功",byArticleid);

    }

    /**
     * 根据id删除评论
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(String id){
        commentService.deleteBy_id(id);
        return new Result(true,StatusCode.OK,"删除成功！");
    }

}
