package com.upsmart.message.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.Admin;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author 
 * @version 0.0.1
 * @desc TODO
 * @date 2016年10月20日
 */
@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, String>{
    public Admin findByAnameAndApassword(String aname, String apassword);
}
