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
 * @desc create table einterface
 * @date 2016年10月17日 上午10:49:19
 */
@Entity
@Table(name = "einterface")
public class EInterface {
    @Id
    @Column(name = "infid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infid;

    // 申请接口url
    @Column(name = "infurl")
    private String infurl;

    // 申请名
    @Column(name = "infname", length = 50, nullable = false)
    private String infname;

    // 申请密码
    @Column(name = "infpassword", nullable = false)
    private String infpassword;

    // 主题（邮箱）
    @Column(name = "title", length = 50)
    private String title;

    // 主机（邮箱）
    @Column(name = "host", length = 50)
    private String host;

    // 端口（邮箱）
    @Column(name = "port")
    private String port;

    // 接口类型
    @Column(name = "inftype", nullable = false)
    private int inftype;

    public int getInfid() {
        return infid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setInfid(int infid) {
        this.infid = infid;
    }

    public String getInfurl() {
        return infurl;
    }

    public void setInfurl(String infurl) {
        this.infurl = infurl;
    }

    public String getInfname() {
        return infname;
    }

    public void setInfname(String infname) {
        this.infname = infname;
    }

    public String getInfpassword() {
        return infpassword;
    }

    public void setInfpassword(String infpassword) {
        this.infpassword = infpassword;
    }

    public int getInftype() {
        return inftype;
    }

    public void setInftype(int inftype) {
        this.inftype = inftype;
    }
}
