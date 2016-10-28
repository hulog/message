package com.upsmart.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.Client;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author aidar
 * @version 0.0.1
 * @desc controller
 * @date 2016年10月17日 
 */
@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer>{

	public List<Client> findAll();

	@Query(value = "select * from client c where c.cid=?1", nativeQuery=true)
	public Client findByCid(int cid);
	
    // 按账号和密码查找
    public Client findByCnameAndCpassword(String cname,String cpassword);
}
