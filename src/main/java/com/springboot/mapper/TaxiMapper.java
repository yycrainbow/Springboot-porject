package com.springboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.Taxi;
import com.springboot.entity.Tourist;
import org.apache.ibatis.annotations.Mapper;
import  java.util.List;

@Mapper
public interface TaxiMapper extends BaseMapper<Taxi> {
}
