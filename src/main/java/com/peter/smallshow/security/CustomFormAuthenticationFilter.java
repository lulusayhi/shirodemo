package com.peter.smallshow.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = false;
		String host = getHost(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);

	}

}
