package com.springboot.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Hotel implements  Serializable {

    private static final long serialVersionUID = -1L;

    //酒店名称
    private  String hotelname;
    //所在地
    private String local;
    //级别
    private String level;
    //详细位置
    private String adress;
    //状态
    private String status;
}
