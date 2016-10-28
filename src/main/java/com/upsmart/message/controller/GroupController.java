package com.upsmart.message.controller;

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

import com.upsmart.message.domain.ObjectGroup;
import com.upsmart.message.domain.SendObject;
import com.upsmart.message.msg.BaseMessage;
import com.upsmart.message.msg.StatusCode;
import com.upsmart.message.repository.GroupRepository;
import com.upsmart.message.repository.SendObjectRepository;
import com.upsmart.message.service.GroupService;
import com.upsmart.message.util.ResponseUtil;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc 
 * @date 2016年10月18日
 */

@Controller
@RequestMapping("/group")
public class GroupController{
	
	private static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired 
	private GroupService groupService;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private SendObjectRepository sendObjectRepository; 
	
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage query() {
        BaseMessage msg = new BaseMessage();
        try {
            ResponseUtil.buildResMsg(msg, StatusCode.SUCCESS);
            msg.setData(this.groupService.queryGroup());
        } catch (Exception e) {
            logger.error("查询用户组别信息异常");
            ResponseUtil.buildResMsg(msg, StatusCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return msg;
    }
	
	@RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage returnData(){
		BaseMessage msg = new BaseMessage();
		List<Map<String , Object>> resultobjgrp = new ArrayList<Map<String , Object>>();
		List<Map<String , Object>> resultobj = new ArrayList<Map<String , Object>>();
		List<ObjectGroup> objectgroup = this.groupService.findAllgroupobject();
		List<SendObject> sendObject = this.sendObjectRepository.findAll();
		Map<String , Object> result = new HashMap<String , Object>();
		try {
			for(int i = 0 ; i < objectgroup.size() ; i++){
				Map<String , Object> objgrp = new HashMap<String , Object>();
				objgrp.put("id", objectgroup.get(i).getId());
				objgrp.put("gid", objectgroup.get(i).getGid());
				objgrp.put("oid", objectgroup.get(i).getOid());
				objgrp.put("gname", this.groupRepository.findByGid(objectgroup.get(i).getGid()).getGname());
				objgrp.put("oname", this.sendObjectRepository.findByOid(objectgroup.get(i).getOid()).getOname());
				objgrp.put("brand", this.sendObjectRepository.findByOid(objectgroup.get(i).getOid()).getBrand());
				objgrp.put("wechat", this.sendObjectRepository.findByOid(objectgroup.get(i).getOid()).getWchat());
				objgrp.put("email", this.sendObjectRepository.findByOid(objectgroup.get(i).getOid()).getEmail());
				objgrp.put("message", this.sendObjectRepository.findByOid(objectgroup.get(i).getOid()).getMessage());
				resultobjgrp.add(objgrp);
			}
			for(int i = 0 ; i < sendObject.size() ; i++){
				Map<String , Object> sdobj = new HashMap<String , Object>();
				sdobj.put("oid", sendObject.get(i).getOid());
				sdobj.put("oname", sendObject.get(i).getOname());
				sdobj.put("brand", sendObject.get(i).getBrand());
				sdobj.put("wechat", sendObject.get(i).getWchat());
				sdobj.put("email", sendObject.get(i).getEmail());
				sdobj.put("message", sendObject.get(i).getMessage());
				resultobj.add(sdobj);
			}
			result.put("resultobjgrp", resultobjgrp);
			result.put("resultobj", resultobj);
			msg.setData(result);
		} catch (Exception e) {
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to return group");
		}
		return msg;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage addGroup(@RequestParam(value="gname",required=false) String gname,
    		@RequestParam(value="oid",required=false) List<String> oidstr){
		BaseMessage msg = new BaseMessage();
		List<Integer> oid = new ArrayList<Integer>();
		for(int i=0 ; i<oidstr.size() ; i++){
			oid.add(Integer.parseInt(oidstr.get(i)));
		}
		try{
			this.groupService.addGroup(gname , oid);
			msg.setData("success");
		}catch(Exception e){
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to add group");
		}
		return msg;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public BaseMessage deleteGroup(@RequestParam(value="gidlist",required=false) List<Integer> gid){
		BaseMessage msg = new BaseMessage();
		try{
			for(int i = 0 ; i < gid.size() ; i++){
				this.groupService.deleteGroup((int)gid.get(i));
			}
			msg.setData("success");
		}catch(Exception e){
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to delete group");
		}
		return msg;
	}
	
	@RequestMapping(value = "mod", method = RequestMethod.POST)
	@ResponseBody
	public BaseMessage modGroup(@RequestParam(value="gid",required=false) String gidstr,
			@RequestParam(value="gname",required=false) String gname,
    		@RequestParam(value="oid",required=false) List<String> oidstr){
		BaseMessage msg = new BaseMessage();
		int gid = Integer.parseInt(gidstr);
		List<Integer> oid = new ArrayList<Integer>();
		for(int i=0 ; i<oidstr.size() ; i++){
			oid.add(Integer.parseInt(oidstr.get(i)));
		}
		try{
			this.groupService.modGroup(gid , gname , oid);
			msg.setData("success");
		}catch(Exception e){
			msg.setData("fail");
			e.printStackTrace();
			logger.error("fail to mod group");
		}
		return msg;
	}
}