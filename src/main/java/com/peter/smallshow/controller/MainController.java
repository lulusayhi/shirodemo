package com.peter.smallshow.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.peter.smallshow.entity.Users;
import com.peter.smallshow.service.UserService;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);  
	  
	@Resource(name = "userService")  
    private UserService userService;  
  
    @RequestMapping(value = "/main.do", method = RequestMethod.GET)  
    public String mainPage(HttpServletRequest request, ModelMap model) {  
        HttpSession session = request.getSession(true);  
        Subject user = SecurityUtils.getSubject();  
        String userID = (String) user.getPrincipal();  
        LOGGER.info(userID);  
        session.setAttribute("USERNAME", userID);  
        List<Users> users = userService.getAllUsers();  
		if (user.hasRole("administrator")) {
 	   System.out.println("用户角色匹配");
 	   LOGGER.info("May the administrator be with you!");
    } else {
 	   System.out.println("用户角色不匹配");
 	   LOGGER.info("Hello, mere mortal.");
    }
        model.addAttribute("users", users);  
        return "main";  
  
    }  
}  
