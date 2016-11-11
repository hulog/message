package com.upsmart.message.converter;

import com.upsmart.message.domain.Msg;
import com.upsmart.message.dto.MsgDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:40:01
 */
public class MsgConverter {
    public MsgDto toDto(Msg msg) {
        MsgDto msgDto = new MsgDto();
        if (null != msgDto) {
            msgDto.setCid(msg.getCid());
            msgDto.setMcontent(msg.getMcontent());
            msgDto.setMid(msg.getMid());
            msgDto.setOid(msg.getOid());
            msgDto.setSendtime(msg.getSendtime());
            msgDto.setSendway(msg.getSendway());
        }
        return msgDto;
    }
}
