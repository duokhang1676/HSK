package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.PhongBan;

public class PhongBanDao {
	public ArrayList<PhongBan> getAllPhongBan() {
		ArrayList<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM PhongBan";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				String maPB = rs.getString("maPhong");
				String tenPhongBan = rs.getString("tenPhong");
				
				dsPhongBan.add(new PhongBan(maPB, tenPhongBan));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsPhongBan;
	}
	
	
}
