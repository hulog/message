package com.upsmart.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.SendObject;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc
 * @date 2016年10月17日
 */
@Service
public interface OuterInfService {

    public boolean sendSms(String content, List<SendObject> userlist);

    public boolean sendWx(String content, List<SendObject> userlist);

    public boolean sendEmail(String content, List<SendObject> to);

}
