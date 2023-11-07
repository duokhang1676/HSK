package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.KhachHang;

public class KhachHangDao {
	public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				int maKH = rs.getInt("maKH");
				String ten = rs.getString("tenKH");
				String sdt = rs.getString("sdt");
				String ngaytao = rs.getString("ngayTao");
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsKhachHang;
	}

}
