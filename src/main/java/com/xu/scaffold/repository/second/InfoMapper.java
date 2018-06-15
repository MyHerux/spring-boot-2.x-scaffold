package com.xu.scaffold.repository.second;

import com.xu.scaffold.entity.Info;
import com.xu.scaffold.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InfoMapper {
    @Select("select * from info where id = #{id}")
    Info SelectInfoById(@Param("id") int id);
}
