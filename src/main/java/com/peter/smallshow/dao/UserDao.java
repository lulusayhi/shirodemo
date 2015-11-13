package com.peter.smallshow.dao;

import java.util.List;

import com.peter.smallshow.dao.exception.DataAccessException;
import com.peter.smallshow.entity.Roles;
import com.peter.smallshow.entity.Users;

public interface UserDao {

	public Users searchUser(String userName) throws DataAccessException;

	public List<Roles> searchRoles(String userName);

	public List<Users> searchAllUsers();;
}
