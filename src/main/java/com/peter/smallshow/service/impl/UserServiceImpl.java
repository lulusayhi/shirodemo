package com.peter.smallshow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.smallshow.dao.UserDao;
import com.peter.smallshow.dao.exception.DataAccessException;
import com.peter.smallshow.entity.Roles;
import com.peter.smallshow.entity.Users;
import com.peter.smallshow.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public Users getUser(String userName) {
		Users users = null;
		try {
			users = userDao.searchUser(userName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<Roles> getUserRoles(String userName) {
		List<Roles> roles = new ArrayList<Roles>();
		try {
			roles = userDao.searchRoles(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<Users>();
		try {
			users = userDao.searchAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	
}
