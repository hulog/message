package com.upsmart.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.upsmart.message.constant.GlobalConstants;

public class UserLoginInterceptor implements HandlerInterceptor {

    // private final String ADMINSESSION = "adminsession";

    private static Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

    // 拦截前处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object sessionObj = request.getSession().getAttribute(GlobalConstants.ID);
        if (sessionObj != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.html");
        logger.info(request.getRemoteAddr() + "试图以用户身份登陆，被成功拦截");
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