package com.upsmart.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.upsmart.message.domain.Group;
import com.upsmart.message.domain.ObjectGroup;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author buby
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月17日
 */

@Service
public interface GroupService {
    
    public List<Map<String, Object>> queryGroup();
	
	public List<Group>findAllgroup();
	
	public List<ObjectGroup>findAllgroupobject();
	
	public void addGroup(String gname , List<Integer> oid);
	
	public void deleteGroup(int gid);
	
	public void modGroup(int gid , String gname , List<Integer> oid);
	
	public boolean check(String gname);
}