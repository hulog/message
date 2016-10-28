package com.upsmart.message.service;

import java.util.Date;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.Admin;
import com.upsmart.message.repository.AdminRepository;
import com.upsmart.message.util.Md5;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月19日 
 */
@Service
public class AdminServiceImp implements AdminService {
    private static Logger logger = LoggerFactory.getLogger(AdminServiceImp.class);
    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public String insertadmin(String aname, String apassword) {
        Admin admin = new Admin();
        admin.setAname(aname);
        admin.setApassword(Md5.MD5(apassword));
        try {
            this.adminRepository.save(admin);
        } catch (Exception e) {
            logger.error("存储管理员用户名和密码异常");
        }
        return "success";
    }

}
