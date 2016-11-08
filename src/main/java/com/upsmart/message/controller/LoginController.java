package com.upsmart.message.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.domain.Admin;
import com.upsmart.message.dto.ClientDto;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.service.LoginService;
import com.upsmart.message.service.LoginServiceImp;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage adminLogin(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password, HttpSession session) {
        BaseMessage msg = new BaseMessage();
        try {
            Admin admin = this.loginService.checkadmin(name, password);
            if (null != admin) {
                ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
                session.setAttribute(GlobalConstants.ID, admin.getAid());
                session.setAttribute(GlobalConstants.NAME, admin.getAname());
                System.out.println("管理员已经登陆");
            } else {
                ResponseUtil.buildResMsg(msg, StatusCode.ACCOUNT_PWD_INVALID_NOT_EXISTS);
            }
        } catch (Exception e) {
            ResponseUtil.buildResMsg(msg, StatusCode.ERROR);
            logger.error("系统验证登陆出错");
        }
        return msg;
    }
    /*
     * @author aidar
     * 
     * @version 0.0.1
     * 
     * @desc controller
     * 
     * @date 2016年10月19日
     */

    @Autowired
    private LoginServiceImp loginServiceImpl;

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage login(@RequestParam(value = "cname", required = true) String cname,
            @RequestParam(value = "cpassword", required = true) String cpassword, HttpSession session) {
        BaseMessage msg = new BaseMessage();
        try {
            ClientDto dto = this.loginServiceImpl.login(cname, cpassword);
            if (dto != null) {
                // 账户、密码正确，进入用户主页
                session.setAttribute("cid", dto.getCid());
                session.setAttribute("cname", dto.getCname());
                ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
                msg.setData("success");
            } else {
                ResponseUtil.buildResMsg(msg, StatusCode.ACCOUNT_PWD_INVALID_NOT_EXISTS);
                msg.setData("fail");
            }
        } catch (Exception e) {
            logger.error("登录异常");
        }
        return msg;
    }

    // 登出系统
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage logout(HttpSession session) {
        BaseMessage msg = new BaseMessage();
        try {
            session.removeAttribute(GlobalConstants.ID);
            session.removeAttribute(GlobalConstants.NAME);
            session.invalidate();
            ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
            msg.setData("success");
        } catch (Exception e) {
            logger.error("登出异常");
        }
        return msg;
    }
}
