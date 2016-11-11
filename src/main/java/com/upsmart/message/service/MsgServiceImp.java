package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.Msg;
import com.upsmart.message.repository.MsgRepository;
import com.upsmart.message.repository.SendObjectRepository;
import com.upsmart.message.util.DateUtil;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月19日
 */
@Service
public class MsgServiceImp implements MsgService {
    private static Logger logger = LoggerFactory.getLogger(SendObjectServiceImpl.class);
    @Autowired
    private MsgRepository msgRepository;
    @Autowired
    private SendObjectRepository sendObjectRepository;

    @Override
    public String insertmsg(int cid, String mcontent, int oid, int sendway) {
        Msg msg = new Msg();
        msg.setCid(cid);
        msg.setOid(oid);
        msg.setMcontent(mcontent);
        msg.setSendway(sendway);
        Date sendtime = DateUtil.getNowDate();
        msg.setSendtime(sendtime);
        try {
            this.msgRepository.save(msg);
        } catch (Exception e) {
            logger.error("存储消息记录表异常");
        }
        return "success";
    }

    public List<Map<String, Object>> findByCid(int cid) {
        List<Msg> msgs = this.msgRepository.findByCid(cid);
        List<Map<String, Object>> returnResult = new ArrayList<>();
        for (int i = 0; i < msgs.size(); i++) {
            Map<String, Object> msgsMap = new HashMap<>();
            int oid = msgs.get(i).getOid();
            msgsMap.put("oname", this.sendObjectRepository.findByOid(oid).getOname());
            msgsMap.put("sendway", msgs.get(i).getSendway());
            msgsMap.put("mcontent", msgs.get(i).getMcontent());
            msgsMap.put("sendtime", msgs.get(i).getSendtime());
            returnResult.add(msgsMap);
        }
        return returnResult;
    }
}
