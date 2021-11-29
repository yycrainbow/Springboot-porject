package com.springboot.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@TableName("`order`")
@Mapper
public interface OrderMapper extends BaseMapper<Order> {


}

