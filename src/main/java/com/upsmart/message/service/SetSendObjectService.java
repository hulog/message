package com.upsmart.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.SendObject;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author buby
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月18日
 */

@Service
public interface SetSendObjectService {
	
	public List<SendObject> findAllObject();
	
	public void addObject(String oname , String brand , 
			String wechat , String email , String message);
	
	public void deleteObject(int oid);
	
	public void modObject(int oid , String oname , String brand , 
			String wechat , String email , String message);
}