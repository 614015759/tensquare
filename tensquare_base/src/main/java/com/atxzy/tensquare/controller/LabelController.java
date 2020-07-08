package com.atxzy.tensquare.controller;


import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.pojo.Label;
import com.atxzy.tensquare.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/label")
@RestController
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap){
        List<Label> search = labelService.findSearch(searchMap);
        return new Result(true,StatusCode.OK,"查询成功！",search);
    }

    /**
     * 条件+分页查询
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result<Label> findSeach(@RequestBody Map srearchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> search = labelService.findSearch(srearchMap, page, size);
        return new Result<>(true,StatusCode.OK,"查询成功！",search.getContent());


    }

    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll(){
        return new Result<>(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id){
        return new Result<>(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Result update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功！");
    }

}

