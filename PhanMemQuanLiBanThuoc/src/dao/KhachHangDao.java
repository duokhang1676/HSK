package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.KhachHang;
import entity.Thuoc;

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
				int maKhachHang = rs.getInt("MaKhachHang");
				String tenKhachHang = rs.getString("TenKhachHang");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngayTao = rs.getDate("NgayTao").toLocalDate();
				
				
				dsKhachHang.add(new KhachHang(maKhachHang, tenKhachHang, soDienThoai, ngayTao));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsKhachHang;
	}
	
	public boolean themKhachHang(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO KhachHang(TenKhachHang, SoDienThoai, NgayTao) VALUES(?, ?, ?)");

			stmt.setString(1, khachHang.getTenKhachHang());
			stmt.setString(2, khachHang.getSoDienThoai());
			stmt.setDate(3, Date.valueOf(khachHang.getNgayTao()));
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	} 

	public boolean xoaKhachHang(int maKhachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM KhachHang WHERE MaKhachHang = ?");

			stmt.setInt(1, maKhachHang);
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	} 
}
