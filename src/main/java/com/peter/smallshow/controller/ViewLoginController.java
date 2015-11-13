package com.peter.smallshow.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewLoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewLoginController.class);

	@RequestMapping(value = "/index.do")
	public String viewLogin(HttpServletRequest request, ModelMap model) {
		String ip = request.getRemoteAddr();
		LOGGER.info("ip:" + ip);

		return "login";
	}
}
