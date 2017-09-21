package com.j1091.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import com.j1091.dao.GoodsTypeDao;
import com.j1091.pojo.GoodsType;
import com.j1091.service.IGoodsTypeService;
import com.j1091.service.Impl.GoodsTypeServiceImpl;


public class InnerServlet extends HttpServlet{
	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
	@Override
	public void init() throws ServletException {
		List<GoodsType> types = goodsTypeService.findAll();
		ServletContext application = this.getServletContext();
		application.setAttribute("types", types);
		System.out.println("初始化成功");
		super.init();
	}
}
