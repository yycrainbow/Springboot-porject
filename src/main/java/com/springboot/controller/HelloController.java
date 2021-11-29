package com.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Employee;
import com.springboot.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private EmployeeMapper employeeMapper;

    //Employee相关
    @GetMapping("/getEmployeeList")
    public List<Employee> getEmployeeList() {
//        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        return employeeMapper.selectList(null);
    }

    @GetMapping("/getGuiderList")
    public List<Employee> getGuiderList() {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("role","导游");
        return employeeMapper.selectList(wrapper);
    }

    @GetMapping("/getEmployeeById/{id}")
    public List<Employee> getEmployeeById(@PathVariable("id") String id) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id" ,id);
        return employeeMapper.selectList(wrapper);
    }

    @PostMapping("/insertEmployee")
    public int insertEmployee(@RequestBody Employee employee) {
        return employeeMapper.insert(employee);

    }

    @PostMapping("/updateEmployee")
    //public String updateEmployee(@RequestParam("phone") String phone, @RequestParam("id") String id ){
    public int updateEmployee(@RequestBody Employee employee){
//        log.info(("传入值为:"),employee);
        return employeeMapper.updateById(employee);
    }

    @PostMapping("/deleteEmployee/{id}")
    public int deleteEmployee(@PathVariable long id){
//        log.info("传入id为:",id);
//        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",id);
//        return employeeMapper.delete(wrapper);
       return employeeMapper.deleteById(id);
    }

}

