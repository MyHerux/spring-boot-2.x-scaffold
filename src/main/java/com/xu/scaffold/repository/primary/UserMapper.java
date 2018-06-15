package com.xu.scaffold.repository.primary;

import com.xu.scaffold.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User SelectUserById(@Param("id") int id);
}
