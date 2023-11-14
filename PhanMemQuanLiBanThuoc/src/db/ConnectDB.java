package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public void connect() throws SQLException {
		con = DriverManager.getConnection(DbConfiguration.DataBaseUrl, DbConfiguration.User, DbConfiguration.Password);
	}
	
	public void disconnect() {
		if (con == null)
			return;
		
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
}