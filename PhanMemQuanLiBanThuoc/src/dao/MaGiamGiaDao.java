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
import entity.MaGiamGia;
import entity.Thuoc;


public class MaGiamGiaDao {
	public ArrayList<MaGiamGia> getAllTbMaGiamGia() {
		ArrayList<MaGiamGia> dsGG = new ArrayList<MaGiamGia>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "Select * from MaGiamGia";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt("MaGiamGia");
				LocalDate ngayBD = LocalDate.parse(rs.getString("NgayBatDau"));
				LocalDate ngayKT = LocalDate.parse(rs.getString("NgayKetThuc"));
				double ptGiamGia = rs.getDouble("PhanTramGiamGia");
				String mota = rs.getString("MoTa");
				
				Thuoc thuoc = new Thuoc(rs.getInt("MaThuoc"));
				
				MaGiamGia giamgia = new MaGiamGia(ma ,  ngayBD , ngayKT , ptGiamGia , mota, thuoc);
				dsGG.add(giamgia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsGG;
	}
	public boolean xoaTheoMa(int ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Thuoc where MaGiamGia = ?";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ma);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return n > 0;

	}
	public boolean themMaGiamGia(MaGiamGia mgg) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " MaGiamGia values( ?, ?, ?, ?)");

			stmt.setDate(1, Date.valueOf(mgg.getNgayBatDau()));
			stmt.setDate(2, Date.valueOf(mgg.getNgayKetThuc()));
			stmt.setDouble(3, mgg.getPhanTramGiamGia());
			stmt.setString(4, mgg.getMoTa());
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

}
