package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import entity.ChiTietHoaDon;
import entity.KhachHang;
import entity.MaGiamGia;
import entity.NhomThuoc;
import entity.Quay;
import entity.Thuoc;
import model.TopSaleInPremises;

public class QuayDao {
	public ArrayList<Quay> getAllData() {
		ArrayList<Quay> dsQuay = new ArrayList<Quay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from Quay";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int maQuay = rs.getInt("MaQuay");
				String tenQuay = rs.getString("TenQuay");
				String diaChi = rs.getString("DiaChi");
				String phuong = rs.getString("Phuong");
				String thanhPho = rs.getString("ThanhPho");
				String tinh = rs.getString("Tinh");

				Quay quay = new Quay(maQuay, tenQuay, diaChi, phuong, thanhPho, tinh);
				dsQuay.add(quay);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dsQuay;
	}

	public boolean deleteQuay(int maQuay) {

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			PreparedStatement statement = con.prepareStatement("DELETE FROM [Quay] WHERE [MaQuay] = ?");
			statement.setInt(1, maQuay);

			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<TopSaleInPremises> getTopSaleInPremises(LocalDate from, LocalDate to) {
		List topSales = new ArrayList<TopSaleInPremises>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			PreparedStatement statement = con
					.prepareStatement("SELECT TOP 5 Quay.MaQuay, TenQuay, SUM(TongTien) AS DoanhThu\r\n"
							+ "  FROM HoaDon\r\n" + "  LEFT JOIN Quay ON Quay.MaQuay = HoaDon.MaQuay \r\n"
							+ "  WHERE [NgayLapHoaDon] BETWEEN ? AND ? \r\n" + "  GROUP BY Quay.MaQuay, TenQuay\r\n"
							+ "  ORDER BY DoanhThu DESC");

			statement.setDate(1, Date.valueOf(from));
			statement.setDate(2, Date.valueOf(to));

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int maQuay = rs.getInt("MaQuay");
				String tenQuay = rs.getString("TenQuay");
				double doanhThu = rs.getDouble("DoanhThu");

				topSales.add(new TopSaleInPremises(maQuay, tenQuay, doanhThu));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return topSales;
	}

	public boolean themQuay(Quay q) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " Quay values( ?, ?, ?, ?, ?)");
			stmt.setString(1, q.getTenQuay());
			stmt.setString(2, q.getDiaChi());
			stmt.setString(3, q.getPhuong());
			stmt.setString(4, q.getThanhPho());
			stmt.setString(5, q.getTinh());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean suaThongTinQuay(Quay quay) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE Quay "
					+ "SET TenQuay = ?, DiaChi = ?, Phuong = ?, ThanhPho = ?, Tinh = ?" + " WHERE MaQuay = ?");

			stmt.setString(1, quay.getTenQuay());
			stmt.setString(2, quay.getDiaChi());
			stmt.setString(3, quay.getPhuong());
			stmt.setString(4, quay.getThanhPho());
			stmt.setString(5, quay.getTinh());
			stmt.setInt(6, quay.getMaQuay());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
