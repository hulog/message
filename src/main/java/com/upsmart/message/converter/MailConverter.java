package com.upsmart.message.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.domain.EInterface;
import com.upsmart.message.dto.MailDto;
import com.upsmart.message.repository.EInterfaceRepository;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qi
 * @version 0.0.1
 * @desc 账户domain和dto的相互转换
 * @date 4/8/16
 */
@Component
public class MailConverter {

    @Autowired
    private EInterfaceRepository eInterfaceRepository;

    private MailDto setAllMailDtoNeeded(String content, List<String> to) {
        MailDto maildto = new MailDto();

        EInterface e = this.eInterfaceRepository.findByInftype(GlobalConstants.EMAI_TYPE_IN_DATABASE);
        // 主题
        maildto.setSubject(e.getTitle());
        // 内容
        maildto.setContent(content);
        // 发件人邮箱
        maildto.setFrom(e.getInfname());
        // 发件人邮箱密码
        maildto.setPassword(e.getInfpassword());
        // 端口号
        maildto.setPort(e.getPort());
        // host
        maildto.setSmtpHost(e.getHost());
        // 收件人
        maildto.setTo(to);
        return maildto;
    }

    public MailDto mailDtoWapper(String content, List<String> to) {
        MailDto dto = this.setAllMailDtoNeeded(content, to);
        return dto;
    }
}