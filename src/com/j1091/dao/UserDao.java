package com.j1091.dao;


import com.j1091.pojo.User;

public class UserDao extends BaseC3p0PoolDao {

	public User findByName(String username) {
		return (User) super.findOne("select * from t_user where username = ?", username);
	}
	
}
