package com.atxzy.tensquare.service;

import com.atxzy.tensquare.dao.CommentDao;
import com.atxzy.tensquare.pojo.Comment;
import com.atxzy.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 根据id查询评论列表
     */
    public List<Comment> findByArticleid(String articleId){
        List<Comment> byArticleid = commentDao.findByArticleid(articleId);
        return byArticleid;
    }

    /**
     * 根据文章id删除评论
     */
    public void deleteBy_id(String _id){
        commentDao.deleteById(_id);

    }

}
