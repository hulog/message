package com.upsmart.message.dto;

import java.util.Date;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 上午11:50:20
 */
public class MsgDto {
    private int mid;
    
    private int oid;
    
    private int cid;
    
    private int sendway;
    
    private String mcontent;
    
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
