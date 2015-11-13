package com.peter.smallshow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping(value = "/error.do")
	public String error(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String errorMessage = "";
		Subject user = SecurityUtils.getSubject();
		boolean a = user.isAuthenticated();
		String userID = (String) user.getPrincipal();
		session.setAttribute("ErrorMessage", errorMessage);
		return "error";

	}
}
