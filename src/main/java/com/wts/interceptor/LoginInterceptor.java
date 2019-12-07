package com.wts.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpSession;

/**
 * LoginInterceptor class
 *
 * @author wts
 * @date 2019/12/06
 */
public class LoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		HttpSession session = inv.getController().getSession();
		if(session.getAttribute("user") == null){
			inv.getController().redirect("/");
		}
		else{
			inv.invoke();
		}
	}
}