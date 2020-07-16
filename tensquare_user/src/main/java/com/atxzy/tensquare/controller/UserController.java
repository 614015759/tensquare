package com.atxzy.tensquare.controller;
import java.util.List;
import java.util.Map;

import com.atxzy.tensquare.entity.PageResult;
import com.atxzy.tensquare.entity.Result;
import com.atxzy.tensquare.entity.StatusCode;
import com.atxzy.tensquare.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atxzy.tensquare.pojo.User;
import com.atxzy.tensquare.service.UserService;

import javax.servlet.http.HttpServletRequest;


/**
 * user控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * 增加关注数
	 */
	@RequestMapping(value = "/incfollows/{userid}/{x}",method = RequestMethod.POST)
	public void incFollowcount(@PathVariable String userid,@PathVariable int x){
		userService.incFollowcount(userid,x);
	}

	/**
	 * 增加粉丝数
	 */
	@RequestMapping(value = "/incfans/{userid}/{x}")
	public Result incFanscount(@PathVariable String userid,@PathVariable int x){
		userService.incFanscount(userid, x);
		return new Result(true,StatusCode.OK,"增加成功！");
	}


	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Result login( @RequestBody User user){
		System.out.println(user.getMobile() + user.getPassword());
		User user1 = userService.findByMobile(user.getMobile(), user.getPassword());
		if(user1 != null){
			String token = jwtUtil.createJWT(user1.getId(), user1.getMobile(), "user");
			return new Result(true,StatusCode.OK,"登陆成功",token);
		}else{
			return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
		}
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
	public Result register(@PathVariable String code,@RequestBody User user){
		userService.add(user,code);
		return new Result(true,StatusCode.OK,"注册成功");
	}


	/**
	 * 发送短信验证码
	 */
	@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
	public Result sendsms(@PathVariable String mobile){
		userService.sendEms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * 必须有admin角色才能删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id){



		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
