package com.zhenzhigu.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhenzhigu.mvc.entity.User;
/**
 * 
 * @author Master.Xia
 *
 */
public class LoginFilter implements Filter {
	
	private String loginUrl = "/";

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		User user = (User) request.getSession().getAttribute("LoginUser");
		if(user==null) {
			response.sendRedirect(request.getContextPath()+this.loginUrl);
		}else {
			arg2.doFilter(arg0, arg1);
		}
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.loginUrl = filterConfig.getInitParameter("loginUrl");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
