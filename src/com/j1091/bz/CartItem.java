package com.j1091.bz;

import com.j1091.pojo.Goods;

public class CartItem {
	private Goods good;
	private Integer count;
	
	public Goods getGood() {
		return good;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void add(){
    	count = count+1;
    }
    
    public void sub(){
    	count = count-1;
    }
}
