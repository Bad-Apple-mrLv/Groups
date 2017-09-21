package com.j1091.service;

import com.j1091.pojo.User;

public interface IUserService {
	public User findByName(String username);
	public int insert(User user);
}
