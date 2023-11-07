package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import db.ConnectDB;
import entity.KhachHang;

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
				int maKH = rs.getInt("MaKhachHang");
				String ten = rs.getString("TenKhachHang");
				String sdt = rs.getString("SoDienThoai");
				LocalDateTime ngaytao = LocalDateTime.parse(rs.getString("NgayTao"));
				dsKhachHang.add(new KhachHang(maKH , ten , sdt , ngaytao));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsKhachHang;
	}

}
