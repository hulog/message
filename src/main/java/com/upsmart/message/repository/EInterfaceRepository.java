package com.upsmart.message.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upsmart.message.domain.EInterface;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author hulong
 * @version 0.0.1
 * @desc
 * @date 2016年10月18日
 */
@Repository
public interface EInterfaceRepository extends PagingAndSortingRepository<EInterface, Integer> {

    public EInterface findByInftype(int inftype);
}
