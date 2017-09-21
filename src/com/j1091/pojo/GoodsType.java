package com.j1091.pojo;

import java.io.Serializable;

public class GoodsType implements Serializable {
	private int id;
	private String typename;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Override
	public String toString() {
		return "GoodsType [id=" + id + ", typename=" + typename + "]";
	}
	
	
}
