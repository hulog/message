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

import com.upsmart.message.domain.SendObject;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.service.SetSendObjectServiceImp;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc
 * @date 2016年10月18日
 */

@Controller
@RequestMapping("/object")
public class SetObjectController {

    private static Logger logger = LoggerFactory.getLogger(SetObjectController.class);

    @Autowired
    private SetSendObjectServiceImp sendObjectServiceImp;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage returnData() {
        BaseMessage msg = new BaseMessage();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<SendObject> sendObject = this.sendObjectServiceImp.findAllObject();
        for (int i = 0; i < sendObject.size(); i++) {
            Map<String, Object> sdobj = new HashMap<String, Object>();
            sdobj.put("oid", sendObject.get(i).getOid());
            sdobj.put("oname", sendObject.get(i).getOname());
            sdobj.put("brand", sendObject.get(i).getBrand());
            sdobj.put("wechat", sendObject.get(i).getWchat());
            sdobj.put("email", sendObject.get(i).getEmail());
            sdobj.put("message", sendObject.get(i).getMessage());
            result.add(sdobj);
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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage addObject(@RequestParam(value = "oname", required = true) String oname,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "wechat", required = true) String wechat,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "message", required = true) String message) {
        BaseMessage msg = new BaseMessage();
        try {
            this.sendObjectServiceImp.addObject(oname, brand, wechat, email, message);
            msg.setData("success");
        } catch (Exception e) {
            msg.setData("fail");
            e.printStackTrace();
            logger.error("fail to add object");
        }
        return msg;
    }

    @RequestMapping(value = "mod", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage modObject(@RequestParam(value = "oid", required = false) String oidstr,
            @RequestParam(value = "oname", required = false) String oname,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "wechat", required = false) String wechat,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "message", required = false) String message) {
        BaseMessage msg = new BaseMessage();
        try {
            int oid = Integer.parseInt(oidstr);
            this.sendObjectServiceImp.modObject(oid, oname, brand, wechat, email, message);
            msg.setData("success");
        } catch (Exception e) {
            msg.setData("fail");
            e.printStackTrace();
            logger.error("fail to mod object");
        }
        return msg;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage deleteObject(@RequestParam(value = "oidlist", required = false) List<Integer> oid) {
        BaseMessage msg = new BaseMessage();
        try {
            for (int i = 0; i < oid.size(); i++) {
                this.sendObjectServiceImp.deleteObject(oid.get(i));
            }
            msg.setData("success");
        } catch (Exception e) {
            msg.setData("fail");
            e.printStackTrace();
            logger.error("fail to delete object");
        }
        return msg;
    }
}