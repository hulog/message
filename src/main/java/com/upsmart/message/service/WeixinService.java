package com.upsmart.message.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @date 2016年10月18日
 */
@Service
public class WeixinService {
    
    @Autowired
    private EInterfaceRepository eInterfaceRepository;
    
    private static final String separator = "|";
    private static Logger logger = LoggerFactory.getLogger(WeixinService.class);
    private String soapRequestOfSaml = "";
    private String soapResponseData = "";
    
            //"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + CorpID + "&corpsecret="+ Secret;

    private String getAccessToken() {
        EInterface eInterface = this.eInterfaceRepository.findByInftype(GlobalConstants.WEIXIN_TYPE_IN_DATABASE);
        
        String CorpID = eInterface.getInfname();//"wx60796de01f1227b0";
        String Secret = eInterface.getInfpassword();//"GtoFvR8BDGY5PGCn0YoVRxSZC44n089AZQNJG5zHfy7QGjdD-brd5iR-o_yj8YwQ";
        String url = eInterface.getInfurl()+"gettoken?corpid=" + CorpID + "&corpsecret="+Secret;
        PostMethod postmethod = new PostMethod(url);
        HttpClient httpClient = new HttpClient();
        int statusCode;
        try {
            statusCode = httpClient.executeMethod(postmethod);
            logger.info("获取微信token时，statusCode:"+statusCode);
        } catch (HttpException e) {
        	logger.error("获取微信token时，报HttpException异常");
            e.printStackTrace();
        } catch (IOException e) {
        	logger.error("获取微信token时，报IOException异常");
            e.printStackTrace();
        }

        try {
            soapResponseData = postmethod.getResponseBodyAsString();
        } catch (IOException e) {
        	logger.error("获取微信token时，响应数据获取失败");
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(soapResponseData);
        logger.info("微信token获取成功");
        return jsonObject.getString("access_token");
    }

    private void msgTmplBulid(String wxlist,String content){
        
        this.soapRequestOfSaml = "" 
                + "{                                                                   "
                + "   \"touser\": \""+wxlist+"\",                 "
                + "   \"toparty\": \"@all\",                          "
                + "   \"totag\": \"@all\",                              "
                + "   \"msgtype\": \"text\",                        "
                + "   \"agentid\": \"1\",                                "
                + "   \"text\": {                                               "
                + "       \"content\": \""+content+"\"       "
                + "   },                                                                "
                + "   \"safe\":\"0\"                                          "
                + "}                                                                    ";
    }
    
    private boolean send(){
        try {
//            String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
            String url = this.eInterfaceRepository.findByInftype(GlobalConstants.WEIXIN_TYPE_IN_DATABASE).getInfurl();
            url = url+"message/send?access_token="+ this.getAccessToken();
            PostMethod postmethod = new PostMethod(url);
            byte[] b = this.soapRequestOfSaml.getBytes("UTF-8");
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    "application/xop+xml; charset=UTF-8; type=\"text/xml\"");
            postmethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postmethod);
            logger.info("微信发送信息时，statusCode:"+statusCode);
            String soapResponseData = postmethod.getResponseBodyAsString();
            logger.info("微信发送信息成功，第三方微信接口返回信息:"+soapResponseData);
            return true;
        } catch (Exception e) {
            logger.error("微信发送信息时异常");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * @param userlist 发送对象列表
     * @param content 发送内容
     * @return 发送是否成功
     */
    public boolean sendMsgToUsers(List<SendObject> userlist, String content) {
        
        //提取发送对象的微信id，按官方API定义格式化连接各个发送对象ID
        StringBuilder sb = new StringBuilder();
        for(SendObject usr : userlist){
            sb.append(usr.getWchat().trim()).append(separator);
        }
        sb.deleteCharAt(sb.length()-1);
        this.msgTmplBulid(sb.toString(), content);
        return this.send();
    }
}
