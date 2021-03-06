package com.upsmart.message.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.converter.ClientConverter;
import com.upsmart.message.domain.Client;
import com.upsmart.message.dto.ClientDto;
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
    @Autowired
    private ClientConverter clientConverter;

    @Override
    public String insertclient(String cname, String cpassword) {
        Client client = new Client();
        client.setCname(cname);
        client.setCpassword(Md5.MD5(Md5.MD5(cpassword)));
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

    @Override
    public List<ClientDto> queryAllClient() {
        return this.clientConverter.toDtos(this.clientRepository.findAll());
    }

    @Override
    public boolean modifyClient(int cid, String cname, String pwd) {
        try {
            Client client = this.clientRepository.findByCid(cid);
            client.setCname(cname);
            client.setCpassword(Md5.MD5(Md5.MD5(pwd)));
            Date date = DateUtil.getNowDate();
            client.setModtime(date);
            this.clientRepository.save(client);
            logger.info("modify client sucessful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}