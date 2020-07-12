package com.atxzy.tensquare.dao;

import com.atxzy.tensquare.pojo.Spit;
import com.atxzy.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据上级id查询吐槽列表
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
