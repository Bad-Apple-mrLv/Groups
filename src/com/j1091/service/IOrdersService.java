package com.j1091.service;

import java.util.List;

import com.j1091.pojo.Orders;

public interface IOrdersService {
	public int insert(Orders order);
	public List<Orders> findAll();
}
