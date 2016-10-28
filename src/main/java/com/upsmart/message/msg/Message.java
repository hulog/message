package com.upsmart.message.msg;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 返回消息的默认实现
 * @date 6/6/16
 */
public abstract class Message<T> {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    protected String Code;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    protected String Msg;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    protected String sign;

    public Message() {
        super();
    }

    public Message(StatusCode statusCode) {
        this(statusCode.getCode(), statusCode.getMsg());
    }

    public Message(String Code, String Msg) {
        super();
        this.Code = Code;
        this.Msg = Msg;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public abstract T getData();

    public abstract void setData(T data);
}
