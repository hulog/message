package com.upsmart.message.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.message.domain.Msg;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.repository.ClientRepository;
import com.upsmart.message.repository.SendObjectRepository;
import com.upsmart.message.service.ManageMsgServiceImp;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc 
 * @date 2016年10月18日
 */

@Controller
@RequestMapping("/msg")
public class ManageMsgController {
	
	private static Logger logger = LoggerFactory.getLogger(ManageMsgController.class);
	
	@Autowired
	private ManageMsgServiceImp msgServiceImp;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private SendObjectRepository sendObjectRepository;
	
	@RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage returnData() {
		BaseMessage msg = new BaseMessage();
		List<Map<String , Object>> result = new ArrayList<Map<String , Object>>();
		List<Msg> message = this.msgServiceImp.findAll();
		try {
			for(int i = message.size()-1 ; i >= 0 ; i--){
				Map<String , Object> msgmap = new HashMap<String , Object>();
				msgmap.put("mid" , message.get(i).getMid());
				msgmap.put("cid", message.get(i).getCid());
				msgmap.put("cname", this.clientRepository.findByCid(message.get(i).getCid()).getCname());
				msgmap.put("oid", message.get(i).getOid());
				msgmap.put("oname", this.sendObjectRepository.findByOid(message.get(i).getOid()).getOname());
				msgmap.put("content", message.get(i).getMcontent());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
				String sendtime = dateFormat.format(message.get(i).getSendtime());
				msgmap.put("sendtime", sendtime);
				String sendway="";
				if(message.get(i).getSendway()==1){
					sendway="微信";
				}else if(message.get(i).getSendway()==2){
					sendway="邮箱";
				}else if(message.get(i).getSendway()==3){
					sendway="短信";
				}
				msgmap.put("sendway", sendway);
				result.add(msgmap);
			}
			msg.setData(result);
		} catch (Exception e) {
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to return msg");
		}
		return msg;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public BaseMessage deleteMsg(@RequestParam(value="midlist",required=false) List<Integer> mid) {
		BaseMessage msg = new BaseMessage();
		try {
			for(int i = 0 ; i < mid.size() ; i++){
				this.msgServiceImp.deleteMsg(mid.get(i).intValue());
			}
			msg.setData("success");
		} catch (Exception e) {
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to delete msg");
		}
		return msg;
	}
}