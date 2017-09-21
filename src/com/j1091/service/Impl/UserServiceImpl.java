package com.j1091.service.Impl;

import com.j1091.dao.UserDao;
import com.j1091.pojo.User;
import com.j1091.service.IUserService;

public class UserServiceImpl implements IUserService {

	private UserDao dao = new UserDao();
	@Override
	public User findByName(String username) {
		return dao.findByName(username);
	}
	public int insert(User user) {
		return dao.insert(user);
	}
}
