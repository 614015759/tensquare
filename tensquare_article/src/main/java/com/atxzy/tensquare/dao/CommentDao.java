package com.atxzy.tensquare.dao;

import com.atxzy.tensquare.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {


    /**
     * 根据id查询评论列表
     */
    public List<Comment> findByArticleid(String articleid);

}
