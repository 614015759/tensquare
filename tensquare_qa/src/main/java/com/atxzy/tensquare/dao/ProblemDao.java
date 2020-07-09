package com.atxzy.tensquare.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atxzy.tensquare.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * problem数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 根据ID查询最新问题列表
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) order by replytime desc")
    public Page<Problem> findNewListByLabelId(String labelid, Pageable pageable);

    /**
     * 根据标签id查询热门问题列表
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid = ?1) order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelid,Pageable pageable);

    /**
     * 根据id查询等待回答问题列表
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid = ?1) and reply = 0 order by createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelid,Pageable pageable);
}
