package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.domain.ObjectGroup;
import com.upsmart.message.domain.SendObject;
import com.upsmart.message.repository.ObjectGroupRepository;
import com.upsmart.message.repository.SendObjectRepository;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月17日
 */
@Service
public class SendObjectServiceImpl implements SendObjectService {
    private static Logger logger = LoggerFactory.getLogger(SendObjectServiceImpl.class);
    @Autowired
    private SendObjectRepository sendObjectRepository;
    @Autowired
    private ObjectGroupRepository objectGroupRepository;
    @Autowired
    private MsgServiceImp msgServiceImpl;

    @Autowired
    private OuterInfService outerInfService;

    public List<Map<String, Object>> queryObjects() {
        List<SendObject> objects = this.sendObjectRepository.findAll();
        List<Map<String, Object>> returnResult = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            Map<String, Object> objectsMap = new HashMap<>();
            int oid = objects.get(i).getOid();
            // int gid = this.objectGroupRepository.findByOid(oid).getGid();
            objectsMap.put("oid", oid);
            objectsMap.put("oname", objects.get(i).getOname());
            // objectsMap.put("gid", gid);
            // objectsMap.put("gname", this.groupRepository.findByGid(gid));
            returnResult.add(objectsMap);
        }
        return returnResult;
    }

    // 查询所需的发送对象并存储发送记录
    public List<SendObject> querysos(Integer[] sendids, Integer[] groupids, int sendway, String mcontent, int cid) {
        List<SendObject> sos = new ArrayList<>();
        if (null != sendids) {
            if (0 != sendids.length) {
                for (int i = 0, slen = sendids.length; i < slen; i++) {
                    SendObject so = this.sendObjectRepository.findByOid(sendids[i]);
                    sos.add(so);
                }
            }
        }
        if (null != groupids) {
            if (0 != groupids.length) {
                for (int i = 0, glen = groupids.length; i < glen; i++) {
                    List<ObjectGroup> ogs = this.objectGroupRepository.findByGid(groupids[i]);
                    for (int k = 0; k < ogs.size(); k++) {
                        int oid = ogs.get(k).getOid();
                        SendObject so = this.sendObjectRepository.findByOid(oid);
                        sos.add(so);
                    }
                }
            }
        }
        for (int k = 0; k < sos.size(); k++) {
            msgServiceImpl.insertmsg(cid, mcontent, sos.get(k).getOid(), sendway);
        }
        return sos;
    }

    public String sendmsg(Integer[] msgtypes, Integer[] sendids, Integer[] groupids, String context, int cid) {
        String result = null;
        for (int i = 0, len = msgtypes.length; i < len; i++) {
            // 发送对象调用微信接口
            if (msgtypes[i] == GlobalConstants.WEIXIN) {
                try {
                    List<SendObject> sos = querysos(sendids, groupids, GlobalConstants.WEIXIN, context, cid);
                    // 调用微信接口
                    if (!this.outerInfService.sendWx(context, sos)) {
                        result = "微信发送错误";
                    }
                } catch (Exception e) {
                    logger.error("调用微信接口异常");
                    result = "微信接口出错";
                }
            }
            // 调用邮箱接口
            if (msgtypes[i] == GlobalConstants.EMAIL) {
                try {
                    List<SendObject> sos = querysos(sendids, groupids, GlobalConstants.EMAIL, context, cid);
                    // 调用邮箱接口
                    if (!this.outerInfService.sendEmail(context, sos)) {
                        result = "邮件发送错误";
                    }
                } catch (Exception e) {
                    logger.error("调用邮箱接口异常");
                    result = "邮箱接口出错";
                }
            }
            // 调用短信接口
            if (msgtypes[i] == GlobalConstants.SMS) {
                try {
                    List<SendObject> sos = querysos(sendids, groupids, GlobalConstants.SMS, context, cid);
                    // 调用短信接口
                    if (!this.outerInfService.sendSms(context, sos)) {
                        result = "短信发送错误";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("调用短信接口异常");
                    result = "短信接口出错";
                }
            }
        }
        return result;
    }
}
