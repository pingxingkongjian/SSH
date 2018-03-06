package com.zhenzhigu.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="mvc_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {
	@Column(unique=true)
	private String username;	//登录账号
	private String password; 	//登录密码，入库加密
	private String realname;	//真实姓名
	private int age;			//年龄
	private String salt;		//密码加盐
	private String avatar="default.jpg";		//头像路径
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public User(String username, String password, String realname, int age) {
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.age = age;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
}
