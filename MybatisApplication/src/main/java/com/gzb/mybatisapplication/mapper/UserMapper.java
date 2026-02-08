package main.java.com.gzb.mybatisapplication.mapper;

import main.java.com.gzb.mybatisapplication.POJO.User;

import java.util.List;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 21:20
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user where 1=1")
    List<User> list();

    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);

    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);
}
