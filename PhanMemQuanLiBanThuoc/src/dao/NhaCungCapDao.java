package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.NhaCungCap;
import entity.NhomThuoc;
import entity.Thuoc;

public class NhaCungCapDao {
	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "SELECT * FROM NhaCungCap";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				int maNCC = rs.getInt("MaNhaCungCap");
				String tenNCC = rs.getString("TenNhaCungCap");
				String sdtNCC = rs.getString("SoDienThoai");
				String diachiNCC = rs.getString("DiaChi");
				String emailNCC = rs.getString("Email");
				String quocGis = rs.getString("QuocGia");

				dsNhaCungCap.add(new NhaCungCap(maNCC, tenNCC, sdtNCC, diachiNCC, emailNCC, quocGis));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsNhaCungCap;
	}
	
	public NhaCungCap timNhaCungCapTheoTen(String tenNhaCungCapParam) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT * FROM [dbo].[NhaCungCap] WHERE [TenNhaCungCap] = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenNhaCungCapParam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				int maNhaCungCap = rs.getInt("MaNhaCungCap");
				String tenNhaCungCap = rs.getString("TenNhaCungCap");
				String soDienThoai = rs.getString("SoDienThoai");
				String diaChi = rs.getString("DiaChi");
				String email = rs.getString("Email");
				String quocGia = rs.getString("QuocGia");
				
				return new NhaCungCap(maNhaCungCap, tenNhaCungCap, soDienThoai, diaChi, email, quocGia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
