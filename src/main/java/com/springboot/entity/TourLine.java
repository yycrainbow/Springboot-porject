package com.springboot.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class TourLine implements Serializable {
    private static final long serialVersionUID = -1L;

    //线路编号
    private String twno;
    //线路名称
    private String twname;
    //起点
    private String twstart;
    //终点
    private String twend;
    //路径详情
    private String twdetail;
}
