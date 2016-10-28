package com.upsmart.message.converter;

import com.upsmart.message.domain.Admin;
import com.upsmart.message.dto.AdminDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:14:04
 */
public class AdminConverter {
    public AdminDto toDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        if(null != admin) {
            adminDto.setAid(admin.getAid());
            adminDto.setAname(admin.getAname());
            adminDto.setApassword(admin.getApassword());
            adminDto.setEmail(admin.getEmail());
            adminDto.setMessage(admin.getMessage());
            adminDto.setWechat(admin.getWechat());
        }
        return adminDto;
    }
}
