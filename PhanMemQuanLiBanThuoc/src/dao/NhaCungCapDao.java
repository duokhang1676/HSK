package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCapDao {
	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM NhaCungCap";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				int maNCC = rs.getInt("MaNhaCungCap");
				String tenNCC = rs.getString("TenNhaCungCap");
				String sdtNCC = rs.getString("SoDienThoai");
				String diachiNCC = rs.getString("DiaChi");
				String emailNCC = rs.getString("Email");
				String quocGis = rs.getString("QuocGia");

				dsNhaCungCap.add(new NhaCungCap(maNCC, tenNCC, sdtNCC, diachiNCC, emailNCC, quocGis));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsNhaCungCap;
	}
	
}
