package com.upsmart.message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.service.InterfaceService;
import com.upsmart.message.service.LoginService;
import com.upsmart.message.service.mq.producer.MessageProducer;
import com.upsmart.message.util.ResponseUtil;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午2:13:18
 */
@Controller
public class Interface {

    private static Logger logger = LoggerFactory.getLogger(Interface.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private InterfaceService interfaceService;

    @Autowired
    private MessageProducer messageProducer;

    // 查询发送对象信息
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage search(@RequestParam(value = "client_id", required = true) String cname,
            @RequestParam(value = "client_password", required = true) String password,
            @RequestParam(value = "search_type", required = true) int type,
            @RequestParam(value = "search_id", required = true) int searchid) {
        BaseMessage msg = new BaseMessage();
        if (loginService.check(cname, password) > 0) {
            try {
                if (null != this.interfaceService.search(type, searchid)) {
                    ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
                    msg.setData(this.interfaceService.search(type, searchid));
                } else {
                    logger.warn("未查询到结果");
                    ResponseUtil.buildResMsg(msg, StatusCode.NO_RESPONSE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("查询对象出错");
                ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
            }
        } else {
            logger.error("身份验证失败");
            ResponseUtil.buildResMsg(msg, StatusCode.ACCOUNT_PWD_INVALID_NOT_EXISTS);
        }
        return msg;
    }

    // 选择对象发送
    @RequestMapping(value = "/message/send", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage send(@RequestParam(value = "client_id", required = true) String cname,
            @RequestParam(value = "client_password", required = true) String password,
            @RequestParam(value = "send_type", required = true) int type,
            @RequestParam(value = "send_id", required = true) int sendid,
            @RequestParam(value = "send_way", required = true) int way,
            @RequestParam(value = "message", required = true) String content) {
        BaseMessage msg = new BaseMessage();
        int cid = loginService.check(cname, password);
        if (cid > 0) {
            try {
                if (null == this.messageProducer.send(cid, type, sendid, way, content)) {
                    ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
                } else {
                    logger.warn("发送过程出错");
                    ResponseUtil.buildResMsg(msg, StatusCode.ERROR,
                            this.messageProducer.send(cid, type, sendid, way, content));
                }
            } catch (Exception e) {
                logger.error("发送消息失败");
                ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
            }
        } else {
            logger.error("身份验证失败");
            ResponseUtil.buildResMsg(msg, StatusCode.ACCOUNT_PWD_INVALID_NOT_EXISTS);
        }
        return msg;
    }

    // //输入账号信息发送
    // @RequestMapping(value="/message/sendown", method = RequestMethod.POST)
    // @ResponseBody
    // public BaseMessage sendown(@RequestParam(value = "client_id", required =
    // true) String cname,
    // @RequestParam(value = "client_password", required = true) String
    // password,
    // @RequestParam(value = "send_account", required = true) String account,
    // @RequestParam(value = "send_way", required = true) int way,
    // @RequestParam(value = "message", required = true) String content) {
    // BaseMessage msg = new BaseMessage();
    // if(loginService.check(cname, password)){
    // try{
    // if (null == this.interfaceService.sendown(account, way, content)) {
    // ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
    // } else {
    // logger.warn("发送过程出错");
    // ResponseUtil.buildResMsg(msg, StatusCode.ERROR,
    // this.interfaceService.sendown(account, way, content));
    // }
    // } catch(Exception e) {
    // logger.error("发送消息失败");
    // ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
    // }
    // } else {
    // logger.error("身份验证失败");
    // ResponseUtil.buildResMsg(msg, StatusCode.ACCOUNT_PWD_INVALID_NOT_EXISTS);
    // }
    // return msg;
    // }
}
