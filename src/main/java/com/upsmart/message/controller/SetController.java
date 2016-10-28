package com.upsmart.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.service.SetService;
import com.upsmart.message.util.ResponseUtil;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月18日
 */
@Controller
@RequestMapping(value = "/set")
public class SetController {
    @Autowired
    private SetService setService;
    
    @RequestMapping(value = "/setwchat", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage setwchat(@RequestParam(value = "wurl", required = true) String url,
                @RequestParam(value = "wname", required = true) String wname,
                @RequestParam(value = "wpassword", required = true) String wpassword){
        BaseMessage message = new BaseMessage();
        try {
            if(null == this.setService.setwchat(url, wname, wpassword)) {
                ResponseUtil.buildResMsg(message, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(message, StatusCode.ERROR);
            }
        } catch (Exception e) {
            ResponseUtil.buildResMsg(message, StatusCode.SYSTEM_ERROR,this.setService.setwchat(url, wname, wpassword));
        }
        return message;
    }
    
    @RequestMapping(value = "/setemail", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage setemail(@RequestParam(value = "title", required = true) String title,
                @RequestParam(value = "ename", required = true) String ename,
                @RequestParam(value = "epassword", required = true) String epassword,
                @RequestParam(value = "host", required = true) String host,
                @RequestParam(value = "port", required = true) String port){
        BaseMessage message = new BaseMessage();
        try {
            if(null == this.setService.setemail(title, host, port, ename, epassword)) {
                ResponseUtil.buildResMsg(message, StatusCode.SUCCESS);
            } else {
                ResponseUtil.buildResMsg(message, StatusCode.ERROR, this.setService.setemail(title, host, port, ename, epassword));
            }
        } catch (Exception e) {
            ResponseUtil.buildResMsg(message, StatusCode.SYSTEM_ERROR);
        }
        return message;
    }
}
