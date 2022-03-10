package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户模块")
@TableName("`users`")
@Data
public class User {

    @ApiModelProperty(value = "用户ID" ,required = true)
    private String userId;

    @ApiModelProperty(value = "账号名(昵称)" ,required = false)
    private String accountName;

    @ApiModelProperty(value = "密码" ,required = true)
    private String userPwd;

    @ApiModelProperty(value = "用户名" ,required = true)
    private String userName;

    @ApiModelProperty(value = "用户身份 1-->普通用户 2-->配送员" ,required = true)
    private String userRole;

    @ApiModelProperty(value = "性别" ,required = false)
    private String userGender;

    @ApiModelProperty(value = "手机号码" ,required = true)
    private String userPhone;

    @ApiModelProperty(value = "邮箱" ,required = true)
    private String userMail;

    @ApiModelProperty(value = "余额" ,required = false)
    private String Money;

    @ApiModelProperty(value = "用户状态 0 --> 正常 1 --> 已提交申请" ,required = true)
    private String userStatus;

    @ApiModelProperty(value = "是否删除 0 --> 否 1 --> 是" ,required = true)
    private String isDelete;

    @ApiModelProperty(value = "注册时间" ,required = true)
    private String createTime;

    @ApiModelProperty(value = "type" ,required = false)
    private String type;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userMail='" + userMail + '\'' +
                ", Money='" + Money + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createTime='" + createTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
