package com.peter.smallshow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.peter.smallshow.dao.UserDao;
import com.peter.smallshow.dao.exception.DataAccessException;
import com.peter.smallshow.entity.Roles;
import com.peter.smallshow.entity.UserToRole;
import com.peter.smallshow.entity.Users;

@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	public Users searchUser(String userName) throws DataAccessException{
		Users users = null;
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Users a where a.name=:name").setString(
					"name", userName);
			users = (Users) query.uniqueResult();
			session.close();
		} catch (Throwable e) {
			throw new DataAccessException(e);
		}
		return users;
	
	}

	public List<Roles> searchRoles(String userName) {
		List<Roles> roles = new ArrayList<Roles>();
		Users users = null;
		UserToRole userToRole = null;
		try {
			Session session = sessionFactory.openSession();
			Query query1 = session.createQuery("from Users a where a.name=:name").setString(
					"name", userName);
			users = (Users) query1.uniqueResult();
			String userId = String.valueOf(users.getId());
			Query query2 = session.createQuery("from UserToRole a where a.userId=:userId").setString(
					"userId", userId);
			userToRole = (UserToRole) query2.uniqueResult();
			String roleId = String.valueOf(userToRole.getRoleId());
			Query query3 = session.createQuery("from Roles a where a.id=:roleId").setString(
					"roleId", roleId);
			roles = query3.list();
			
			session.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return roles;
	}

	public List<Users> searchAllUsers() {
		List<Users> users = new ArrayList<Users>();
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Users");
			users = query.list();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
