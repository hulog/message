package com.upsmart.message.util;

import java.util.List;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc
 * @date 2016年10月19日
 */
public class Stringutil {

    public static String listToStringWithSeparator(List<String> list, String separator) {
        if (null == list || list.size() == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s).append(separator);
            }
            return sb.toString().substring(0, sb.toString().length() - 1);
        }
    }
}
