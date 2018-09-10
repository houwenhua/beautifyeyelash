package com.meijie.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meijie.entity.SysUser;

public class LoginFilter implements Filter {

	private static String LOGIN_PAGE = "/index.jsp";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String currentUrl = req.getServletPath();

		HttpSession session = req.getSession();

		System.out.println("登录过滤器LoginFilter【检查是否登录,如果没登录则重定向到index.jsp】");
		if(currentUrl.startsWith("/display.jsp")){
			chain.doFilter(request, response);
			return;
		}

		if (currentUrl.equals("")) {
			currentUrl = currentUrl + "/";
			
		} else if ((currentUrl.startsWith("/")) && (!currentUrl.startsWith(LOGIN_PAGE))) {
			SysUser user = (SysUser) session.getAttribute("user");
			if (user == null) {
				res.sendRedirect(req.getContextPath() + LOGIN_PAGE);

				return;
			}
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
