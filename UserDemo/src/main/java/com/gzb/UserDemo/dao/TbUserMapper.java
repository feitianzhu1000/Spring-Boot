package com.gzb.UserDemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gzb.UserDemo.po.TbUser;
import com.gzb.UserDemo.utils.MyMapper;

@Repository
public interface TbUserMapper extends MyMapper<TbUser> {

	TbUser findUserById2(@Param("Id")Integer id);
	
}