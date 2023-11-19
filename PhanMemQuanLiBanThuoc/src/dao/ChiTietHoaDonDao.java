package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Thuoc;
import model.TopSaleInCategory;

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
				ChiTietHoaDon ctHD = new ChiTietHoaDon(ma, soLuong, donViTinh, donGgia, sp, thue, thanhTien, giamGia,
						hd, maGiamGia);
				dsCTHD.add(ctHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public boolean themChiTietHoaDonKhongGG(ChiTietHoaDon ctHD) {

		int n = 0;
		try {
			PreparedStatement statement = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO [dbo].[ChiTietHoaDon]([SoLuong]\r\n" + "      ,[DonViTinh]\r\n"
					+ "      ,[DonGia]\r\n" + "      ,[MaSanPham]\r\n" + "      ,[Thue]\r\n" + "      ,[ThanhTien]\r\n"
					+ "      ,[GiamGia]\r\n" + "      ,[MaHoaDon])\r\n" + "VALUES \r\n" + "	(?,?,?,?,?,?,?,?)";
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
		return n > 0;

	}

	public boolean themChiTietHoaDon(ChiTietHoaDon ctHD) {

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
		return n > 0;

	}
	
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonByMaDonHang(int maDonHang) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();

			String sql = "SELECT * \r\n"
					+ "FROM ChiTietHoaDon\r\n"
					+ "LEFT JOIN Thuoc ON ChiTietHoaDon.MaSanPham = Thuoc.MaThuoc\r\n"
					+ "WHERE ChiTietHoaDon.[MaHoaDon] = ?";
			

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, maDonHang);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				int ma = rs.getInt("MaChiTietHoaDon");
				int soLuong = rs.getInt("SoLuong");
				String donViTinh = rs.getString("DonViTinh");
				double donGia = rs.getDouble("DonGia");
				Thuoc sp = new Thuoc(rs.getInt("MaThuoc"), rs.getString("TenThuoc"), null, "", "", "", LocalDate.now(), "", "", "", 0, 0, 0, 0, null);
				double thue = rs.getDouble("Thue");
				double thanhTien = rs.getDouble("ThanhTien");
				double giamGia = rs.getDouble("GiamGia");
				HoaDon hd = new HoaDon(rs.getInt("MaHoaDon"));
				int maGiamGia = rs.getInt("MaGiamGia");
				ChiTietHoaDon ctHD = new ChiTietHoaDon(ma, soLuong, donViTinh, donGia, sp, thue, thanhTien, giamGia, hd, maGiamGia);
				dsCTHD.add(ctHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public ArrayList<TopSaleInCategory> getTop5DanhMucThuocBanChay(LocalDate from, LocalDate to) {
		ArrayList<TopSaleInCategory> top5DanhMucThuoc = new ArrayList<TopSaleInCategory>();

		try {
			PreparedStatement statement = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP 5 NhomThuoc.MaNhomThuoc,TenNhomThuoc, SUM(SoLuong) AS SoThuocBanRa FROM ChiTietHoaDon\r\n"
					+ "INNER JOIN Thuoc ON Thuoc.MaThuoc = ChiTietHoaDon.MaSanPham\r\n"
					+ "INNER JOIN NhomThuoc ON NhomThuoc.MaNhomThuoc = Thuoc.MaNhomThuoc\r\n"
					+ "INNER JOIN HoaDon ON ChiTietHoaDon.MaHoaDon = HoaDon.MaHoaDon\r\n"
					+ "WHERE NgayLapHoaDon BETWEEN ? AND ?\r\n"
					+ "GROUP BY NhomThuoc.MaNhomThuoc, TenNhomThuoc\r\n";
			statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(from));
			statement.setDate(2, Date.valueOf(to));
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String tenNhomThuoc = rs.getString("TenNhomThuoc");
				long soLuongBanRa = rs.getLong("SoThuocBanRa");

				top5DanhMucThuoc.add(new TopSaleInCategory(tenNhomThuoc, soLuongBanRa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return top5DanhMucThuoc;
	}
}
