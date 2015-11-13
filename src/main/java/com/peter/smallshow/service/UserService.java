package com.peter.smallshow.service;

import java.util.List;

import com.peter.smallshow.entity.Roles;
import com.peter.smallshow.entity.Users;


public interface UserService {
	public Users getUser(String userName);

	public List<Roles> getUserRoles(String userName);

	public List<Users> getAllUsers();
}
