package com.atxzy.tensquare.service;

import com.atxzy.tensquare.dao.LabelDao;
import com.atxzy.tensquare.pojo.Label;
import com.atxzy.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /***
     * 构建查询条件 *
     * @param searchMap
     * *
     * @return */
    private Specification<Label> createSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
        public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(searchMap.get("labelname")!=null && !"".equals(searchMap.get("labelname"))){
                    predicateList.add(cb.like( root.get("labelname")
                        .as(String.class), "%"+ (String)searchMap.get("labelname")+"%" ) ); }
                if(searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
                    predicateList.add(cb.equal( root.get("state").as(String.class), (String)searchMap.get("state") ) ); }if(searchMap.get("recommend")!=null && !"".equals(searchMap.get("recommend"))){ predicateList.add(cb.equal( root.get("recommend").as(String.class), (String)searchMap.get("recommend") ) );
    }return cb.and( predicateList.toArray( new Predicate[predicateList.size()]) ); } }; }

    public Page<Label> findSearch(Map searchMap,int page,int size){
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page,size);
        Page all = labelDao.findAll(specification, pageRequest);
        return all;
    }


    /**
     * 条件查询
     * @return
     */
    public List<Label> findSearch(Map searchMap){
        Specification specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }


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
