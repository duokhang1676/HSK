package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhomThuoc;

public class NhomThuocDao {
	public ArrayList<NhomThuoc> getAllData() {
		ArrayList<NhomThuoc> dsNhomThuoc = new ArrayList<NhomThuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from NhomThuoc";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int maThuoc = rs.getInt("MaNhomThuoc");
				String tenThuoc = rs.getString("TenNhomThuoc");
				NhomThuoc nThuoc = new NhomThuoc(maThuoc, tenThuoc);
				dsNhomThuoc.add(nThuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhomThuoc;
	}
}
