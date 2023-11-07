package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCapDao {
	public ArrayList<NhaCungCap> getAllPhongBan() {
		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM NhaCungCap";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				int maNCC = rs.getInt("maNCC");
				String tenNCC = rs.getString("tenNCC");
				String sdtNCC = rs.getString("sdtNCC");
				String diachiNCC = rs.getString("diachiNCC");
				String emailNCC = rs.getString("emailNCC");
				

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsNhaCungCap;
	}
	
}
