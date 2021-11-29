package com.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Tourist;
import com.springboot.mapper.TouristMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TouristController {
    @Autowired
    private TouristMapper touristMapper;

    //Order相关
    @GetMapping("/getTouristList")
    public List<Tourist> getTouristList() {

        return touristMapper.selectList(null);
    }

    @GetMapping("/getTouristByName/{tname}")
    public List<Tourist> getTouristByName(@PathVariable("tname") String tname) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        wrapper.eq("tname" ,tname);
        return touristMapper.selectList(wrapper);
    }

    @GetMapping("/getTouristByPhone/{tphone}")
    public List<Tourist> getTouristByPhone(@PathVariable("tphone") String tphone) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        wrapper.eq("tphone" ,tphone);
        return touristMapper.selectList(wrapper);
    }

    @PostMapping("/updateTourist")
    public void updateOrder(@RequestBody Tourist tourist) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        wrapper.eq("tid" , tourist.getTid());
        touristMapper.update(tourist,wrapper);
        log.info("更新成功");
    }

    @PostMapping("/insertTourist")
    public int insertTourist(@RequestBody Tourist tourist) {
        return touristMapper.insert(tourist);
    }

    @PostMapping("/deleteTourist/{tid}")
    public void deleteTourist(@PathVariable String tid) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        wrapper.eq("tid" , tid);
        touristMapper.delete(wrapper);

    }
}
