package com.upsmart.message.service;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.EInterface;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc 配置发送接口
 * @date 2016年10月18日
 */
@Service
public interface SetService {
    public String setwchat(String url, String wname, String wpassword);
    
    public String setemail(String title, String host, String port, String ename, String epassword);
    
    public EInterface findConfig(int inftype);
}
