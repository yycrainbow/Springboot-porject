package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("`users`")
@Data
public class User {

    //用户号
    private String id;

    //用户姓名
    private String name;

    private String pwd;
    //用户邮箱
    private String mail;
    //职位
    private String type;
    //标签
    private  String sign;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mail='" + mail + '\'' +
                ", type='" + type + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
