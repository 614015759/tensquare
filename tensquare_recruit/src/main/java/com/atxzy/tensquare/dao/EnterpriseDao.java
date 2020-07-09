package com.atxzy.tensquare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atxzy.tensquare.pojo.Enterprise;

import java.util.List;

/**
 * enterprise数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    /**
     * 根据热门状态获取企业列表
     */
    public List<Enterprise> findByIshot(String ishot);
}
