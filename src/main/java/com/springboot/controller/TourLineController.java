package com.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.TourLine;
import com.springboot.mapper.TourLineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TourLineController {
    @Autowired
    private TourLineMapper tourLineMapper;

    @GetMapping("/getTourLineList")
    public List<TourLine> getTourLineList(){
        return tourLineMapper.selectList(null);
    }
    @GetMapping("/getTourLineByNo/{twno}")
    public List<TourLine> getTourLineByNo(@PathVariable("twno") String twno) {
        QueryWrapper<TourLine> wrapper = new QueryWrapper<>();
        wrapper.eq("twno",twno);
        return tourLineMapper.selectList(wrapper);
    }
    @PostMapping("/insertTourLine")
    public void insertTourLine(@RequestBody TourLine tourLine){
        tourLineMapper.insert(tourLine);
        log.info("线路添加成功");
    }
    @PostMapping("/deleteTourLine/{twno}")
    public void deleteTourLine(@PathVariable String twno ){
        QueryWrapper<TourLine> wrapper = new QueryWrapper<>();
        wrapper.eq("twno",twno);
        tourLineMapper.delete(wrapper);
    }
    @PostMapping("/updateTourLine")
    public void updateTourLine(@RequestBody TourLine tourLine){
        QueryWrapper<TourLine> wrapper = new QueryWrapper<>();
        wrapper.eq("twno", tourLine.getTwno());
        tourLineMapper.update(tourLine,wrapper);
        log.info("线路更新成功");
    }
}
