package com.upsmart.message.repository;

import com.upsmart.message.domain.ObjectGroup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc 
 * @date 2016年10月17日
 */

@Repository
public interface ObjectGroupRepository extends PagingAndSortingRepository<ObjectGroup , Integer > {
	
	List<ObjectGroup> findAll();
	
	List<ObjectGroup> findByGid(int gid);
	
	List<ObjectGroup> findByOid(int oid);
}