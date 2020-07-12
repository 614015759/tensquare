package com.atxzy.tensquare.service;

import com.atxzy.tensquare.dao.SpitDao;
import com.atxzy.tensquare.pojo.Spit;
import com.atxzy.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    /**
     * 注入MongoDBTemplate
     */
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 点赞
     */
    public void updateThumbupTraditional(String id){
        Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);
    }

    /**
     * 点赞MongoDB版本
     */
    public void updateThumbupMongoDB(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }


    /**
     * 根据上级id查询列表
     * @return
     */
    public Page<Spit> findByParentId(String parentId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId,pageRequest);
    }

    @Autowired
    private IdWorker idWorker;

    /***
     * 查询全部记录
     * * @return
     * */

    public List<Spit> findAll(){
        return spitDao.findAll();
    }
    /***
     * 根据主键查询实体
     * * @param id *
     * @return */
    public Spit findById(String id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }
    /***
     * 增加
     * * @param spit */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId()+""); //主键值
        spitDao.save(spit);
    }

    /**
     * 修改
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }

}
