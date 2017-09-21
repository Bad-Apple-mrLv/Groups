package com.j1091.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j1091.controller.IAction;
import com.j1091.pojo.Cart;
import com.j1091.pojo.CartItem;
import com.j1091.pojo.Goods;
import com.j1091.service.IGoodsService;
import com.j1091.service.Impl.GoodsServiceImpl;


public class GoodsAction implements IAction {
	
	private int typeid = 0;
	private int pageCurr = 1;
	
	//商品详细
	private int gid;
	
	IGoodsService goodsService = new GoodsServiceImpl();
	
	//增加商品到购物车
	private Date s;

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int pageSum = goodsService.findCount(typeid);
		
		if(pageCurr < 1)
			this.pageCurr = 1;
		if(pageCurr > pageSum)
			this.pageCurr = pageSum;
		List<Goods> goods = goodsService.findBypage(pageCurr, typeid);
		request.setAttribute("goods", goods);
		request.setAttribute("pageCurr", pageCurr);
		request.setAttribute("pageSum", pageSum);
		request.getSession().setAttribute("typeid", typeid);
		return "index.jsp";
	}
	//查询商品详细
	public String todetailgood(HttpServletRequest request, HttpServletResponse response) {
		Goods good = goodsService.findById(gid);
		request.setAttribute("goods", good);
		return "detailgood.jsp";	
	}
	//查询商品并更新
	public String toupdgood(HttpServletRequest request, HttpServletResponse response) {
		Goods good = goodsService.findById(gid);
		request.setAttribute("goods", good);
		request.setAttribute("act", "upd");
		return "addgood.jsp";	
	}
	
	//查询购物车
	public String shopcar(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			//如果session当中没有绑定cart, 则创建cart对象, 然后绑定到session上
			cart = new Cart();
		}
		Map<Integer, CartItem> shopcar = cart.list();
		Double sum = cart.sum();
		request.setAttribute("sum", sum);
		request.getSession().setAttribute("shopcar", shopcar);
		return "shopcar.jsp";
	}
	
	//添加商品到购物车
	public String addshopcar(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			//如果session当中没有绑定cart, 则创建cart对象, 然后绑定到session上
			cart = new Cart();
		}
		System.out.println("gid:" + gid + "s:" + s);
		return "@json_{detailgood.jsp}";	
	}

	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getPageCurr() {
		return pageCurr;
	}
	public void setPageCurr(int pageCurr) {
		this.pageCurr = pageCurr;
	}
	
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Date getS() {
		return s;
	}
	public void setS(Date s) {
		this.s = s;
	}	
}
