package com.upsmart.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.converter.ClientConverter;
import com.upsmart.message.domain.Admin;
import com.upsmart.message.domain.Client;
import com.upsmart.message.dto.ClientDto;
import com.upsmart.message.repository.AdminRepository;
import com.upsmart.message.repository.ClientRepository;
import com.upsmart.message.util.Md5;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午3:34:26
 */
@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private AdminRepository adminRepository;

    public int check(String cname, String password) {
        if (null != this.clientRepository.findByCnameAndCpassword(cname, Md5.MD5(Md5.MD5(password)))) {
            return this.clientRepository.findByCnameAndCpassword(cname, Md5.MD5(Md5.MD5(password))).getCid();
        } else {
            return -1;
        }
    }

    public int check(String cname) {
        if (null != this.clientRepository.findByCname(cname)) {
            return 1;
        } else {
            return -1;
        }
    }

    public Admin checkadmin(String name, String password) {
        Admin admin = this.adminRepository.findByAnameAndApassword(name, Md5.MD5(password));
        if (null != admin) {
            return admin;
        } else {
            return null;
        }
    }

    public ClientDto login(String cname, String cpassword) {
        Client client = this.clientRepository.findByCnameAndCpassword(cname,Md5.MD5(Md5.MD5(cpassword)));
        return this.clientConverter.toDto(client);
    }
}
