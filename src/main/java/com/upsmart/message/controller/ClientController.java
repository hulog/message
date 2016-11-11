package com.upsmart.message.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.dto.ClientDto;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.service.ClientService;
import com.upsmart.message.util.ResponseUtil;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月17日
 */
@Controller
@RequestMapping("/client")
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;

    // 新增发送者
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage insert(@RequestParam(value = "cname", required = false) String cname,
            @RequestParam(value = "cpasswd", required = false) String cpassword) {
        BaseMessage msg = new BaseMessage();
        try {
            ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
            msg.setData(this.clientService.insertclient(cname, cpassword));
        } catch (Exception e) {
            logger.error("配置发送人用户名和密码出错");
            ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

    // delete user
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage delete(@RequestParam(value = "cids", required = true) String[] cids) {
        BaseMessage msg = new BaseMessage();
        if (null == cids || cids.length == 0) {
            ResponseUtil.buildResMsg(msg, StatusCode.ERROR_INPUT);
            return msg;
        }
        if (this.clientService.deleteClient(cids)) {
            ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
            return msg;
        }
        ResponseUtil.buildResMsg(msg, StatusCode.ERROR);
        return msg;
    }

    // query all users
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryAllClient() {
        BaseMessage msg = new BaseMessage();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<ClientDto> clientlist = this.clientService.queryAllClient();
        if (!clientlist.isEmpty()) {
            for (int i = 0; i < clientlist.size(); i++) {
                Map<String, Object> clt = new HashMap<String, Object>();
                clt.put("cid", clientlist.get(i).getCid());
                clt.put("cname", clientlist.get(i).getCname());
                result.add(clt);
            }
        }

        try {
            msg.setData(result);
        } catch (Exception e) {
            msg.setData("fail");
            e.printStackTrace();
            logger.error("fail to return object");
        }
        return msg;
    }

    // query all users
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage modifyClient(@RequestParam(value = "cid", required = false) String id,
            @RequestParam(value = "cname", required = false) String name,
            @RequestParam(value = "cpasswd", required = false) String passwd) {
        System.out.println("id=" + id + ",passwd=" + passwd + ",name=" + name);
        BaseMessage msg = new BaseMessage();
        try {
            Integer cid = Integer.parseInt(id);
            String pwd = passwd;
            String cname = name;
            if (this.clientService.modifyClient(cid, cname, pwd)) {
                ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(msg, StatusCode.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.buildResMsg(msg, StatusCode.ERROR);
        }
        return msg;
    }
}
