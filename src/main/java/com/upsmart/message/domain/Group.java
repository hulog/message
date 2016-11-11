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
 * @desc create table group
 * @date 2016年10月17日 上午10:48:37
 */
@Entity
@Table(name = "ogroup")
public class Group {
    @Id
    @Column(name = "gid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;

    // 群组名
    @Column(name = "gname", length = 50, nullable = false)
    private String gname;

    @Column(name = "createtime", nullable = false)
    private Date createtime;

    @Column(name = "modtime", nullable = false)
    private Date modtime;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
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
