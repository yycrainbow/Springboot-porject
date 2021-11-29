package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Hotel;
import com.springboot.mapper.HotelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class HotelController {
    @Autowired
    private HotelMapper hotelMapper;

    @GetMapping("/getHotelList")
    public List<Hotel> getHotelList(){
        return hotelMapper.selectList(null) ;

    }
    @PostMapping("/insertHotel")
    public int insertHotel(@RequestBody Hotel hotel){
        return hotelMapper.insert(hotel);
    }

    @PostMapping("/deleteHotel/{hotelname}")
    public int deleteHotel(@PathVariable String hotelname){
//        JSONObject jsonObject = JSONObject.parseObject(hotelParam);
//        Hotel hotel = JSON.toJavaObject(jsonObject,Hotel.class);
//        hotelMapper.deleteHotel(hotel);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("hotelname" , hotelname);
        return hotelMapper.delete(wrapper);
    }

    @PostMapping("/updateHotel")
    public void updateHotel(@RequestBody Hotel hotel){
        QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
//        log.info("接收对象:" , hotel);
        wrapper.eq("hotelname" , hotel.getHotelname());
        hotelMapper.update(hotel,wrapper);
        log.info("更新成功");
    }

    @GetMapping("/getHotelByHName/{hotelname}")
    public List<Hotel> getHotelByHName(@PathVariable("hotelname") String hotelname){
        QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
        wrapper.eq("hotelname" ,hotelname);
        return hotelMapper.selectList(wrapper);
    }
}
