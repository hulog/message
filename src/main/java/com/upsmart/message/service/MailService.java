package com.upsmart.message.service;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.MailMessage;
import com.upsmart.message.dto.MailDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc account相关的service
 * @date 4/8/16
 */
@Service
public interface MailService {
	 public MailMessage sendMail(MailDto mailDto);
}
