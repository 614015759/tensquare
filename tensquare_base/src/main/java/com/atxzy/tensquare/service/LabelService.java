package com.atxzy.tensquare.service;

import com.atxzy.tensquare.dao.LabelDao;
import com.atxzy.tensquare.pojo.Label;
import com.atxzy.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        List<Label> all = labelDao.findAll();
        return all;
    }

    public Label findById(String id){
        Label byId = labelDao.findById(id).get();
        return byId;
    }

    public void add(Label label){
        label.setId(idWorker.nextId()+""); //设置ID
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }
}
