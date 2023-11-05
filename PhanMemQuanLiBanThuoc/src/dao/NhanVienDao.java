package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVienDao {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM NhanVien";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				int maNV = rs.getInt("maNV");
				String ho = rs.getString("ho");
				String ten = rs.getString("ten");
				int tuoi = rs.getInt("tuoi");
				boolean phai = rs.getBoolean("phai");
				double luong = rs.getDouble("tienLuong");
				PhongBan pBan = new PhongBan(rs.getString("maPhong"));
				
				//dsNhanVien.add(new NhanVien(maNV, ho, ten, tuoi, phai, luong, pBan));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsNhanVien;
	}
	
}
