package com.upsmart.message.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.Group;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc 
 * @date 2016年10月17日
 */

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group , Integer > {
	
	List<Group> findAll(); 
	
	Group findByGid(int gid);
	
	@Query(value = "select * from ogroup g where g.gname=?1", nativeQuery=true)
	Group findByGname(String gname);
	
}