package com.zhenzhigu.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	protected HttpServletRequest request;
	protected HttpServletResponse  response;
	protected HttpSession session;
	protected ServletContext application;
	protected String path;
	protected String basePath;
	protected PrintWriter out;
	
	public Map<String, Object> map = new HashMap<String, Object>();
	public int page;	//查第几页
	public int rows;	//每页多少条
	public File file;	//接收一个文件
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		try {
			this.out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();
		this.application = this.request.getServletContext();
		this.path = this.request.getContextPath();
		this.basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	}
	
	public void printJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}
	/* -------- 页面跳转 --------- */
	protected void redirect(String url) throws IOException{
		response.sendRedirect(url);
	}
	protected void forward(String url) throws IOException, ServletException{
		request.getRequestDispatcher(url).forward(request, response);
	}
}
