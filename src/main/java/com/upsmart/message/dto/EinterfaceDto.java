package com.upsmart.message.dto;
/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Norman
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 上午11:49:51
 */
public class EinterfaceDto {
    private int infid;
    
    private String infurl;
    
    private String infname;
    
    private String infpassword;
    
    private int inftype;

    public int getInfid() {
        return infid;
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
