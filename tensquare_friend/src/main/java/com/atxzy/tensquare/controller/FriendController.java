package com.atxzy.tensquare.controller;

import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.service.FriendService;

import com.netflix.discovery.converters.Auto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;


    @Autowired
    private HttpServletRequest request;

    /**
     * 添加好友
     */
    @RequestMapping(value = "/like/{friendId}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendId,@PathVariable String type){

        System.out.println("我进来了");
        Claims claims = (Claims) request.getAttribute("claims_user");


        if (claims == null){
            return new Result(false, StatusCode.LOGINERROR,"无权访问");
        }
        //如果是喜欢
    if(type.equals("1")){
        if (friendService.addFriend(claims.getId(),friendId) == 0){
            return new Result(false,StatusCode.REPERROR,"已经添加此好友");
        }
    }else{//不喜欢
        friendService.addNoFriend(claims.getId(),friendId);

    }
    return new Result(true,StatusCode.OK,"添加成功！");
    }

    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(String friendid){
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null){
            return new Result(false,StatusCode.LOGINERROR,"权限不足");
        }
        friendService.deleteFriend(claims.getId(),friendid);
        return new Result(true,StatusCode.OK,"删除成功！");


    }


}
