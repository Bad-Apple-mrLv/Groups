package com.j1091.service.Impl;

import java.util.List;

import com.j1091.dao.GoodsTypeDao;
import com.j1091.pojo.GoodsType;
import com.j1091.service.IGoodsTypeService;

public class GoodsTypeServiceImpl implements IGoodsTypeService {

	private GoodsTypeDao dao = new GoodsTypeDao();
	
	@Override
	public List<GoodsType> findAll() {
		return dao.findAll();
	}
}
