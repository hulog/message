package com.upsmart.message.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.message.domain.Client;
import com.upsmart.message.repository.ClientRepository;
import com.upsmart.message.util.DateUtil;
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
public class ClientServiceImp implements ClientService {
    private static Logger logger = LoggerFactory.getLogger(ClientServiceImp.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public String insertclient(String cname, String cpassword) {
        Client client = new Client();
        client.setCname(cname);
        client.setCpassword(Md5.MD5(cpassword));
        Date date = DateUtil.getNowDate();
        client.setCreatetime(date);
        client.setModtime(date);
        try {
            this.clientRepository.save(client);
        } catch (Exception e) {
            logger.error("存储发送人用户名和密码异常");
        }
        return "success";
    }

    @Override
    @Transactional
    public boolean deleteClient(String[] strs) {
        // List<Integer> cids = new ArrayList<Integer>();
        try {
            for (String s : strs) {
                this.clientRepository.delete(Integer.parseInt(s));
            }
            logger.warn("delete client(s) successful......");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}