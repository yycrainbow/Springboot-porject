package com.springboot.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Taxi;
import com.springboot.mapper.TaxiMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
public class TaxiController {
    @Autowired
    private TaxiMapper taxiMapper;

    @GetMapping("/getTaxiList")
    public List<Taxi> getTaxiList(){
        return taxiMapper.selectList(null);
    }

    @GetMapping("/getTaxiByNo/{txno}")
    public List<Taxi> getTaxiByNo(@PathVariable("txno") String txno){
        QueryWrapper<Taxi> wrapper = new QueryWrapper<>();
        wrapper.eq("txno",txno);
       return taxiMapper.selectList(wrapper);
    }
    @PostMapping("/insertTaxi")
    public int insertTaxi(@RequestBody Taxi taxi){
        return taxiMapper.insert(taxi);
    }

    @PostMapping("/deleteTaxi/{txno}")
    public void deleteTaxi(@PathVariable String txno){
        QueryWrapper<Taxi> wrapper = new QueryWrapper<>();
        wrapper.eq("txno",txno);
        taxiMapper.delete(wrapper);
    }

    @PostMapping("/updateTaxi")
    public void updateTaxi(@RequestBody Taxi taxi){
        QueryWrapper<Taxi> wrapper = new QueryWrapper<>();
        wrapper.eq("txno" ,taxi.getTxno());
        taxiMapper.update(taxi,wrapper);
    }

}
