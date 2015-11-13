package com.peter.smallshow.controller.bean;

public class PasswordAuthcInfo {
	private String loginName;

	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return this.loginName + "," + this.password;
	}
}
