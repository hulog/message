package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.ObjectGroup;
import com.upsmart.message.domain.SendObject;
import com.upsmart.message.repository.ObjectGroupRepository;
import com.upsmart.message.repository.SendObjectRepository;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author buby
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月18日
 */

@Service
public class SetSendObjectServiceImp implements SetSendObjectService {
	
	private static Logger logger = LoggerFactory.getLogger(SetSendObjectServiceImp.class);

	@Autowired
	private SendObjectRepository sendObjectRepository;
	
	@Autowired 
	private ObjectGroupRepository objectGroupRepository;
	
	public List<SendObject> findAllObject(){
		List<SendObject> sendObject = new ArrayList<SendObject>();
		try{
			sendObject = this.sendObjectRepository.findAll();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("return object fail");
		}
		return sendObject;
	}
	
	public void addObject(String oname , String brand , String wechat ,
		String email , String message){
		SendObject obj = new SendObject();
		obj.setOname(oname);
		obj.setBrand(brand);
		obj.setWchat(wechat);
		obj.setEmail(email);
		obj.setMessage(message);
		Date date = new Date();
		obj.setModtime(date);
		obj.setCreatetime(date);
		try{
			this.sendObjectRepository.save(obj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("add object fail");
		}
	}
	
	public void deleteObject(int oid){
		List<ObjectGroup> objgrp = this.objectGroupRepository.findByOid(oid);
		try{
			for(int i=0 ; i<objgrp.size();i++){
				ObjectGroup obj = new ObjectGroup();
				obj = objgrp.get(i);
				this.objectGroupRepository.delete(obj);
			}
			this.sendObjectRepository.delete(this.sendObjectRepository.findByOid(oid));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("delete object fail");
		}
	}
	
	public void modObject(int oid , String oname , String brand , 
			String wechat , String email , String message){
		SendObject obj = this.sendObjectRepository.findByOid(oid);
		obj.setOname(oname);
		obj.setBrand(brand);
		obj.setWchat(wechat);
		obj.setEmail(email);
		obj.setMessage(message);
		Date date = new Date();
		obj.setModtime(date);
		try{
			this.sendObjectRepository.save(obj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("mod object fail");
		}
	}
}