package com.upsmart.message.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 发送邮件返回的message
 * @date 3/9/16
 */
@Component
public class MailMessage implements Serializable {

    private static final long serialVersionUID = -4171603668519675599L;

    public static final int MESSAGE_SEND_FAILED = 0;
    public static final int MESSAGE_SEND_SUCCESS = 1;

    public static final String MESSAGE_ADDRESS_NOT_FOUND_MSG = "邮件地址有误或者认证失败";
    public static final String MESSAGE_ATTACH_FAILED_MSG = "附件添加失败";
    public static final String MESSAGE_SEND_SUCCESS_MSG = "邮件发送成功";
    public static final String MESSAGE_SEND_FAILED_MSG = "邮件发送失败";

    // 响应code
    private int code;
    // 响应具体描述
    private String desc;
    // 响应耗时(ms)
    private long cost;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
