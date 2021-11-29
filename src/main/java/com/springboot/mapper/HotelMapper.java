package com.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import  com.springboot.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HotelMapper extends BaseMapper<Hotel> {

}
