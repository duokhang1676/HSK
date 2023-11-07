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

	public boolean themChiTietHoaDon(ChiTietHoaDon ctHD) throws Exception{
		int n = 0;
		PreparedStatement statement = null;

			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert into ChiTietHoaDon values(?,?,?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setInt(1, ctHD.getMaCTHD());
			statement.setDouble(2, ctHD.getSoLuong());
			statement.setString(3, ctHD.getDonViTinh());
			statement.setDouble(4, ctHD.getDonGia());
			statement.setInt(5, ctHD.getSanPham().getMaThuoc());
			statement.setDouble(6, ctHD.getThue());
			statement.setDouble(7, ctHD.getThanhTien());
			statement.setDouble(8, ctHD.getGiamGia());
			statement.setInt(9, ctHD.getHoaDon().getMaHD());
			statement.setInt(10, ctHD.getMaGiamGia());
			n = statement.executeUpdate();

		return n>0;
	}
}
