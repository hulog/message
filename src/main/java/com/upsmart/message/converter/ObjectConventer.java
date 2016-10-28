package com.upsmart.message.converter;

import com.upsmart.message.domain.SendObject;
import com.upsmart.message.dto.ObjectDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:43:19
 */
public class ObjectConventer {
    public ObjectDto toDto(SendObject object) {
        ObjectDto objectDto = new ObjectDto();
        if(null != object) {
            objectDto.setBrand(object.getBrand());
            objectDto.setCreatetime(object.getCreatetime());
            objectDto.setEmail(object.getEmail());
            objectDto.setMessage(object.getMessage());
            objectDto.setModtime(object.getModtime());
            objectDto.setOid(object.getOid());
            objectDto.setOname(object.getOname());
            objectDto.setWchat(object.getWchat());
        }
        return objectDto;
    }
}
