package com.j1091.service.Impl;

import java.util.List;

import com.j1091.dao.GoodsDao;
import com.j1091.pojo.Goods;
import com.j1091.service.IGoodsService;

public class GoodsServiceImpl implements IGoodsService {
	
	private GoodsDao dao = new GoodsDao();
	
	//每页显示数据
	private int pageSize = 8;
	
	//总页数
	private int pageSum = 0;

	@Override
	public int findCount(int typeid) {
		//获取总数据数
		int count = dao.findCount(typeid);
		if(count%pageSize == 0) {
			pageSum = count/pageSize;
		} else {
			pageSum = count/pageSize+1;
		}
		return pageSum;
	}

	@Override
	public List<Goods> findBypage(int pageCurr, int typeid) {
		if(typeid == 0) {
			return dao.findBypage((pageCurr-1)*pageSize, pageSize);
		}
		return dao.findBypage((pageCurr-1)*pageSize, pageSize, typeid);
	}

	@Override
	public Goods findById(int id) {
		return dao.findById(id);
	}

	@Override
	public int insert(Goods good) {
		return dao.insert(good);
	}

	@Override
	public int update(Goods good) {
		return dao.update(good);
	}

	@Override
	public int findTuangoucountById(int goodId) {
		return dao.findTuangoucountById(goodId);
	}

}
