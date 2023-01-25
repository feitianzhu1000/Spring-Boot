package com.gzb.UserDemo.vo;

import java.io.Serializable;

public class FindUserByIdReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户id
	Integer Id;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "FindUserByIdReqVo [Id=" + Id + "]";
	}
	

}
