package com.gzb.UserDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.gzb.UserDemo.dao.TbUserMapper;
import com.gzb.UserDemo.po.TbUser;
import com.gzb.UserDemo.vo.AddUserReqVo;
import com.gzb.UserDemo.vo.FindUserByIdReqVo;
import com.gzb.UserDemo.vo.FindUserListReqVo;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {

	//日志输出
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	//数据库接口对象
	@Autowired
	private TbUserMapper tbUserMapper;
	
	
	public String AddUserInfo(AddUserReqVo reqVo) {
		
		try {
			//判断变量
			if(reqVo==null) {
				return "参数为空";
			}
			
			//判断用户名是否为空
			if("".equals(reqVo.getUserName())) {
				return "用户名为空";
			}
			
			//判断密码是否为空
			if("".equals(reqVo.getPassword())) {
				return "密码为空";
			}
			
			//创建一个新的用户对象
			TbUser tbUser = new TbUser();
			
			tbUser.setUsername(reqVo.getUserName());
			tbUser.setPassword(reqVo.getPassword());
			
			logger.info(">>>>>>>>>>到这里了>>>>>>>>>>");
			logger.info("参数为："+reqVo.toString());
			//插入数据
			int num = tbUserMapper.insert(tbUser);
			
			return "数据插入正常";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "数据插入错误";
		}
		
	}

	//分页查询用户
	public List<TbUser> FindUserList(FindUserListReqVo reqVo) {
		//创建返回参数
		List<TbUser> userList = new ArrayList<TbUser>();
		
		//判断参数是否为空
		if(reqVo==null) {
			logger.info(">>>>>>>>>>参数为空>>>>>>>>>>");
			return userList;
		}
		
		//判断当前页是否为空
		if(reqVo.getPage()==null) {
			logger.info(">>>>>>>>>>当前页参数为空>>>>>>>>>>");
			return userList;
		}
		
		//判断页面显示条数是否为空
		if(reqVo.getPageSize()==null) {
			logger.info(">>>>>>>>>>页面显示参数为空>>>>>>>>>>");
			return userList;
		}
		
		logger.info(">>>>>>>>>当前参数有：>>>>>>>>>" + reqVo.toString());
		
		//分页
		PageHelper.startPage(reqVo.getPage(), reqVo.getPageSize());
		
		Example example = new Example(TbUser.class);
		Example.Criteria criteria = example.createCriteria();
		
		if(!"".equals(reqVo.getUserName())) {
			criteria.andLike("userName", "%" + reqVo.getUserName() + "%");
			
		}
		
		//排序
		example.orderBy("id").desc();
		
		userList = tbUserMapper.selectByExample(example);
		
		return userList;
	}

	//根据id查询指定用户
	public TbUser FindUserById3(FindUserByIdReqVo reqVo) {
		
		//输出参数值
		logger.info(">>>>>>>>>>参数为："+reqVo.toString());
		
		//创建返回值
		TbUser user = new TbUser();
		
		//先判断参数是否为空
		if(reqVo==null) {
			logger.info(">>>>>>>>>>参数值为空");
			return user;
		}
		
		//判断id是否为空
		if(reqVo.getId()==null) {
			logger.info(">>>>>>>>>>id值为空");
			return user;
		}
		
		//进行查询
		user = tbUserMapper.selectByPrimaryKey(reqVo.getId());
		//user = tbUserMapper.findUserById2(reqVo.getId());
		
		return user;
	}

	

}
