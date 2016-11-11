package com.upsmart.message.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc create table admin
 * @date 2016年10月17日 上午10:47:41
 */
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "aid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    // 管理员账号
    @Column(name = "aname", length = 30, nullable = false)
    private String aname;

    // 管理员密码
    @Column(name = "apassword", length = 32, nullable = false)
    private String apassword;

    // 管理员微信
    @Column(name = "wechat", length = 50)
    private String wechat;

    // 管理员邮箱
    @Column(name = "email", length = 50)
    private String email;

    // 管理员短信
    @Column(name = "message", length = 50)
    private String message;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
