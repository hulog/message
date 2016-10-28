package com.upsmart.message.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.service.MsgService;
import com.upsmart.message.util.ResponseUtil;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc 
 * @date 2016年10月18日
 */

@Controller
@RequestMapping("/msg")
public class MsgController {
	
	private static Logger logger = LoggerFactory.getLogger(MsgController.class);
	
	@Autowired
	private MsgService msgService;
	
	@RequestMapping(value = "querymsg", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage querymsg(HttpSession session) {
		BaseMessage msg = new BaseMessage();
		try {
		    int cid = (Integer)session.getAttribute("cid");
	          ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
	          msg.setData(this.msgService.findByCid(cid));
		} catch (Exception e) {
	          logger.error("查询信息失败");
	          ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
	          e.printStackTrace();
		}
		return msg;
	}
}