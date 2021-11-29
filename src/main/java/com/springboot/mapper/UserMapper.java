package com.springboot.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@TableName("`users`")
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
