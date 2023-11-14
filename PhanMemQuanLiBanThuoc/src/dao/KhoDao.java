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
import entity.Kho;
import entity.NhaCungCap;
import entity.NhomThuoc;
import entity.Thuoc;

public class KhoDao {
	public ArrayList<Kho> getAllTonKhoTheoThuoc() {
		ArrayList<Kho> dsKho = new ArrayList<Kho>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT * FROM Kho RIGHT JOIN Thuoc ON Kho.MaThuoc = Thuoc.MaThuoc LEFT JOIN NhomThuoc ON Thuoc.MaNhomThuoc = NhomThuoc.MaNhomThuoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
				NhaCungCap nhaCC = new NhaCungCap(rs.getInt("MaNhaCungCap"));
				String donViTinh = rs.getString("DonViTinh");
				String thanhPhanChinh = rs.getString("ThanhPhanChinh");
				String donViTinhLe = rs.getString("DonViTinhLe");
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				String dkBaoQuan = rs.getString("DieuKienBaoQuan");
				String donViTinhChan = rs.getString("DonViTinhChan");
				String ghiChu = rs.getString("GhiChu");
				double giaNhapLe = rs.getDouble("GiaNhapLe");
				double giaNhapChan = rs.getDouble("GiaNhapChan");
				double giaBanLe = rs.getDouble("GiaBanLe");
				double giaBanChan = rs.getDouble("GiaBanChan");

				int soLuong = rs.getInt("SoLuong");

				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung,
						dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
				Kho kho = new Kho(soLuong, thuoc);

				dsKho.add(kho);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKho;
	}
	
	public Kho getTonKhoTheoThuoc(int maThuocParam) {
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT * FROM Kho RIGHT JOIN Thuoc ON Kho.MaThuoc = Thuoc.MaThuoc LEFT JOIN NhomThuoc ON Thuoc.MaNhomThuoc = NhomThuoc.MaNhomThuoc WHERE Kho.MaThuoc = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			
			
			statement.setInt(1, maThuocParam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
				NhaCungCap nhaCC = new NhaCungCap(rs.getInt("MaNhaCungCap"));
				String donViTinh = rs.getString("DonViTinh");
				String thanhPhanChinh = rs.getString("ThanhPhanChinh");
				String donViTinhLe = rs.getString("DonViTinhLe");
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				String dkBaoQuan = rs.getString("DieuKienBaoQuan");
				String donViTinhChan = rs.getString("DonViTinhChan");
				String ghiChu = rs.getString("GhiChu");
				double giaNhapLe = rs.getDouble("GiaNhapLe");
				double giaNhapChan = rs.getDouble("GiaNhapChan");
				double giaBanLe = rs.getDouble("GiaBanLe");
				double giaBanChan = rs.getDouble("GiaBanChan");

				int soLuong = rs.getInt("SoLuong");

				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung,
						dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
				return new Kho(soLuong, thuoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateSoLuongTonKho(int maThuocParam, int soLuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE Kho SET SoLuong = ? WHERE MaThuoc = ?");

			stmt.setInt(1, soLuong);
			stmt.setInt(2, maThuocParam);
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
