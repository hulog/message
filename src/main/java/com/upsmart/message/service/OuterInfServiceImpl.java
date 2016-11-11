package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.converter.MailConverter;
import com.upsmart.message.domain.MailMessage;
import com.upsmart.message.domain.SendObject;
import com.upsmart.message.dto.MailDto;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日
 */
@Service
public class OuterInfServiceImpl implements OuterInfService {

    private static Logger logger = LoggerFactory.getLogger(OuterInfServiceImpl.class);

    @Autowired
    private WeixinService weixinService;
    @Autowired
    private MailConverter mailConverter;
    @Autowired
    private MailServiceImpl mailServiceImpl;
    @Autowired
    private SmsService smsService;

    @Override
    public boolean sendSms(String content, List<SendObject> userlist) {
        return this.smsService.sendSms(content, userlist);
    }

    @Override
    public boolean sendWx(String content, List<SendObject> userlist) {
        // TODO Auto-generated method stub
        if (null != userlist && userlist.size() != 0) {
            logger.info("微信发送成功");
            return this.weixinService.sendMsgToUsers(userlist, content);
        } else {
            logger.error("发送对象为空,微信发送失败！");
            return false;
        }
    }

    @Override
    public boolean sendEmail(String content, List<SendObject> emaillist) {
        List<String> to = new ArrayList<String>();
        for (SendObject s : emaillist) {
            to.add(s.getEmail());
        }
        // TODO 连接邮箱用“,”
        MailDto dto = this.mailConverter.mailDtoWapper(content, to);
        MailMessage ms = this.mailServiceImpl.sendMail(dto);
        if (ms.getCode() == MailMessage.MESSAGE_SEND_SUCCESS) {
            return true;
        } else {
            return false;
        }
    }
}
