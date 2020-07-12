package com.atxzy.tensquare.controller;

import com.atxzy.tensquare.entity.PageResult;
import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.pojo.Spit;
import com.atxzy.tensquare.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;




@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    /**
     * 点赞
     */
    @RequestMapping(value = "/thumbup/{id}",method = RequestMethod.PUT)
    public Result Thumbup(@PathVariable String id){
        spitService.updateThumbupMongoDB(id);
        return new Result(true,StatusCode.OK,"点赞成功！");
    }

    /**
     * 根据上级id查询列表
     */
    @RequestMapping(value = "/search/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page,@PathVariable int size){
        Page<Spit> byParentId = spitService.findByParentId(parentId, page, size);
        PageResult<Spit> spitPageResult = new PageResult<Spit>(byParentId.getTotalElements(),byParentId.getContent());
        return new Result(true,StatusCode.OK,"查询成功",spitPageResult);
    }


    /**
     * 查询全部数据
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功！",spitService.findAll());
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功！",spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加成功！");
    }

    /**
     * 修改
     */

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public Result update(@PathVariable String id,@RequestBody Spit spit){
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功！");
    }


}
