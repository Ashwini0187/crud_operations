package com.sistec.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmpDb {
private static final String URL="jdbc:mysql://localhost:3306/emp_db";
private static final String USER="root";
private static final String PWD="iniwhsa";
private static final String DRIVER="com.mysql.jdbc.Driver";
private static Connection conn=null;
static {
	try {
		Class.forName(DRIVER);
		conn=DriverManager.getConnection(URL, USER, PWD);
		
	} catch (Exception e) {
	System.out.println("connection faild"+e);
	}
}
	public static Connection getEmpDb() {
		return conn;
	}
		public static void main(String[] args) {
			System.out.println(getEmpDb());
		}
}
