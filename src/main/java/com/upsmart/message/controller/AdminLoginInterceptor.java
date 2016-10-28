package com.upsmart.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.upsmart.message.constant.GlobalConstants;

public class AdminLoginInterceptor implements HandlerInterceptor {

    // private final String ADMINSESSION = "adminsession";

    // 拦截前处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object sessionObj = request.getSession().getAttribute(GlobalConstants.ID);
        if (sessionObj != null) {
            System.out.println("url>>>" + request.getRequestURL().toString());
            System.out.println("管理员登陆页获取到了session！");
            return true;
        }
        System.out.println("url>>>" + request.getRequestURL().toString());
        response.sendRedirect(request.getContextPath()+"/admin");
        System.out.println("管理员登陆页未获取到session！");
        return false;
    }

    // 拦截后处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
            throws Exception {
    }

    // 全部完成后处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
            throws Exception {
    }
}