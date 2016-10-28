package com.upsmart.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.Msg;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc 
 * @date 2016年10月18日
 */

@Repository
public interface MsgRepository extends PagingAndSortingRepository<Msg , Integer >{
	
	List<Msg> findAll();
	
	@Query(value = "select * from msg m where m.mid=?1", nativeQuery=true)
	Msg findByMid(int mid);
	
	List<Msg> findByCid(int cid);
	
}