package com.j1091.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.j1091.bz.AjaxReturn;
import com.j1091.bz.CartItem;
import com.j1091.bz.PaymentUtil;
import com.j1091.controller.IAction;
import com.j1091.pojo.Goods;
import com.j1091.pojo.Orders;
import com.j1091.pojo.User;
import com.j1091.service.IGoodsService;
import com.j1091.service.IOrdersService;
import com.j1091.service.Impl.GoodsServiceImpl;
import com.j1091.service.Impl.OrdersServiceImpl;


public class GoodsAction implements IAction {
	
	private int typeid = 0;
	private int pageCurr = 1;
	
	//商品详细
	private Integer gid;
	
	IGoodsService goodsService = new GoodsServiceImpl();
	IOrdersService orderService = new OrdersServiceImpl();
	
	//增加商品到购物车
	private Date s;
	
	//商品总价
	private Double sum;
	
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
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>) request.getSession().getAttribute("shopcar");
		if(shopcar == null) {
			shopcar = new HashMap<Integer, CartItem>();
		}
		request.getSession().setAttribute("shopcar", shopcar);
		return "shopcar.jsp";
	}
	
	//添加商品到购物车
	public String addshopcar(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>) request.getSession().getAttribute("shopcar");
		if(shopcar == null) {
			shopcar = new HashMap<Integer, CartItem>();
		}
		CartItem item = shopcar.get(gid);
		if(item == null) {
			item = new CartItem();
			item.setGood(goodsService.findById(gid));
			item.setCount(0);
		}
		//弹框数据
    	AjaxReturn ajaxReturn = new AjaxReturn();
    	ajaxReturn.setHead("ok");
		if(item.getCount() < goodsService.findTuangoucountById(gid)) {
			item.add();
			shopcar.put(gid, item);
	    	ajaxReturn.setBody("添加购物车成功！！！"+((Goods)goodsService.findById(gid)).getName());	
		}else {	
			ajaxReturn.setBody("抱歉，商品库存不足，添加失败！"+((Goods)goodsService.findById(gid)).getName());	
		}
    	Gson gson = new Gson();
    	request.getSession().setAttribute("shopcar", shopcar);
    	return "@json_"+gson.toJson(ajaxReturn);
	}
	//购物车数量减一
	public String subshopcar(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>)request.getSession().getAttribute("shopcar");
		if(gid != null) {
			CartItem cartItem = shopcar.get(gid);
			if(cartItem.getCount()>1) {
				cartItem.sub();
				shopcar.put(gid, cartItem);
			} else {
				shopcar.remove(gid);
			}
			request.getSession().setAttribute("shopcar", shopcar);
		}
		return "shopcar.jsp";	
	}
	//购物车数量加一
	public String addgoods(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>)request.getSession().getAttribute("shopcar");
		if(gid != null) {
			CartItem cartItem = shopcar.get(gid);
			if(cartItem.getCount() < goodsService.findTuangoucountById(gid)) {
				cartItem.add();
				shopcar.put(gid, cartItem);
			} else {
				request.setAttribute("msg", "库存不足");
			}
			request.getSession().setAttribute("shopcar", shopcar);
		}
		return "shopcar.jsp";	
		
	}
	//购物车删除商品
	public String removegoods(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>)request.getSession().getAttribute("shopcar");
		shopcar.remove(gid);
		request.getSession().setAttribute("shopcar", shopcar);
		return "shopcar.jsp";	
	}

	//提交订单
	public String order(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			return "login.jsp";
		}
		Map<Integer, CartItem> shopcar = (Map<Integer, CartItem>)request.getSession().getAttribute("shopcar");
		if(shopcar.size() <= 0) {
			request.setAttribute("msg", "订单提交失败，购物车不能为空");
			return "shopcar.jsp";
		}
		Date date = new Date();
		SimpleDateFormat sdfNo = new SimpleDateFormat("yyyyMMddHHmmssSS");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = Timestamp.valueOf(sdfDate.format(date));
		Orders order = new Orders();
		order.setOrderid(sdfNo.format(date));
		order.setUserid(user.getId());
		order.setRealname(user.getRealname());
		order.setAddress(user.getAddress());
		order.setPhone(user.getPhone());
		order.setEmail(user.getEmail());
		order.setTotal(sum);
		order.setTime(timestamp);
		order.setStatus(0);
		int fig = orderService.insert(order);
		if(fig == 1) {
			shopcar.clear();
			request.getSession().setAttribute("shopcar", shopcar);
			request.setAttribute("order", order);
			return "order.jsp";
		}
		request.setAttribute("msg", "订单提交失败，请稍候再试");
		return "shopcar.jsp";
	}
	
	public String pay(HttpServletRequest request, HttpServletResponse response) {
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String pd_Id = request.getParameter("pd_FrpId");
		System.out.println(orderid+"  "+money+"     "+pd_Id);
		//配置接口参数
		String p0_Cmd = "Buy";//业务类型
		String p1_MerId = "10001126856";//商户编号
		String p2_Order = orderid; //唯一不能为空，商户订单号
		String p3_Amt = money;//支付金额
		String p4_Cur = "CNY";//货币类型
		String p5_Pid = "";//商品名称
		String p6_Pcat = "";//商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = "http://localhost:8080/Groups/goods_CallbackServlet.action";//接收付款成功后的回傳值
		String p9_SAF = "";//送货地址
		String pa_MP = "";//商户扩展信息
		String pd_FrpId = pd_Id; //银行编号
		String pr_NeedResponse = "";//应答机制
		
		//商户密钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		
		//签名数据
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
				p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
				pr_NeedResponse, keyValue);	
		System.out.println(hmac);
		
		// 将所有参数 发送给 易宝指定URL
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		return "confirm.jsp";
	}
	//接收付款成功   goods_CallbackServlet.action
  	public String CallbackServlet(HttpServletRequest request,
  			HttpServletResponse response) {
  		// 验证请求来源和数据有效性
  		// 阅读支付结果参数说明
  		String p1_MerId = request.getParameter("p1_MerId");
  		String r0_Cmd = request.getParameter("r0_Cmd");
  		String r1_Code = request.getParameter("r1_Code");
  		String r2_TrxId = request.getParameter("r2_TrxId");
  		String r3_Amt = request.getParameter("r3_Amt");
  		String r4_Cur = request.getParameter("r4_Cur");
  		String r5_Pid = request.getParameter("r5_Pid");
  		String r6_Order = request.getParameter("r6_Order");
  		String r7_Uid = request.getParameter("r7_Uid");
  		String r8_MP = request.getParameter("r8_MP");
  		//交易结果返回类型，为“1”: 浏览器重定向; 为“2”: 服务器点对点通讯.
  		String r9_BType = request.getParameter("r9_BType");
  		//以下返回的结果参数不参与到hmac校验
  		//支付通道编码
  		String rb_BankId = request.getParameter("rb_BankId");
  		//银行订单号
  		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
  		//支付成功时间
  		String rp_PayDate = request.getParameter("rp_PayDate");
  		//神州行充值卡序列号
  		String rq_CardNo = request.getParameter("rq_CardNo");
  		//交易结果通知时间
  		String ru_Trxtime = request.getParameter("ru_Trxtime");

  		// hmac
  		String hmac = request.getParameter("hmac");
  		
  		// 利用本地密钥和加密算法 加密数据
  		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
  		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
  				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
  				r8_MP, r9_BType, keyValue);
  		if (isValid) {
  			// 有效
  			if (r9_BType.equals("1")) {
  				// 浏览器重定向
  			  return  "@json_"+("支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
  			} else if (r9_BType.equals("2")) {
  				// 服务器点对点，来自于易宝的通知
  				System.out.println("收到易宝通知，修改订单状态！");//
  				// 回复给易宝success，如果不回复，易宝会一直通知
  				return  "@json_"+("success");
  			}

  		} else {
  			throw new RuntimeException("数据被篡改！");
  		}	
  		return "index.jsp";
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

	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Date getS() {
		return s;
	}
	public void setS(Date s) {
		this.s = s;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
}
