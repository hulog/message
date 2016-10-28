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
import com.upsmart.message.service.AdminServiceImp;
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
@RequestMapping("/admin")
public class AdminController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
  @Autowired
  private AdminServiceImp adminServiceImpl;

  //新增管理员
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  @ResponseBody
  public BaseMessage insert(
          @RequestParam(value = "name", required = false) String aname,
          @RequestParam(value = "passwd", required = false) String apassword) {
      BaseMessage msg = new BaseMessage();
      try {
          ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
          msg.setData(this.adminServiceImpl.insertadmin(aname, apassword));
      } catch (Exception e) {
          logger.error("配置管理员用户名和密码出错");
          ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
          e.printStackTrace();
      }
      return msg;
  }
}
