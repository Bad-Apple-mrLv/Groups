package com.j1091.service;

import java.util.List;

import com.j1091.pojo.Goods;

public interface IGoodsService {
	public int findCount(int typeid);
	public List<Goods> findBypage(int i, int typeid);	
	public Goods findById(int id);
	public int insert(Goods good);
	public int update(Goods good);
}
