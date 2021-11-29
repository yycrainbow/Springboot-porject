package com.springboot.entity;

import lombok.Data;

@Data
public class Employee {
    // id
    private String id;

    // 员工名字
    private String name;

    // 职位
    private String role;

    // 加入时间
    private String jointime;

    private String phone;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", jointime='" + jointime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
