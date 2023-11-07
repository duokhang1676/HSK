package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhomThuoc;
import entity.Quay;
import entity.Thuoc;

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
				
				Quay quay = new Quay(maQuay, tenQuay, diaChi, diaChi, thanhPho, tinh);
				dsQuay.add(quay);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dsQuay;
	}
}
