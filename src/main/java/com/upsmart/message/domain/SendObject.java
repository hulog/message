package com.upsmart.message.domain;

import java.util.Date;

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
 * @desc create table Object
 * @date 2016年10月17日 上午10:38:57
 */
@Entity
@Table(name = "object")
public class SendObject {
    @Id
    @Column(name = "oid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    // 发送对象名
    @Column(name = "oname", length = 30, nullable = false)
    private String oname;

    // 对象所属部门
    @Column(name = "brand", length = 30, nullable = false)
    private String brand;

    // 发送对象微信账号
    @Column(name = "wchat", length = 50)
    private String wchat;

    // 发送对象邮箱账号
    @Column(name = "email", length = 50)
    private String email;

    // 发送对象短信账号
    @Column(name = "message", length = 50)
    private String message;

    @Column(name = "createtime", nullable = false)
    private Date createtime;

    @Column(name = "modtime", nullable = false)
    private Date modtime;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWchat() {
        return wchat;
    }

    public void setWchat(String wchat) {
        this.wchat = wchat;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
}
