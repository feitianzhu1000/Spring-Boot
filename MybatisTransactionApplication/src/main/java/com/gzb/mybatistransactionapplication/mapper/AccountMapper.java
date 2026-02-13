package com.gzb.mybatistransactionapplication.mapper;

import com.gzb.mybatistransactionapplication.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/13 18:28
 */
@Mapper
public interface AccountMapper {
    @Select("select * from account where account_id=1")
    Account getAccount();

    @Update("update account set balance = balance+100 where account_id=1")
    void addMoney();
}
