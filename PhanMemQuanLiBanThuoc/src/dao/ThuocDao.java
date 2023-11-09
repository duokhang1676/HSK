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
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NhomThuoc;
import entity.Thuoc;

public class ThuocDao {
	public ThuocDao() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Thuoc> getAllData() {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from Thuoc";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
				NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt("MaNhaCungCap"));
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
				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));
				MaGiamGia maGiamGia = new MaGiamGia(rs.getInt("MaGiamGia"));
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCungCap, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc, maGiamGia);
				dsThuoc.add(thuoc);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dsThuoc;
	}
	public boolean themThuoc(Thuoc t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " Thuoc values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1,t.getMaThuoc());
			stmt.setString(2,t.getTenThuoc());
//			stmt.setInt(3,maNCC);
			stmt.setString(4, t.getDonViTinh());
			stmt.setString(5, t.getThanhPhanChinh());
			stmt.setString(6, t.getDonViTinhLe());
			stmt.setDate(7, Date.valueOf(t.getHanSuDung()));
			stmt.setString(8, t.getDieuKienBaoQuan());
			stmt.setString(9, t.getDonViTinhChan());
			stmt.setString(10, t.getGhiChu());
			stmt.setDouble(11, t.getGiaNhapLe());
			stmt.setDouble(12, t.getGiaNhapChan());
			stmt.setDouble(13, t.getGiaBanLe());
			stmt.setDouble(14, t.getGiaBanChan());
			stmt.setInt(15, t.getNhomThuoc().getMaNhomThuoc());
			n = stmt.executeUpdate();						
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0; 
	}
	public boolean xoaTheoMa(int ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Thuoc where MaThuoc = ?";
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
	public Thuoc timThuocTheoMa(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from Thuoc where TenThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
				NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt("MaNhaCungCap"));
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
				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));
		
				MaGiamGia maGiamGia = new MaGiamGia(rs.getInt("MaGiamGia"));
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCungCap, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc, maGiamGia);
				return thuoc;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	public ArrayList<Thuoc> locThuocTheoTenNhom(int ma) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select * from Thuoc where MaNhomThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
				NhaCungCap nhaCungCap = new NhaCungCap(rs.getInt("MaNhaCungCap"));
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
				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));
				MaGiamGia maGiamGia = new MaGiamGia(rs.getInt("MaGiamGia"));
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCungCap, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc, maGiamGia);
				dsThuoc.add(thuoc);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dsThuoc;
	}
}
