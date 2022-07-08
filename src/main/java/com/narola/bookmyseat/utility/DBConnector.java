package com.narola.bookmyseat.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.narola.bookmyseat.exception.DBException;

public class DBConnector {

	private static DBConnector dbConnection = null;

	private Connection connection = null;
	private String dbUrl = null;
	private String dbUser = null;
	private String dbPassword = null;

	private DBConnector(String url, String user, String pwd) throws DBException {
		this.dbUrl = url;
		this.dbUser = user;
		this.dbPassword = pwd;
		init();
	}

	public static DBConnector getInstance() {
		return dbConnection;
	}

	public static DBConnector getInstance(String url, String user, String pwd) {
		if (dbConnection == null) {
			dbConnection = new DBConnector(url, user, pwd);
		}
		return dbConnection;
	}

	private void init() throws DBException {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Not able to connect DB", e);
		}
	}

	public Connection getConnection() throws DBException {
		init();
		return connection;
	}

	public void releaseResource(PreparedStatement ps) {
		releaseResource(ps, null);
	}

	public void releaseResource(PreparedStatement ps, ResultSet resultSet) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
