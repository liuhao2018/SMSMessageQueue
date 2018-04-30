package com.liuhao.common.sms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user (mobile,password) values (#{mobile},#{password})")
    int addOne(@Param("mobile")String mobile,@Param("password")String password);

}
