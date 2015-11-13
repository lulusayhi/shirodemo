package com.peter.smallshow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_to_role")
public class UserToRole {
	@Id
	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "role_id")
	private String roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserToRole [userId=" + userId + ", roleId=" + roleId + "]";
	}

	

	
}
