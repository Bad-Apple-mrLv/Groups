package com.j1091.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j1091.pojo.Goods;
import com.j1091.pojo.User;
import com.j1091.service.IGoodsService;
import com.j1091.service.Impl.GoodsServiceImpl;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/AddGood")
public class AddGood extends HttpServlet {
	
	private ServletConfig config;

	private IGoodsService goodsService = new GoodsServiceImpl();
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//拿到真实的项目路径
		String path = this.getServletContext().getRealPath("/gpic");
		//新建一个SmartUpload对象  
		SmartUpload smart = new SmartUpload();
		//上传初始化一个SmartUpload
		smart.initialize(config, request, response);
		//设置上传限制
		//1.限制每个上传文件的最大长度为10MB  
		smart.setMaxFileSize(10 * 1024 * 1024);  
        //2.限制总上传文件的长度  
		smart.setTotalMaxFileSize(30 * 1024 * 1024);  
        //3.设定允许上传的文件  
        // smart.setAllowedFilesList("txt,jpg");  
        //4.设定禁止上传的文件 
		try {
			smart.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			//上传文件  
			smart.upload();
			SmartFiles smartFiles = smart.getFiles();
			if(smartFiles!=null){
				//拿到文件
				SmartFile smartFile = smartFiles.getFile(0);
				//拿到文件名

				String gname = smartFile.getFileName();
				//session作用域拿到用户
				User user = (User)request.getSession().getAttribute("user");
				//创建实体，进行封装数据
				Goods good = new Goods();
				
				good.setName(smart.getRequest().getParameter("name"));
				good.setDetails(smart.getRequest().getParameter("details"));
				good.setGoodspic("gpic/" + gname);
				good.setPrice(Double.valueOf(smart.getRequest().getParameter("price")));
				good.setTuangoucount(Integer.valueOf(smart.getRequest().getParameter("tuangoucount")));
				good.setType(Integer.valueOf(smart.getRequest().getParameter("type")));
				good.setTraderid(user.getId());
				
				String act = smart.getRequest().getParameter("act");
				if("upd".equals(act)) {
					good.setId(Integer.valueOf(smart.getRequest().getParameter("goodsid")));
					goodsService.update(good);
				} else {
					goodsService.insert(good);
				}
				smartFile.saveAs(path + "/" + gname);
				System.out.println("路径为：" + gname);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
