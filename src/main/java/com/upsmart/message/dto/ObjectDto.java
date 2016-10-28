package com.upsmart.message.dto;

import java.util.Date;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 上午11:50:30
 */
public class ObjectDto {
    private int oid;
    
    private String oname;
    
    private String brand;
    
    private String wchat;
    
    private String email;
    
    private String message;
    
    private Date createtime;
    
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
