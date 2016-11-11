package com.upsmart.message.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.upsmart.message.domain.Client;
import com.upsmart.message.dto.ClientDto;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日 下午1:23:22
 */
@Component
public class ClientConverter {
    public List<ClientDto> toDtos(List<Client> clients) {
        if (null == clients || clients.size() == 0) {
            return null;
        }
        List<ClientDto> list = new ArrayList<>();
        for (Client element : clients) {
            list.add(this.toDto(element));
        }
        return list;
    }

    public ClientDto toDto(Client client) {
        if (null != client) {
            ClientDto clientDto = new ClientDto();
            clientDto.setCid(client.getCid());
            clientDto.setCname(client.getCname());
            clientDto.setCreatetime(client.getCreatetime());
            clientDto.setModtime(client.getModtime());
            return clientDto;
        }
        return null;
    }
}
