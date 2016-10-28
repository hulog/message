package com.upsmart.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author 
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月20日
 */
@Controller
public class InterceptController {
    
    @RequestMapping(value="/admin")
    public String login(){
        return "admin";
    }
    
    @RequestMapping(value = "/admin/setinterface")
    public String main(){
        return "set";
    }
    
    @RequestMapping(value = "/user/send")
    public String send(){
        return "sendmsg";
    }

    @RequestMapping(value = "/admin/setgroup")
    public String setgroup(){
        return "group";
    }
    
    @RequestMapping(value = "/admin/setobject")
    public String setobject(){
        return "object";
    }
    
    @RequestMapping(value = "/admin/msg")
    public String msg(){
        return "msg";
    }
    
    @RequestMapping(value = "/admin/adduser")
    public String adduser(){
        return "addcltadm";
    }
    
    @RequestMapping(value = "/user")
    public String user(){
        return "login";
    }
}
