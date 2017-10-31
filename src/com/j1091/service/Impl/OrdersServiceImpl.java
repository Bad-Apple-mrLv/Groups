package com.j1091.service.Impl;

import java.util.List;

import com.j1091.dao.OrdersDao;
import com.j1091.pojo.Orders;
import com.j1091.service.IOrdersService;

public class OrdersServiceImpl implements IOrdersService {
	
	private OrdersDao dao = new OrdersDao();

	@Override
	public int insert(Orders order) {
		return dao.insert(order);
	}

	@Override
	public List<Orders> findAll() {
		return dao.findAll();
	}

}
