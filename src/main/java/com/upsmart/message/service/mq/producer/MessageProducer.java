package com.upsmart.message.service.mq.producer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc
 * @date 2016年10月25日
 */
@Service
public class MessageProducer {
    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    // TODO 貌似可以用@Autowired替换
    // http://blog.csdn.net/gst6062825/article/details/8765157
    // https://www.zhihu.com/question/39356740
    // @Resource(name = "amqpTemplate")
    @Autowired
    private AmqpTemplate amqpTemplate;

    // @Resource(name = "amqpTemplate2")
    // private AmqpTemplate amqpTemplate2;

    public boolean sendMessage(Object message) throws IOException {
        logger.info("to send message:{}", message);
        amqpTemplate.convertAndSend("queueTestKey", message);// MessageConsumer.java

        return true;
        // amqpTemplate2.convertAndSend("wuxing.xxxx.wsdwd", message);
    }

    public String send(int cid, int type, int sendid, int way, String content) {
        String result = null;
        try {
            Map<String, Object> msg = new HashMap<>();
            msg.put("cid", cid);
            msg.put("type", type);
            msg.put("sendid", sendid);
            msg.put("way", way);
            msg.put("content", content);
            if(!this.sendMessage(msg)) {
                result = "发送失败";
            }
        } catch (Exception e) {
            result = "系统出错";
        }
        return result;
    }
}