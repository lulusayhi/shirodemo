package com.peter.smallshow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "passwd")
	private String passwd;
	
	@Column(name = "real_name")
	private String realName;
	
	@Column(name = "sex")
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", passwd=" + passwd
				+ ", real_name=" + realName + ", sex=" + sex + "]";
	}
	
	
}
