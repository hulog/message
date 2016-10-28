package com.upsmart.message.converter;

import com.upsmart.message.domain.ObjectGroup;
import com.upsmart.message.dto.ClientGroupDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Norman
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:29:43
 */
public class ClientGroupConverter {
    public ClientGroupDto toDto(ObjectGroup clientGroup) {
        ClientGroupDto clientGroupDto = new ClientGroupDto();
        if(null != clientGroup) {
            clientGroupDto.setGid(clientGroup.getGid());
            clientGroupDto.setId(clientGroup.getId());
            clientGroupDto.setOid(clientGroup.getOid());
        }
        return clientGroupDto;
    }
}
