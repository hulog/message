package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.SendObject;
import com.upsmart.message.repository.SendObjectRepository;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author wangjm
 * @version 0.0.1
 * @desc 接口实现
 * @date 2016年10月17日
 */
@Service
public class InterfaceServiceImp implements InterfaceService {

    private Logger logger = LoggerFactory.getLogger(InterfaceServiceImp.class);

    @Autowired
    private SendObjectRepository sendObjectRepository;

    @Autowired
    private OuterInfService outerInfService;
    
    @Autowired
    private MsgService msgService;
    
    public Map<String, Object> search(int type, int searchid) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (0 == type) {
                result.put("object", this.sendObjectRepository.findByGid(searchid));
            } else if (1 == type) {
                result.put("object", this.sendObjectRepository.findByOid(searchid));
            }
        } catch (Exception e) {
            logger.error("查询数据库出错");
        }
        return result;
    }

    // 选择对象发送
    public String send(int cid, int type, int sendid, int way, String content) {
        String result = null;
        try {
            if (0 == type) {
                List<SendObject> sendObjects = this.sendObjectRepository.findByGid(sendid);
                    //TODO 调用发送方法
                    if(1 == way) {
                        if(!this.outerInfService.sendWx(content, sendObjects)) {
                            result = "微信发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                    } else if(2 == way) {
                        if(!this.outerInfService.sendEmail(content, sendObjects)) {
                            result = "邮件发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                    } else if(3 == way) {
                        if(!this.outerInfService.sendSms(content, sendObjects)) {
                            result = "短信发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                    } else if(4 == way) {
                        if(!this.outerInfService.sendWx(content, sendObjects)) {
                            result = "微信发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                        if(!this.outerInfService.sendEmail(content, sendObjects)) {
                            result = "邮件发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                        if(!this.outerInfService.sendSms(content, sendObjects)) {
                            result = "短信发送错误";
                        } else {
                            for(SendObject sendObject : sendObjects) {
                                this.msgService.insertmsg(cid, content, sendObject.getOid(), way);
                            }
                        }
                    } else {
                        result = "发送方式错误";
                    }
            } else if(1 == type) {
                SendObject sendObject = this.sendObjectRepository.findByOid(sendid);
                List<SendObject> sendObjects = new ArrayList<>();
                sendObjects.add(sendObject);
              //TODO 调用发送方法
                if(1 == way) {
                    if(!this.outerInfService.sendWx(content, sendObjects)) {
                        result = "微信发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                } else if(2 == way) {
                    if(!this.outerInfService.sendEmail(content, sendObjects)) {
                        result = "邮件发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                } else if(3 == way) {
                    if(!this.outerInfService.sendSms(content, sendObjects)) {
                        result = "短信发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                } else if(4 == way) {
                    if(!this.outerInfService.sendWx(content, sendObjects)) {
                        result = "微信发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                    if(!this.outerInfService.sendEmail(content, sendObjects)) {
                        result = "邮件发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                    if(!this.outerInfService.sendSms(content, sendObjects)) {
                        result = "短信发送错误";
                    } else {
                        this.msgService.insertmsg(cid, content, sendid, way);
                    }
                } else {
                    result = "发送方式错误";
                }
            } else {
                result = "发送类别错误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "系统出错";
        }
        return result;
    }

//    public String sendown(String account, int way, String content) {
//        String result = null;
//        try {
//            if(1 == way) {
//                //TODO 调用微信发送
//                
//            } else if(2 == way) {
//                if(account.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
//                    //TODO 调用邮件发送方式
//                } else {
//                    result = "邮箱格式错误";
//                }
//            } else if(3 == way) {
//                if(account.matches("1[358][0-9]{9,9}")) {
//                    //TODO 调用短信发送方法
//                } else {
//                    result = "手机号格式错误";
//                }
//            } else {
//                result = "发送方式错误";
//            }
//        } catch (Exception e) {
//            result = "系统出错";
//        }
//        return result;
//    }
}
