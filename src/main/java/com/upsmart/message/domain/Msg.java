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
 * @desc create table msg
 * @date 2016年10月17日 上午10:48:19
 */
@Entity
@Table(name = "msg")
public class Msg {
    @Id
    @Column(name = "mid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;
    
    //发送对象编号
    @Column(name = "oid", nullable = false)
    private int oid;
    
    //使用客户编号
    @Column(name = "cid", nullable = false)
    private int cid;
    
    //消息发送方式
    @Column(name = "sendway" , nullable = false)
    private int sendway;
    
    //消息发送内容
    @Column(name = "mcontent", nullable = false)
    private String mcontent;
    
    @Column(name = "sendtime", nullable = false)
    private Date sendtime;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSendway() {
        return sendway;
    }

    public void setSendway(int sendway) {
        this.sendway = sendway;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
}
