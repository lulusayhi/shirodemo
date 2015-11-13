package com.peter.smallshow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.peter.smallshow.controller.bean.PasswordAuthcInfo;

@Controller
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String isLogin(HttpServletRequest request, PasswordAuthcInfo passwordAuthcInfo) {
		String loginName = passwordAuthcInfo.getLoginName();
		String loginPassword = passwordAuthcInfo.getPassword();
		LOGGER.info("loginName:" + loginName + ";loginPassword:" + loginPassword);

		HttpSession session = request.getSession(true);
		String errorMessage = "";

		Subject user = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
		token.setRememberMe(true);

		try {
			user.login(token);
			String userID = (String) user.getPrincipal();
			LOGGER.info("User [" + userID + "] logged in successfully.");

			session.setAttribute("USERNAME", userID);

			return "success";
		} catch (UnknownAccountException uae) {
			errorMessage = "用户认证失败：" + "username wasn't in the system.";
			LOGGER.info(errorMessage);
		} catch (IncorrectCredentialsException ice) {
			errorMessage = "用户认证失败：" + "password didn't match.";
			LOGGER.info(errorMessage);
		} catch (LockedAccountException lae) {
			errorMessage = "用户认证失败：" + "account for that username is locked - can't login.";
			LOGGER.info(errorMessage);
		} catch (AuthenticationException e) {
			errorMessage = "登录失败错误信息：" + e;
			LOGGER.error(errorMessage);
			e.printStackTrace();
			token.clear();
		}

	       
		
		session.setAttribute("ErrorMessage", errorMessage);
		return "error";
	}
}
