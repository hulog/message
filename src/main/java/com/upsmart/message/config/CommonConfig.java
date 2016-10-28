package com.upsmart.message.config;

import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 项目中用到的共用配置
 * @date 4/8/16
 */
@Component
public class CommonConfig {

	public String getMailSavePath() {
		return "/home/upsmart/workspace/eclipse_workspace/bookshare-demo.git/src/main/resources/properties/";
	}
}
