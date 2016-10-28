package com.upsmart.message.service.mq.consumer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.service.InterfaceService;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc 消费者 接受direct消息 监听key为 queueTestKey 的消息队列
 * @date 2016年10月25日
 */
@Service
public class MessageConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private InterfaceService InterfaceService;

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message);
            Map<String, Object> map = (HashMap<String, Object>) SerializationUtils.deserialize(message.getBody());
            int cid = (int) map.get("cid");
            int type = (int) map.get("type");
            int sendid = (int) map.get("sendid");
            int way = (int) map.get("way");
            String content = (String) map.get("content");
            InterfaceService.send(cid, type, sendid, way, content);
        } catch (Exception e) {
            logger.error("接收rabbit信息失败");
            e.printStackTrace();
        }
        logger.info("consumer receive message------->:{}", message);
    }
}
