package com.upsmart.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.upsmart.message.service.SendObjectService;
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
@RequestMapping("/object")
public class SendObjectController {
    private static Logger logger = LoggerFactory.getLogger(SendObjectController.class);
  @Autowired
  private SendObjectService sendObjectService;

  //查询所有的用户
  @RequestMapping(value = "query", method = RequestMethod.GET)
  @ResponseBody
  public BaseMessage query() {
      BaseMessage msg = new BaseMessage();
      try {
          ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
          msg.setData(this.sendObjectService.queryObjects());
      } catch (Exception e) {
          logger.error("查询所有发送对象信息异常");
          ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
          e.printStackTrace();
      }
      return msg;
  }
  
  @RequestMapping(value = "sendmsg", method = RequestMethod.POST)
  @ResponseBody
  public BaseMessage sendmsg(HttpServletRequest request, HttpSession session,
          @RequestParam(value = "msgType", required = false) Integer[] msgtypes,
          @RequestParam(value = "sendid", required = false) Integer[] sendids,
          @RequestParam(value = "groupid", required = false) Integer[] groupids,
          @RequestParam(value = "context", required = false) String context) {
      BaseMessage msg = new BaseMessage();
      try {
          int cid = (Integer)session.getAttribute("cid");
          if(null != this.sendObjectService.sendmsg(msgtypes, sendids, groupids, context, cid)) {
              ResponseUtil.buildResMsg(msg, StatusCode.ERROR, this.sendObjectService.sendmsg(msgtypes, sendids, groupids, context, cid));
          } else {
              ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
          }
      } catch (Exception e) {
          logger.error("调用接口发送异常");
          ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
      }
      return msg;
  }
}
