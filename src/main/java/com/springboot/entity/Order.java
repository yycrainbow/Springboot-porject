package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = -1L;

    // 线路编号
    private String twno;

    //游客姓名
    private String tname;

    //游客手机号码
    private String tphone;

    //酒店名称
    private String hotelname;

    //车牌号
    private String txno;

    //车辆类型
    private String txtype;

    //景点
    private String tickname;

    //业务员
    private String slave;

    //订单号码
    private String dno;

    //房间类型
    private String room;

    @Override
    public String toString() {
        return "Order{" +
                "twno='" + twno + '\'' +
                ", tname='" + tname + '\'' +
                ", tphone='" + tphone + '\'' +
                ", hotelname='" + hotelname + '\'' +
                ", txno='" + txno + '\'' +
                ", txtype='" + txtype + '\'' +
                ", tickname='" + tickname + '\'' +
                ", slave='" + slave + '\'' +
                ", dno='" + dno + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
