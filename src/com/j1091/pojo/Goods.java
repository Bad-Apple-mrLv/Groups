package com.j1091.pojo;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
	private int id;
	private int type;
	private int traderid;
	private String name;
	private double price;
	private String details;
	private String goodspic;
	private int tuangoucount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTraderid() {
		return traderid;
	}

	public void setTraderid(int traderid) {
		this.traderid = traderid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getGoodspic() {
		return goodspic;
	}

	public void setGoodspic(String goodspic) {
		this.goodspic = goodspic;
	}

	public int getTuangoucount() {
		return tuangoucount;
	}

	public void setTuangoucount(int tuangoucount) {
		this.tuangoucount = tuangoucount;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", type=" + type + ", traderid=" + traderid + ", name=" + name + ", price=" + price
				+ ", details=" + details + ", goodspic=" + goodspic + ", tuangoucount=" + tuangoucount + "]";
	}

}
