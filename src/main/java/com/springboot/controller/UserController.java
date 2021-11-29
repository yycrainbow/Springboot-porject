package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.springboot.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@TableName("`users`")
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    TokenUtil tokenUtil;

    //user
    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @GetMapping("/getUserByMail/{mail}")
    public List<User> getUserByName(@PathVariable("mail") String mail) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mail" , mail);
        return userMapper.selectList(wrapper);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userMapper.updateById(user);
    }

    @PostMapping("/insertUser")
    public int insertUser(@RequestBody User user) {
        return userMapper.insert(user) ;
//        log.info("添加成功");
    }

    @PostMapping("/deleteUser/{name}")
    public void deleteUser(@PathVariable String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name" , name);
        userMapper.delete(wrapper);
//        log.info("删除成功");
    }

    @PostMapping("/login")
    public String Login(@RequestBody User user ) {
        System.out.println(user.getName() + " " + user.getPwd());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", user.getName());
        wrapper.eq("pwd", user.getPwd());
        User user1 = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user1)) {
            log.info("登录失败");
            JSONObject result = new JSONObject();
            result.put("code" , 10001);
            return result.toJSONString();
        }
            log.info("登录成功 ");
            String token = tokenUtil.getToken(user1,null);
            System.out.println(token);
            JSONObject result = new JSONObject();
            result.put("code" , 10000);
            result.put("token" , token);
            return result.toJSONString();

    }

}
