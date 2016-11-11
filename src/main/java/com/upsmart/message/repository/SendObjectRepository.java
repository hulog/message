package com.upsmart.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.SendObject;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author Buby
 * @version 0.0.1
 * @desc
 * @date 2016年10月17日
 */

@Repository
public interface SendObjectRepository extends PagingAndSortingRepository<SendObject, Integer> {

    List<SendObject> findAll();

    @Modifying
    @Query(value = "select o.* from object o  join objectgroup og on o.oid=og.oid where og.gid=?1", nativeQuery = true)
    public List<SendObject> findByGid(int gid);

    @Query(value = "select * from object so where so.oid=?1", nativeQuery = true)
    SendObject findByOid(int oid);

    @Query(value = "select * from object so where so.oname=?1", nativeQuery = true)
    SendObject findByName(String oname);

    @Query(value = "select * from object so where so.brand=?1", nativeQuery = true)
    List<SendObject> findByBrand(String brand);
}
