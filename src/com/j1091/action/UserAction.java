package com.j1091.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j1091.controller.IAction;
import com.j1091.pojo.User;
import com.j1091.service.IUserService;
import com.j1091.service.Impl.UserServiceImpl;

public class UserAction implements IAction {

	private String username;
	private String userpwd;
	private User user = new User();
	
	private IUserService userService = new UserServiceImpl();

	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return "index.jsp";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入登录方法：");
		System.out.println("用户名：" + username + "密码：" + userpwd);
		User user = userService.findByName(username);
		if(user == null) {
			request.setAttribute("msg", "用户名不存在");
			return "login.jsp";
		} 
		if(!userpwd.equals(user.getUserpwd())) {
			request.setAttribute("msg", "用户密码错误");
			return "login.jsp";
		}
		request.getSession().setAttribute("user", user);
		return "@red_goods.action";
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入注销方法");
		request.getSession().removeAttribute("user");
		return "@red_goods.action";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入注册方法");

		if(userService.findByName(user.getUsername()) != null) {
			request.setAttribute("msg", "用户名已存在");
			return "regist.jsp";
		}
		user.setRegtime(new Date());
		int row = userService.insert(user);
		if(row==0) {
			request.setAttribute("msg", "注册失败");
			return "regist.jsp";
		}
		System.out.println("注册成功");
		return "login.jsp";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
