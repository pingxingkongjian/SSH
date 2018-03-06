package com.zhenzhigu.mvc.service.impl;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhenzhigu.common.persistence.BaseDAO;
import com.zhenzhigu.common.service.impl.BaseServiceImpl;
import com.zhenzhigu.mvc.entity.User;
import com.zhenzhigu.mvc.service.UserService;
import com.zhenzhigu.mvc.util.MD5Util;
/**
 * 业务逻辑层
 * @author Master.Xia
 *
 */
@Service
//@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired BaseDAO dao;

	@Override
	public void save(User user) throws Exception {

		//保存用户之前对密码进行加密
		String password = user.getPassword();
		password = MD5Util.md5(password); //1.对明文密码进行MD5加密
		user.setSalt(UUID.randomUUID().toString());//2.给用户随机生成一个盐值
		password=MD5Util.md5(password+user.getSalt());//3.密文密码连接salt再次MD5
		user.setPassword(password);
		dao.save(user);
	
		//结算=开事务+减库存+减用户余额+加商家余额+创建订单+生成物流+提交事务+无论如何释放资源
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return dao.findOne("from User where username=?", username);
	}

	@Override
	public void changeAvatar(HttpServletRequest request, MultipartFile file) throws Exception {

		//1.当前登录用户
		User loginUser = (User) request.getSession().getAttribute("LoginUser");
		User user = dao.findById(User.class,loginUser.getId());
		//2.创建本地文件
		String real = request.getSession().getServletContext().getRealPath("/upload/avatar");
		File local = new File(real,UUID.randomUUID().toString()+".jpg");
		//3.文件复制
		file.transferTo(local);
		//4.更新数据库信息
		user.setAvatar(local.getName());
		dao.update(user);
		//5.更新登录信息
		request.getSession().setAttribute("LoginUser",user);
		//throw new RuntimeException(); //会触发hibernate回滚
		
	}

	@Override
	public void changePassword(String loginUserId,String oldPwd, String newPwd) throws Exception {
		
		User user = dao.findById(User.class,loginUserId);
		//旧密码是否正确
		if(MD5Util.md5(MD5Util.md5(oldPwd)+user.getSalt()).equals(user.getPassword())) {
			user.setSalt(UUID.randomUUID().toString());
			user.setPassword(MD5Util.md5(MD5Util.md5(newPwd)+user.getSalt()));
			dao.update(user);
		}else {
			throw new RuntimeException("OldPwdErr");
		}
	}

}
