package com.upsmart.message.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsmart.message.domain.Msg;
import com.upsmart.message.repository.MsgRepository;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author buby
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月18日
 */

@Service
public class ManageMsgServiceImp {
	
	private static Logger logger = LoggerFactory.getLogger(ManageMsgServiceImp.class);
	
	@Autowired
	private MsgRepository msgRepository;
	
	public List<Msg> findAll(){
		List<Msg> msg = new ArrayList<Msg>();
		try {
			msg = this.msgRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("find msg fail");
		}
		return msg;
	}
	
	public void deleteMsg(int mid){
		try {
			this.msgRepository.delete(this.msgRepository.findByMid(mid));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("delete msg fail");
		}
	}
}