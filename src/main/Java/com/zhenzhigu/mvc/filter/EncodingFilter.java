package com.zhenzhigu.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String charset = "UTF-8";
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) arg1;
		arg0.setCharacterEncoding(charset);
		arg1.setCharacterEncoding(charset);
		response.setHeader("charset", charset);
		arg2.doFilter(arg0, arg1);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String a = filterConfig.getInitParameter("encoding");
		if(a!=null) {
			this.charset = a;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
