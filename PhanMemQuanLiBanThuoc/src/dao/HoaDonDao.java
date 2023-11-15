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
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Quay;
import model.PercentagePaymentMethod;

public class HoaDonDao {
	public ArrayList<HoaDon> getAllTBHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "Select * from HoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt(0);
				LocalDate ngayLapHD = rs.getDate(1).toLocalDate();
				String trangThai = rs.getString(2);
				String phuongThucTT = rs.getString(3);
				double tienNhan = rs.getDouble(4);
				double tienDu = rs.getDouble(5);
				KhachHang kh = new KhachHang(rs.getInt(6));
				NhanVien nv = new NhanVien(rs.getInt(7));
				Quay quay = new Quay(rs.getInt(8));
				double tongTienGiam = rs.getDouble(9);
				double tongTien = rs.getDouble(10);
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	public HoaDon themHoaDon(HoaDon hd)  {
		int affectedRows  = 0;
		PreparedStatement statement = null;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Insert into HoaDon values(?,?,?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, Date.valueOf(hd.getNgayLapHD()));
			statement.setString(2, hd.getTrangThai());
			statement.setString(3, hd.getPhuongThucThanhToan());
			statement.setDouble(4, hd.getTienNhan());
			statement.setDouble(5, hd.getTienDu());
			statement.setInt(6, hd.getKhachHang().getMaKhachHang());
			statement.setInt(7, hd.getNhanVien().getMaNhanVien());
			statement.setInt(8, hd.getQuay().getMaQuay());
			statement.setDouble(9, hd.getTongTienGiam());
			statement.setDouble(10, hd.getTongTien());
			affectedRows = statement.executeUpdate();

			if (affectedRows > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
	            	hd.setMaHD(generatedKeys.getInt(1));
	            	return hd;
	            }
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<PercentagePaymentMethod> getPercentageOfMethodPayment() {
		ArrayList<PercentagePaymentMethod> dsPers = new ArrayList<PercentagePaymentMethod>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql =
					"SELECT [PhuongThucThanhToan], COUNT([PhuongThucThanhToan]) AS SoLuongDonHang\r\n" + 
					"FROM [dbo].[HoaDon]\r\n" + 
					"GROUP BY [PhuongThucThanhToan]";
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
				int soLuongDonHang = rs.getInt("SoLuongDonHang");
				
				dsPers.add(new PercentagePaymentMethod(phuongThucThanhToan, soLuongDonHang));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPers;
	}
	
	public int getTongSoDonHang() {
		return 0;
	}
	public int getMaHoaDonLast() {
		int maHoaDonLast = -1;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT Top 1 MaHoaDon\r\n"
					+ "FROM HoaDon\r\n"
					+ "ORDER BY MaHoaDon DESC";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			maHoaDonLast = rs.getInt("MaHoaDon");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maHoaDonLast;
	}
}
