package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Thuoc;

public class ChiTietHoaDonDao {
	public ArrayList<ChiTietHoaDon> getAllTBChiTietHoaDon() {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "Select * from ChiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt(0);
				int soLuong = rs.getInt(1);
				String donViTinh = rs.getString(2);
				double donGgia = rs.getDouble(3);
				Thuoc sp = new Thuoc(rs.getInt(4));
				double thue = rs.getDouble(5);
				double thanhTien = rs.getDouble(6);
				double giamGia = rs.getDouble(7);
				HoaDon hd = new HoaDon(rs.getInt(8));
				int maGiamGia = rs.getInt(9);
				ChiTietHoaDon ctHD = new ChiTietHoaDon(ma, soLuong, donViTinh, donGgia, sp, thue, thanhTien, giamGia, hd, maGiamGia);
				dsCTHD.add(ctHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public boolean themChiTietHoaDonKhongGG(ChiTietHoaDon ctHD){
		
		int n = 0;
		try {
		PreparedStatement statement = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[ChiTietHoaDon]([SoLuong]\r\n"
					+ "      ,[DonViTinh]\r\n"
					+ "      ,[DonGia]\r\n"
					+ "      ,[MaSanPham]\r\n"
					+ "      ,[Thue]\r\n"
					+ "      ,[ThanhTien]\r\n"
					+ "      ,[GiamGia]\r\n"
					+ "      ,[MaHoaDon])\r\n"
					+ "VALUES \r\n"
					+ "	(?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setDouble(1, ctHD.getSoLuong());
			statement.setString(2, ctHD.getDonViTinh());
			statement.setDouble(3, ctHD.getDonGia());
			statement.setInt(4, ctHD.getSanPham().getMaThuoc());
			statement.setDouble(5, ctHD.getThue());
			statement.setDouble(6, ctHD.getThanhTien());
			statement.setDouble(7, ctHD.getGiamGia());
			statement.setInt(8, ctHD.getHoaDon().getMaHD());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
		
	}
	
public boolean themChiTietHoaDon(ChiTietHoaDon ctHD){
		
		int n = 0;
		try {
		PreparedStatement statement = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert into ChiTietHoaDon values(?,?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setDouble(1, ctHD.getSoLuong());
			statement.setString(2, ctHD.getDonViTinh());
			statement.setDouble(3, ctHD.getDonGia());
			statement.setInt(4, ctHD.getSanPham().getMaThuoc());
			statement.setDouble(5, ctHD.getThue());
			statement.setDouble(6, ctHD.getThanhTien());
			statement.setDouble(7, ctHD.getGiamGia());
			statement.setInt(8, ctHD.getHoaDon().getMaHD());
			statement.setInt(9, ctHD.getMaGiamGia());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
		
	}
	
	private ArrayList<ChiTietHoaDon> getAllChiTietHoaDonByMaDonHang(int maDonHang) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT * FROM ChiTietHoaDon WHERE [MaHoaDon] = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, maDonHang);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt(0);
				int soLuong = rs.getInt(1);
				String donViTinh = rs.getString(2);
				double donGgia = rs.getDouble(3);
				Thuoc sp = new Thuoc(rs.getInt(4));
				double thue = rs.getDouble(5);
				double thanhTien = rs.getDouble(6);
				double giamGia = rs.getDouble(7);
				HoaDon hd = new HoaDon(rs.getInt(8));
				int maGiamGia = rs.getInt(9);
				ChiTietHoaDon ctHD = new ChiTietHoaDon(ma, soLuong, donViTinh, donGgia, sp, thue, thanhTien, giamGia, hd, maGiamGia);
				dsCTHD.add(ctHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}
}
