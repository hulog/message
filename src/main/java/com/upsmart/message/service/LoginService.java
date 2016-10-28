package com.upsmart.message.service;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.Admin;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午3:33:07
 */
@Service
public interface LoginService {
    public int check(String cname, String password);
    
    public Admin checkadmin(String name, String password);
    public Object login(String cname, String password);
}
