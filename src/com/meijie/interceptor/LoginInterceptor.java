package com.meijie.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.meijie.entity.SysUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 用于登录验证
 * @author Administrator
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		SysUser user = (SysUser) session.getAttribute("user");
		if(user != null) {
			return invo.invoke();
		} else {
			return "login";
		}
	}
}
