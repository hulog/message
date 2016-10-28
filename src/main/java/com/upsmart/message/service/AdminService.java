package com.upsmart.message.service;

import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月17日 
 */
@Service
public interface AdminService {
    public String insertadmin(String aname, String apassword);
}
