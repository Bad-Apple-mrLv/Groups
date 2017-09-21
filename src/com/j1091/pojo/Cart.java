package com.j1091.pojo;

import java.util.HashMap;
import java.util.Map;
/**
 * 购物车类
 * @author Administrator
 *
 */
public class Cart {
	private Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();
	
	/**
	 * 添加商品
	 */
	public void add(CartItem cartItem) {
		//添加商品前先判断购物车是否已经存在
		for(Integer key : items.keySet()) {
			CartItem item = items.get(key);
			if(item.getGood().getId() == key) {
				//已经购买过，返回flass
				item.setNum(item.getNum() + 1);
				return;
			}
		}
		items.put(cartItem.getGood().getId(), cartItem);
	}
	/**
	 * 返回所有商品
	 */
	public Map<Integer, CartItem> list() {
		return items;
	}
	/**
	 * 计价
	 */
	public Double sum() {
		double sum = 0;
		for(Integer key : items.keySet()) {
			CartItem item = items.get(key);
			sum += item.getGood().getPrice() * item.getNum();
		}
		return sum;
	}
	/**
	 * 删除商品
	 */
	public void delete(Integer id) {
		items.remove(id);
	}
	/**
	 * 修改商品的数量
	 */
	public void modify(Integer id, Integer num) {
		for(Integer key : items.keySet()) {
			CartItem item = items.get(key);
			if(id.equals(key)) {
				item.setNum(num);
			}
		}
	}
	/**
	 * 清空
	 */
	public void clear(){
		items.clear();
	}
}
