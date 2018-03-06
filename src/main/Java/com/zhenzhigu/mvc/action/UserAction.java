package com.zhenzhigu.mvc.action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhenzhigu.common.BaseAction;
import com.zhenzhigu.common.service.CommonService;

@ParentPackage("app")
public class UserAction extends BaseAction { //原本struts2自己创建，Action对象托管给Spring
	
	//因为action对象已经托管给spring，因此spring可以进行依赖注入
	@Autowired CommonService service;
	
	@Override
	public String execute() throws Exception {
		//检验整合是否成功的办法：看是否可以自动注入
		System.out.println(service);
		return null;
	}
}
