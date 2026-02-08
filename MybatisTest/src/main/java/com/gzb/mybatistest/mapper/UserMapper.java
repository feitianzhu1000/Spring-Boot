package com.gzb.mybatistest.mapper;

import com.gzb.mybatistest.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 21:55
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user")
    public List<User> list();
}
