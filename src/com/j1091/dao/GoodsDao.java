package com.j1091.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.j1091.pojo.Goods;

public class GoodsDao extends BaseC3p0PoolDao {
	
	public int findCount(int typeid) {
		int max = 0;
		String sql = null;
		try {
			if(typeid == 0) {
				sql ="select count(id) from t_goods";
			} else {
				sql = "select count(id) from t_goods where type=" + typeid;
			}
			Connection con = super.getConn();
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs =st.executeQuery();
			while(rs.next()){
				max =rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeConn();
		}
		return max;
	}

	public int findTuangoucountById(int goodId) {
		int max = 0;
		String sql = null;
		try {
			sql = "select tuangoucount from t_goods where id=" + goodId;
			Connection con = super.getConn();
			PreparedStatement st=con.prepareStatement(sql);
			ResultSet rs =st.executeQuery();
			while(rs.next()){
				max =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeConn();
		}
		return max;
	}
	
	public Goods findById(int id) {
		return (Goods)super.findById(id);
	}
	
	//可删
	public List<Goods> findBypage(int i, int pageSize) {
		
		return super.findSome("select * from t_goods limit ?,?",i,pageSize);
	}

	public List<Goods> findBypage(int i, int pageSize, int typeid) {
		return super.findSome("select * from t_goods where type = ? limit ?,?",typeid,i,pageSize);
	}
	
	public int insert(Object pojoObject) {
		return super.insert(pojoObject);
	}
	
	public int update(Object pojoObject) {
		return super.update(pojoObject);
	}
}
