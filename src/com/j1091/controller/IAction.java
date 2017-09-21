package com.j1091.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
	// 默认处理请求的逻辑方法 
	//String[1.默认转发（index.jsp）,2.重定向(@red_index.jsp),3. ajax: @json_{sfsdf}]
	String execute(HttpServletRequest request, HttpServletResponse response);
	
}
