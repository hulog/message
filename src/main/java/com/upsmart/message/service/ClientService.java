package com.upsmart.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upsmart.message.dto.ClientDto;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月17日
 */
@Service
public interface ClientService {

    public String insertclient(String cname, String cpassword);

    public boolean deleteClient(String[] cids);

    public List<ClientDto> queryAllClient();

    public boolean modifyClient(int cid, String cname, String pwd);
}
