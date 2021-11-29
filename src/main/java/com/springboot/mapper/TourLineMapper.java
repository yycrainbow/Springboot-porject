package com.springboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.TourLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourLineMapper extends BaseMapper<TourLine> {

}
