package com.upsmart.message.util;

import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 接口返回结果工具类
 * @date 6/6/16
 */
public class ResponseUtil {

    public static boolean IS_SIGN = true;

    /**
     * 构造返回message
     * 
     * @param msg
     * @param res
     * @param sta
     */
    public static void buildResMsg(BaseMessage msg, StatusCode sta) {
        if (null != msg) {
            msg.setCode(sta.getCode());
            msg.setMsg(sta.getMsg());
        }
    }

    /**
     * 构造返回message
     * 
     * @param msg
     * @param res
     * @param sta
     * @param appendMessage
     */
    public static void buildResMsg(BaseMessage msg, StatusCode sta, String appendMessage) {
        if (null != msg) {
            msg.setCode(sta.getCode());
            msg.setMsg(sta.getMsg() + GlobalConstants.COMMA + appendMessage);
        }
    }

    /**
     * 
     * 通过code获取制定枚举
     * 
     * @param code
     * @return
     */
    public static StatusCode getStatusCode(String code) {
        StatusCode[] statusCodes = StatusCode.values();
        for (StatusCode status : statusCodes) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     *
     * @param map
     * @param key
     * @param defaultValue
     * @return 
     * @desc never used
     */
//    private static String getValue(Map<String, String> map, String key, String defaultValue) {
//        String value = map.get(key);
//        return isBlank(value) ? defaultValue : value;
//    }

}
