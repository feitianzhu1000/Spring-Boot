package com.gzb.UserDemo.vo;

import java.io.Serializable;

public class FindUserListReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户名
	String userName;
	
	//当前页数
	Integer page;
	
	//页面显示条数
	Integer pageSize;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "FindUserListReqVo [page=" + page + ", pageSize=" + pageSize + "]";
	}

	
}
