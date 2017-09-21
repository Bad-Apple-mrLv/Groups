package com.j1091.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BaseC3p0PoolDao {
	private static final String table_prefix = "t_";

	private C3P0DBPool pool;
	private String DBURL;
	private String DBUSR;
	private String DBPWD;
	private DataSource ds;
	private Connection connection = null;
	private PreparedStatement pstm = null;

	public BaseC3p0PoolDao() {
		pool = C3P0DBPool.getPool();
		ds = pool.getDataSource();

	}

	public int delete(int id) {
		// 动态拼接SQL
		String sql = "delete from " + getTableName() + " where id =   ?  ";
		return meger(sql, id);

	}

	public List findAll() {
		List list = null;
		String sql = getSelectAll();
		list = findSome(sql);
		return list;
	}

	public Object findById(int id) {
		Object object = null;
		String sql = this.getSelectAll() + " where id = ? ";
		object = findOne(sql, id);
		return object;
	}

	/**
	 * 动态select * from t_xxx
	 * 
	 * @return
	 */
	public String getSelectAll() {
		// 取得自己

		String sql = "select * from " + getTableName();
		return sql;
	}

	public int update(Object pojoObject) {
		int resultInt = 0;
		// 动态拼接SQL
		StringBuilder sql = new StringBuilder("update   " + getTableName()
				+ " set   ");

		// 把包名的dao替换成pojo
		String pojoName = this.getClass().getName().replace("dao", "pojo");
		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] fields = pojoClass.getDeclaredFields();
			for (int i = 1; i < fields.length; i++) {
				sql.append(fields[i].getName() + " = ?   ");
				// 最后一个，不加
				if (i != fields.length - 1) {
					sql.append(", ");
				}
			}
			sql.append(" where id = ?  ");
			Object[] params = new Object[fields.length]; // UPDATE语句带ID所以不-1
			for (int i = 1; i < fields.length; i++) {
				// 动态拼接getter方法
				String getMethodName = "get"
						+ fields[i].getName().substring(0, 1).toUpperCase()
						+ fields[i].getName().substring(1);
				// 获取get方法
				Method getMethod = pojoClass.getMethod(getMethodName);
				// 调用getter方法放到数组当中
				params[i - 1] = getMethod.invoke(pojoObject);
			}

			// 填充id
			// 动态拼接getter方法
			String getMethodName = "get"
					+ fields[0].getName().substring(0, 1).toUpperCase()
					+ fields[0].getName().substring(1);
			// 获取get方法
			Method getMethod = pojoClass.getMethod(getMethodName);
			// 调用getter方法放到数组当中
			params[fields.length - 1] = getMethod.invoke(pojoObject);

			resultInt = meger(sql.toString(), params);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultInt;
	}

	public int insert(Object pojoObject) {
		int resultInt = 0;
		StringBuilder sql = new StringBuilder("insert into " + getTableName()
				+ " (  ");

		// 把包名的dao替换成pojo
		String pojoName = this.getClass().getName().replace("dao", "pojo");
		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);

		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] pojoFields = pojoClass.getDeclaredFields();

			for (int i = 1; i < pojoFields.length; i++) {
				sql.append(pojoFields[i].getName() + "   ");
				if (i != pojoFields.length - 1) {
					sql.append(", ");
				}
			}

			sql.append("  ) values ( ");
			for (int i = 1; i < pojoFields.length; i++) {
				sql.append("  ? ");
				if (i != pojoFields.length - 1) {
					sql.append(", ");
				}
			}
			sql.append("  )");
			Object[] params = new Object[pojoFields.length - 1]; // UPDATE语句带ID所以不-1
			for (int i = 1; i < pojoFields.length; i++) {
				// 动态拼接getter方法
				String getMethodName = "get"
						+ pojoFields[i].getName().substring(0, 1).toUpperCase()
						+ pojoFields[i].getName().substring(1);
				// 获取get方法
				Method getMethod = pojoClass.getMethod(getMethodName);
				// 调用getter方法放到数组当中
				params[i - 1] = getMethod.invoke(pojoObject);
			}

			resultInt = meger(sql.toString(), params);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultInt;
	}

	/**
	 * 获取表名
	 * 
	 * @return
	 */
	private String getTableName() {
		Class daoClass = this.getClass();
		String tableName = daoClass.getName().substring(
				daoClass.getName().indexOf("dao") + 4);
		tableName = table_prefix
				+ tableName.substring(0, tableName.length() - 3);
		return tableName;
	}

	/**
	 * 更新
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	protected int meger(String sql, Object... objs) {
		int resultInt = 0;
		try {
			pstm = this.getConn().prepareStatement(sql);

			if (objs != null && objs.length > 0) {
				for (int i = 0; i < objs.length; i++) {
					pstm.setObject(i + 1, objs[i]);
				}
			}
			resultInt = pstm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		return resultInt;
	}

	protected Object findOne(String sql, Object objs) {
		Object object = null;

		ResultSet resultSet = this.query(sql, objs);

		try {
			if (resultSet != null && resultSet.next()) {
				object = AutoSetter(resultSet);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		return object;
	}

	protected List findSome(String sql, Object... objs) {
		List list = null;

		ResultSet resultSet = this.query(sql, objs);

		try {
			if (resultSet != null) {
				list = new ArrayList();
				while (resultSet.next()) {
					Object object = AutoSetter(resultSet);
					list.add(object);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		return list;
	}

	protected ResultSet query(String sql, Object... objs) {

		try {
			pstm = this.getConn().prepareStatement(sql);
			if (objs != null && objs.length > 0) {
				for (int i = 0; i < objs.length; i++) {
					pstm.setObject(i + 1, objs[i]);
				}
			}
			return pstm.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 动态封装
	 * 
	 * @return
	 */
	private Object AutoSetter(ResultSet rs) {
		Object pojo = null;
		if (rs != null) {
			Class daoClass = this.getClass();
			// 把包名换成pojo
			String pojoName = daoClass.getName().replace("dao", "pojo");
			// 把类名的Dao去掉
			pojoName = pojoName.substring(0, pojoName.length() - 3);

			try {

				Class pojoClass = Class.forName(pojoName);
				// 利用反射得到对象
				pojo = pojoClass.newInstance();
				// 得到属性名
				Field[] pojoFields = pojoClass.getDeclaredFields();
				if (pojoFields != null && pojoFields.length > 0) {
					for (int i = 0; i < pojoFields.length; i++) {
						// 根据类属性名字修改成set方法名
						String setMethonName = "set"
								+ pojoFields[i].getName().substring(0, 1)
										.toUpperCase()
								+ pojoFields[i].getName().substring(1,
										pojoFields[i].getName().length());
						// 得到方法
						Method setMethod = pojoClass.getMethod(setMethonName,
								pojoFields[i].getType());
						// 得到结果集的值
						Object values = rs.getObject(pojoFields[i].getName());

						if (values != null) {
							// 执行Setter方法
							setMethod.invoke(pojo, values);
						}
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return pojo;
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	protected Connection getConn() {
		try {
			// 假如连接为空
			if (connection == null) {
				// 创建连接
				connection = ds.getConnection();

			} else {
				// 连接关闭的时候
				if (connection.isClosed()) {
					// 创建连接
					connection = ds.getConnection();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	protected void closeConn() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
