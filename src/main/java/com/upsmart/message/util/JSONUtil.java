package com.upsmart.message.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author json工具类
 * @version 0.0.1
 * @desc 接口返回结果工具类
 * @date 6/6/16
 */
public class JSONUtil {
    private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转为JSON字符串
     * 
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.info("对象转为JSON字符串失败" + e.getMessage(), e);
        }
        return str;
    }

    /**
     * JSON字符串转为对象
     * 
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toObject(String json, Class<T> valueType) throws IOException {
        T object = null;
        try {
            object = objectMapper.reader(valueType).readValue(json);
        } catch (IOException e) {
            logger.info("JSON字符串转为对象" + e.getMessage());
            throw e;
        }
        return object;
    }

    public static Map<String, Object> jsonToObject(String jsonStr) throws Exception {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        Iterator<String> nameItr = jsonObj.keys();
        String name;
        Map<String, Object> outMap = new HashMap<String, Object>();
        while (nameItr.hasNext()) {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }
}
