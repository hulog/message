package com.upsmart.message.msg;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 状态码, 具体的失败消息
 * @date 6/6/16
 */
public enum StatusCode {

    // success
    SUCCESS("00000", "提交成功"),
    NO_RESPONSE("00001", "没有查询到结果"),
    // fail
    ACCOUNT_PWD_INVALID_NOT_EXISTS("10110", "账号名密码不匹配"),
    ACCOUNT_INVALID_NOT_EXISTS("10104", "账号不存在"),
    VERIFICATION_CODE_ERROR("00002","验证码出错"),
    PWD_CONFIRM("00003","密码输入不一致"),
    ERROR("00004", "操作出错,"),
    SYSTEM_ERROR("20101", "系统异常");

    private String code;
    private String msg;

    private StatusCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
}
