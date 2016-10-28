package com.upsmart.message.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc 外部调用接口
 * @date 2016年10月17日
 */
@Service
public interface InterfaceService {
    //查询对象信息，type(０表示群组，１表示个人)
    public Map<String, Object> search(int type, int searchid);
    //选择对象发送，type(０表示群组，１表示个人),way(1表示微信，2表示邮箱，3表示短信，4表示全部)
    public String send(int cid, int type, int sendid, int way, String content);
    //输入账号信息发送，way(1表示微信，2表示邮箱，3表示短信)
//    public String sendown(String account, int way, String content);
}
