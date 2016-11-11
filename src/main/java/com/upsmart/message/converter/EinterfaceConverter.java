package com.upsmart.message.converter;

import org.springframework.stereotype.Component;

import com.upsmart.message.domain.EInterface;
import com.upsmart.message.dto.EinterfaceDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:33:15
 */
@Component
public class EinterfaceConverter {
    public EinterfaceDto toDto(EInterface eInterface) {
        EinterfaceDto einterfaceDto = new EinterfaceDto();
        if (null != eInterface) {
            einterfaceDto.setInfid(eInterface.getInfid());
            einterfaceDto.setInfname(eInterface.getInfname());
            einterfaceDto.setInfpassword(eInterface.getInfpassword());
            einterfaceDto.setInftype(eInterface.getInftype());
            einterfaceDto.setInfurl(eInterface.getInfurl());
            einterfaceDto.setHost(eInterface.getHost());
            einterfaceDto.setPort(eInterface.getPort());
            einterfaceDto.setTitle(eInterface.getTitle());
        }
        return einterfaceDto;
    }
}
