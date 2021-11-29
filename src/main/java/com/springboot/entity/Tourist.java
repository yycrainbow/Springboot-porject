package com.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tourist implements Serializable {

    private static final long serialVersionUID = -1L;

    //游客手机号码
    private String tphone;

    //游客姓名
    private String tname;

    //身份证号
    private String tid;

    //游客性别
    private String sex;
}
