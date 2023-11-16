package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.Kho;
import entity.NhaCungCap;
import entity.NhomThuoc;
import entity.Thuoc;

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
	
	
	public Kho getTop5NhomThuocBanChay(LocalDate from, LocalDate to) {
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT * FROM Kho RIGHT JOIN Thuoc ON Kho.MaThuoc = Thuoc.MaThuoc LEFT JOIN NhomThuoc ON Thuoc.MaNhomThuoc = NhomThuoc.MaNhomThuoc WHERE Kho.MaThuoc = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			
			
			
			
			//statement.setInt(1, maThuocParam);
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
}
