package com.zhenzhigu.mvc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zhenzhigu.common.service.BaseService;
import com.zhenzhigu.mvc.entity.User;

public interface UserService extends BaseService<User> {
	
	void save(User user) throws Exception;
	
	User findByUsername(String username)throws Exception;
	
	void changeAvatar(HttpServletRequest request, MultipartFile file) throws Exception;
	
	void changePassword(String loginUserId, String oldPwd, String newPwd)throws Exception;
	
}
