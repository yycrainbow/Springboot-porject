package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.springboot.entity.Admin;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.springboot.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@Api(value = "用户接口" , tags = "用户管理")
@TableName("`users`")
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    TokenUtil tokenUtil;

    //user
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @ApiOperation(value = "获取用户数据")
    @GetMapping("/getUserData")
    public String getUserData() {
        Integer allCount = userMapper.selectCount(null);
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("userRole" , 1);
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("userRole" , 2);
        Integer ordinaryCount = userMapper.selectCount(wrapper1);
        Integer deliverCount = userMapper.selectCount(wrapper2);
        JSONObject result = new JSONObject();
        result.put("allCount" , allCount);
        result.put("ordinaryCount" , ordinaryCount);
        result.put("deliverCount" , deliverCount);
        return result.toJSONString();
    }

    @ApiOperation(value = "手机号查询信息",notes = "根据手机号用户相关信息")
    @GetMapping("/getUserByPhone/{uphone}")
    public List<User> getUserByPhone(@PathVariable("uphone") String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userPhone" , phone);
        return userMapper.selectList(wrapper);
    }

    @ApiOperation(value = "ID查询信息",notes = "根据用户ID查询相关信息")
    @GetMapping("/getUserByUserId/{uid}")
    public List<User> getUserById(@PathVariable("uid") String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userId" , id);
        return userMapper.selectList(wrapper);
    }

    @ApiOperation(value = "邮箱查询用户列表" , notes = "根据用户邮箱获取用户相关信息")
    @GetMapping("/getUserByMail/{mail}")
    public List<User> getUserByMail(@PathVariable("mail") String mail) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userMail" , mail);
        return userMapper.selectList(wrapper);
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName" , user.getUserName());
        userMapper.update(user,wrapper);
        log.info("更新成功");
    }

    @ApiOperation(value = "添加用户信息")
    @PostMapping("/insertUser")
    public int insertUser(@RequestBody User user) {
        return userMapper.insert(user) ;
//        log.info("添加成功");
    }
    @ApiOperation(value = "删除用户信息" ,notes = "根据用户名删除")
    @PostMapping("/deleteUser/{name}")
    public void deleteUser(@PathVariable String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName" , name);
        userMapper.delete(wrapper);
//        log.info("删除成功");
    }

    @ApiOperation(value = "用户登陆")
    @PostMapping("/login")
    public String Login(@RequestBody User user ) {
        System.out.println(user.getUserName() + " " + user.getUserPwd());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", user.getUserName());
        wrapper.eq("userPwd", user.getUserPwd());
        wrapper.eq("userRole", user.getUserRole());
        User user1 = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user1)) {
            log.info("登录失败");
            JSONObject result = new JSONObject();
            result.put("code" , 10001);
            result.put("msg" , "登陆失败");
            return result.toJSONString();
        }
            log.info("登录成功 ");
            String token = tokenUtil.getToken(user1,null);
            System.out.println(token);
            JSONObject result = new JSONObject();
            result.put("code" , 10000);
            result.put("msg" , "登陆成功");
            result.put("token" , token);
            return result.toJSONString();

    }
    //用户注册（普通用户、送货员)
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public String Register(@RequestBody User user) {
        System.out.println(user.getUserName() + "" + user.getUserMail() + "" + user.getUserPwd());
        //检查用户名
        QueryWrapper<User> checkName = new QueryWrapper<>();
        checkName.eq("userName", user.getUserName());
        User checkname = userMapper.selectOne(checkName);
        //检查邮箱
        QueryWrapper<User> checkMail = new QueryWrapper<>();
        checkMail.eq("userMail", user.getUserMail());
        User checkmail = userMapper.selectOne(checkMail);

        if (ObjectUtils.isEmpty(checkname) && ObjectUtils.isEmpty(checkmail)) {
            userMapper.insert(user);
            log.info("信息添加成功！");
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("userName", user.getUserName());
            wrapper.eq("userPwd", user.getUserPwd());
            User user2 = userMapper.selectOne(wrapper);
            String token = tokenUtil.getToken(user2, null);
            System.out.println(token);
            JSONObject result = new JSONObject();
            result.put("code", 10000);
            result.put("token", token);
            return result.toJSONString();
        }else{
            log.info("信息添加失败!");
            JSONObject result = new JSONObject();
            result.put("code", 10001);
            result.put("state", "用户名或邮箱已存在!");
            return result.toJSONString();
        }
    }

    @ApiOperation(value = "角色切换申请列表" , notes = "普通用户->送货员")
    @GetMapping("/getChangeRoleList")
    public List<User> getChangeRoleList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userStatus" , "1");
        return userMapper.selectList(wrapper);
    }


    @ApiOperation(value = "角色更换申请提交(普通->送货员)" , notes = "普通用户->送货员")
    @PostMapping("/ChangeToDeliver")
    public void ChangeToDeliver(@RequestBody User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",user.getUserId());
//        updateWrapper.eq("userName" , user.getUserName());
        updateWrapper.set("userStatus" , "1");
        userMapper.update(user,updateWrapper);
        //log.info("提交");
    }

    @ApiOperation(value = "角色切换(送货员->普通用户)" , notes = "普通用户->送货员")
    @PostMapping("/ChangeToUser")
    public void ChangeToUser(@RequestBody User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",user.getUserId());
//        updateWrapper.eq("userName" , user.getUserName());
        updateWrapper.set("userRole","1");
        updateWrapper.set("userStatus" , "0");
        userMapper.update(user,updateWrapper);
        //log.info("提交");
    }

    @ApiOperation(value = "管理员审核" , notes = "普通用户->送货员")
    @PostMapping("/CheckToDeliver")
    public void CheckToDeliver(@RequestBody User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",user.getUserId());
 //       updateWrapper.eq("userName" , user.getUserName());
        updateWrapper.set("userStatus" , "0");
        updateWrapper.set("userRole" , "2");
        userMapper.update(user,updateWrapper);
        //log.info("提交");
    }


}
