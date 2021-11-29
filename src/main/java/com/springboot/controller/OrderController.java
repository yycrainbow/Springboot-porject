package com.springboot.controller;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Order;
import com.springboot.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@TableName("`order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    //Order相关
    @GetMapping("/getOrderList")
    public List<Order> getOrderList() {
        return orderMapper.selectList(null);
    }

    @GetMapping("/getOrderByDno/{dno}")
    public List<Order> getOrderByDno(@PathVariable("dno") String dno) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("dno",dno);
        return orderMapper.selectList(wrapper);
    }

    @GetMapping("/getOrderByPhone/{tphone}")
    public List<Order> getOrderByPhone(@PathVariable("tphone") String tphone) {
//  public List<Order> getOrderByPhone(@PathVariable("stphone") String tphone) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("tphone",tphone);
        return orderMapper.selectList(wrapper);
    }

    @PostMapping("/updateOrder")
    public void updateOrder(@RequestBody Order order) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("dno" , order.getDno());
        orderMapper.update(order,wrapper);
        log.info("更新成功");
    }

    @PostMapping("/insertOrder")
    public int insertOder(@RequestBody Order order) {
        return orderMapper.insert(order);
    }

    @PostMapping("/deleteOrder/{dno}")
    public void deleteOrder(@PathVariable("dno") String dno) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("dno" ,dno);
        orderMapper.delete(wrapper);
        log.info("删除成功");
    }
}
