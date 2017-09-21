package com.j1091.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends HttpServlet {
	private Properties properties = null;
	// 默认UTF-8
	private String charset = "UTF-8";
	private static final String Jsonregex = "^@json_";
	private static final String Redregex = "^@red_";
	// 格式化日期
	private SimpleDateFormat smt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 格式化日期
	private SimpleDateFormat smt2 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 加载当前servlet 的init-param
		String actioncfg = config.getInitParameter("actioncfg");
		properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream(actioncfg));
			// 如果配置文件有配置charset那么根据配置文件来设置
			if (properties.getProperty("charset") != null) {
				charset = properties.getProperty("charset");
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("读配置文件错误！");
		}
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		// 得到请求参数
		String act = request.getRequestURI();
		// 截取 /userAct.do 最后/和截取.do
		act = act.substring(act.lastIndexOf("/") + 1,
				act.lastIndexOf(".action"));

		String classPath = properties.getProperty(act);
		String resultStr = null;
		// 反射开始
		try {
			if (act.indexOf("_") == -1) {
				Class actClass = Class.forName(classPath);
				IAction action = (IAction) actClass.newInstance();
				autoSetterParameter(request, actClass, action);
				resultStr = action.execute(request, response);
			} else {
				String[] act_mth = act.split("_");
				// 得到calssPath
				classPath = properties.getProperty(act_mth[0]);
				// 找到Actionclass
				Class actClass = Class.forName(classPath);
				System.out.println(act_mth[0]);
				System.out.println(act_mth[1]);
				// 得到一个对象
				IAction action = (IAction) actClass.newInstance();
				// 找到对应的方法
				Method actMethod = actClass.getMethod(act_mth[1], new Class[] {
						HttpServletRequest.class, HttpServletResponse.class });
				autoSetterParameter(request, actClass, action);
				// 动态的调用方法, 获取返回的页面name
				System.out.println(actMethod.getName());
				resultStr = (String) actMethod
						.invoke(action, request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (resultStr != null && !resultStr.equals("")) {
			if (regexHead(Jsonregex, resultStr)) {
				response.setContentType("text/html");
				// Json返回
				// 去掉前缀 如：@json_{a:b}去掉后为{a:b}
				PrintWriter out = response.getWriter();
				resultStr = resultStr.replaceFirst(Jsonregex, "");
				out.print(resultStr);
			} else if (regexHead(Redregex, resultStr)) {
				// 重定向
				// 去掉前缀 如：@red_index.jsp去掉后为index.jsp
				resultStr = resultStr.replaceFirst(Redregex, "");
				response.sendRedirect(resultStr);
			} else {
				// 默认转发
				request.getRequestDispatcher(resultStr).forward(request,
						response);
			}

		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("没有返回值，请检查！");
		}

	}

	/**
	 * 自动注入表单：根据组件名从表单中获取所有的组件的值，并将其存到反射创建出来的action的对象属性中
	 * 
	 * @param request
	 * @param actClass
	 * @param action
	 */
	private void autoSetterParameter(HttpServletRequest request,
			Class actClass, IAction action) {

		String fieldName = null;
		String setterName = null;
		Method setter = null;
		Class fieldType = null;

		// 获取表单名字
		Enumeration<String> pmNames = request.getParameterNames();

		while (pmNames.hasMoreElements()) {
			try { // 迭代表单名字
				// 拿到表单名字
				fieldName = pmNames.nextElement();

				//判断是否是表单元素，如果组件名没有.
				if (fieldName.indexOf(".") == -1) {
					
					// 非复合类型
					// 通过表单名称--->setter名称 uname --setUname(String uname)
					setterName = field2Method(1, fieldName);
					// 普通表单名字
					// 获取setter参数类型
					fieldType = actClass.getDeclaredField(fieldName).getType();
					// 获取setter方法
					setter = actClass.getDeclaredMethod(setterName, fieldType);
					// 分不同类型封装值
					if (fieldType.equals(String.class)) {
						//setter方法调用，invoke：第一个参数为调用该方法的对象。第二个参数为注入该方法的方法参数
						setter.invoke(action, request.getParameter(fieldName));
					} else if (fieldType.equals(Integer.class)
							|| fieldType.equals(int.class)) {
						setter.invoke(action, Integer.parseInt(request
								.getParameter(fieldName)));
					} else if (fieldType.equals(Float.class)
							|| fieldType.equals(float.class)) {
						setter.invoke(action, Float.parseFloat(request
								.getParameter(fieldName)));
					} else if (fieldType.equals(Double.class)
							|| fieldType.equals(double.class)) {
						setter.invoke(action, Double.parseDouble(request
								.getParameter(fieldName)));
					} else if (fieldType.equals(Date.class)) {
						if (request.getParameter(fieldName).length() <= 12) {
							setter.invoke(action,
									smt2.parse(request.getParameter(fieldName)));
						} else {
							setter.invoke(action,
									smt1.parse(request.getParameter(fieldName)));
						}

					}
				} 
				//判断是否是表单元素，如果组件名有.则属于一个类
				else {
					//复合类型
					//要封装到pojo类的名字
					//需要封装到pojo

					//根据.来切割。取得对象名和属性名
					String mtd_field[] = fieldName.split("\\.");
					//根据对象名获取对象类型
					Class pojoClz = actClass.getDeclaredField(mtd_field[0])
							.getType();
					//根据对象属性名获取属性类型
					Class fieldClz = pojoClz.getDeclaredField(mtd_field[1])
							.getType();
					// action.getUsr().setLoginName(loginName);
					//根据调用actClass类型的方法getSetter()方法 取得 pojo 类型 
					//注意：该类型需要先创建对象
					Object pojo = actClass.getMethod(
							field2Method(2, mtd_field[0])).invoke(action);
					
					System.out.println("214行" + pojo.getClass());
					
					//根据 对象类型 pojoClz 获取setSetter()方法。将其方法取出来存放到fieldSetter
					//getMethod第一个参数类型是方法名，第二个参数是方法参数类型
					Method fieldSetter = pojoClz.getMethod(
							field2Method(1, mtd_field[1]), fieldClz);
					
					// 封装值：根据属性类型进行判断修改注入 数据fieldName 的类型
					if (fieldClz.equals(String.class)) {
						fieldSetter.invoke(pojo,
								request.getParameter(fieldName));
					} else if (fieldClz.equals(Integer.class)
							|| fieldClz.equals(int.class)) {
						fieldSetter.invoke(pojo, Integer.parseInt(request
								.getParameter(fieldName)));
					} else if (fieldClz.equals(Float.class)
							|| fieldClz.equals(float.class)) {
						fieldSetter.invoke(pojo, Float.parseFloat(request
								.getParameter(fieldName)));
					} else if (fieldClz.equals(Double.class)
							|| fieldClz.equals(double.class)) {
						fieldSetter.invoke(pojo, Double.parseDouble(request
								.getParameter(fieldName)));
					} else if (fieldClz.equals(Date.class)) {
						if (request.getParameter(fieldName).length() <= 12) {

							fieldSetter.invoke(pojo, smt2.parse(request
									.getParameter(fieldName)));
						} else {
							fieldSetter.invoke(pojo, smt1.parse(request
									.getParameter(fieldName)));
						}
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 属性转方法名
	 * 
	 * @param type
	 *            1-setter方法，2-getter方法
	 * @param field
	 *            属性名字
	 * @return
	 */
	private String field2Method(int type, String field) {
		String result = null;
		if (type == 1) {
			result = "set" + field.substring(0, 1).toUpperCase()
					+ field.substring(1);
		}
		if (type == 2) {
			result = "get" + field.substring(0, 1).toUpperCase()
					+ field.substring(1);
		}
		return result;
	}

	/**
	 * 验证返回头
	 * 
	 * @param regex
	 *            正则表达式
	 * @param resultStr
	 *            验证内容
	 * @return true能查找 ，flase没找到
	 */
	private boolean regexHead(String regex, String resultStr) {
		boolean reslut = false;
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
		Matcher m = p.matcher(resultStr);

		if (m.find()) {
			if (m.start() == 0) {
				reslut = true;
			}
		}
		return reslut;
	}
}
