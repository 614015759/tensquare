package com.atxzy.tensquare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atxzy.tensquare.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 审核
     */
    @Modifying
    @Query("update Article set state='1' where id = ?1" )
    public void examine(String id);

    /**
     * 点赞
     */
    @Modifying
    @Query("update Article a set thumbup=thumbup+1 where id = ?1")
    public int updateThumbup(String id);
}
