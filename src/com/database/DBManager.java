package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static String url="jdbc:mysql://120.78.159.172:3306/user";
	private static String driverClass="com.mysql.jdbc.Driver";
	private static String username="root";
	private static String password="root2018";
	private static Connection conn;
	//装载驱动
	static{
		try{
			Class.forName(driverClass);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	//获取数据库连接
	public static Connection getConnection(){
		try{
			conn=DriverManager.getConnection(url,username,password);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	//建立数据库连接
	public static void main(String[] args){
		Connection conn=DBManager.getConnection();
		if(conn==null){
			System.out.println("数据库连接失败！");
		}
	}
	//关闭数据库连接
	public static void Close(){
		if(conn!=null){
			try{
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
