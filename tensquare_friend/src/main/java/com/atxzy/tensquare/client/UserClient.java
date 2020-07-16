package com.atxzy.tensquare.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 增加粉丝数
     */
    @RequestMapping(value = "/user/incfans/{userid}/{x}",method = RequestMethod.POST)
    public void incFans(@PathVariable("userid") String userid,@PathVariable("x") int x);

    /**
     * 增加关注数
     */
    @RequestMapping(value = "/user/incfollows/{userid}/{x}",method = RequestMethod.POST)
    public void incFollows(@PathVariable("userid") String userid,@PathVariable("x") int x);
}
