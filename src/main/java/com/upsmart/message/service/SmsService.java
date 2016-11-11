package com.upsmart.message.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.upsmart.message.constant.GlobalConstants;
import com.upsmart.message.domain.EInterface;
import com.upsmart.message.domain.SendObject;
import com.upsmart.message.repository.EInterfaceRepository;

import net.sf.json.JSONObject;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc
 * @date 2016年10月19日
 */
@Service
public class SmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private EInterfaceRepository eInterfaceRepository;

    public boolean sendSms(String content, List<SendObject> userlist) {
        EInterface intf = this.eInterfaceRepository.findByInftype(GlobalConstants.SMS_TYPE_IN_DATABASE);
        String url = intf.getInfurl();// http://gw.api.taobao.com/router/rest
        String appkey = intf.getInfname();// 23486620
        String secret = intf.getInfpassword();// 128dd6fde0561a4d4b94772f3aa3595d
        // String to = listToStringWithSeparator(userlist,
        // GlobalConstants.COMMA);

        // 调用淘宝官方SDK
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName("银联智慧-无锡");
        req.setSmsTemplateCode("SMS_18680562");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        // 短信模板参数长度不能超过15
        String sendContent = content.length() > 15 ? content.substring(0, 15) : content;
        // TODO 最好调用一次接口，完成所有发送
        // 群发时，短信内容中不能实现姓名显示，因此循环发送
        for (SendObject to : userlist) {
            req.setSmsParamString("{name:'" + to.getOname() + "',content:'" + sendContent + "'}");
            req.setRecNum(to.getMessage());
            try {
                rsp = client.execute(req);
                JSONObject json = JSONObject.fromObject(rsp.getBody());
                if (null == json.getJSONObject("alibaba_aliqin_fc_sms_num_send_response")) {
                    logger.error("调用第三方短信接口失败");
                    return false;
                }
            } catch (ApiException e) {
                logger.error("短信发送失败，报ApiException异常");
                e.printStackTrace();
                return false;
            }
        }
        logger.info("所有短信发送成功");
        return true;
    }
}
