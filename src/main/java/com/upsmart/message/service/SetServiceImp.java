package com.upsmart.message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.EInterface;
import com.upsmart.message.repository.EInterfaceRepository;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc 配置发送接口实现
 * @date 2016年10月18日
 */
@Service
public class SetServiceImp implements SetService {

    @Autowired
    private EInterfaceRepository eInterfaceRepository;
    
    private static Logger logger = LoggerFactory.getLogger(SetService.class);
    
    public String setwchat(String url, String wname, String wpassword) {
        String result = null;
        try {
            EInterface eInterface = new EInterface();
            eInterface.setInfname(wname);
            eInterface.setInfurl(url);
            eInterface.setInfpassword(wpassword);
            eInterface.setInftype(1);
            this.eInterfaceRepository.save(eInterface);
        } catch (Exception e) {
            logger.error("存储微信设置失败");
            result = "系统存储微信设置失败";
        }
        return result;
    }
    
    public String setemail(String title, String host, String port, String ename, String epassword) {
        String result = null;
        try {
            EInterface eInterface = new EInterface();
            eInterface.setHost(host);
            eInterface.setTitle(title);
            eInterface.setInfname(ename);
            eInterface.setInfpassword(epassword);
            eInterface.setPort(port);
            eInterface.setInftype(2);
            this.eInterfaceRepository.save(eInterface);
        } catch (Exception e) {
            result = "存储邮箱信息失败";
            logger.error("系统存储邮箱信息失败");
        }
        return result;
    }

}
