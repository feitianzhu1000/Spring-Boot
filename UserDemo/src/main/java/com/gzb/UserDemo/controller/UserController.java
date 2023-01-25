package com.gzb.UserDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gzb.UserDemo.po.TbUser;
import com.gzb.UserDemo.service.UserService;
import com.gzb.UserDemo.vo.AddUserReqVo;
import com.gzb.UserDemo.vo.FindUserByIdReqVo;
import com.gzb.UserDemo.vo.FindUserListReqVo;

@RestController
@RequestMapping("/User/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//添加用户信息
	@RequestMapping("addUser")
	public String AddUser(@RequestBody AddUserReqVo reqVo) {
		
		return userService.AddUserInfo(reqVo);
		
	}
	
	//分页查询所有用户
	@RequestMapping("findUserList")
	@ResponseBody
	public List<TbUser> FindUserList(@RequestBody FindUserListReqVo reqVo){
		return userService.FindUserList(reqVo);
		
	}
	
	//根据id查询用户
	@RequestMapping("findUserById")
	@ResponseBody
	public TbUser FindUserById(@RequestBody FindUserByIdReqVo reqVo){
		return userService.FindUserById3(reqVo);
		
	}
}
