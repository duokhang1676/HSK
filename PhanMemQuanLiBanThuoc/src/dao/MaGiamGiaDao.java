package dao;

import java.sql.Connection;
import java.sql.ResultSet;
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
}
