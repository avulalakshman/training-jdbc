package com.lwl.tjdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public enum ConnectionUtil {
	obj;

	private static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(ConnectionUtil.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			System.out.println("While loading properties :" + e);
		}
	}

	public Connection getConnection() {

		Connection con = null;
		try {
			con = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
					properties.getProperty("db.password"));

		} catch (Exception e) {
			System.out.println("While getting connection :" + e);
		}
		return con;
	}

	public void close(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("While closing resources :" + e);
		}
	}

	public void close(Statement st, Connection con) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("While closing resources :" + e);
		}
	}
}
