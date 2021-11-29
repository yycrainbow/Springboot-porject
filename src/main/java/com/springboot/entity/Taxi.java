package com.springboot.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Taxi implements Serializable{

    private static final long serialVersionUID = -1L;

    //车型
    private String txkind;

    //车名
    private String txtype;

    //车牌号
    private String txno;

    //车座
    private String txseat;

    //所在地
    private String txlocal;

    //状态
    private String status;
}
