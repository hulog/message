package com.upsmart.message.msg;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 包装接口最终返回的对象
 * @date 6/6/16
 */
public class BaseMessage extends Message<Object> {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Object data;

    public BaseMessage() {
        super();
    }

    public BaseMessage(StatusCode statusCode) {
        this.Code = statusCode.getCode();
        this.Msg = statusCode.getMsg();
    }

    public BaseMessage(StatusCode statusCode, String appendMessage) {
        this(statusCode);
        this.Code = statusCode.getCode();
        if (null != appendMessage) {
            this.Msg = statusCode.getMsg() + "," + appendMessage;
        } else {
            this.Msg = statusCode.getMsg();
        }
    }

    public BaseMessage(StatusCode statusCode, Object data) {
        this(statusCode);
        this.data = data;
    }

    public BaseMessage(StatusCode statusCode, String appendMessage, Object data) {
        this(statusCode, appendMessage);
        this.data = data;
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

}
