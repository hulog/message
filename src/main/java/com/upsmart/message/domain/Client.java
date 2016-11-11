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
 * @desc create table client
 * @date 2016年10月17日 上午10:18:02
 */
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "cid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    // 客户名
    @Column(name = "cname", length = 30, nullable = false)
    private String cname;

    // 客户密码
    @Column(name = "cpassword", length = 32, nullable = false)
    private String cpassword;

    @Column(name = "createtime", nullable = false)
    private Date createtime;

    @Column(name = "modtime", nullable = false)
    private Date modtime;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
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
