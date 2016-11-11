package com.upsmart.message.converter;

import com.upsmart.message.domain.Group;
import com.upsmart.message.dto.GroupDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:36:44
 */
public class GroupConverter {
    public GroupDto toDto(Group group) {
        GroupDto groupDto = new GroupDto();
        if (null != group) {
            groupDto.setCreatetime(group.getCreatetime());
            groupDto.setGid(group.getGid());
            groupDto.setGname(group.getGname());
            groupDto.setModtime(group.getModtime());
        }
        return groupDto;
    }
}
