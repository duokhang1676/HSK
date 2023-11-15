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
//				NhaCungCap nhaCC = new Nha
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
				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));

				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung,
						dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
				dsThuoc.add(thuoc);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dsThuoc;
	}

	public Thuoc themThuoc(Thuoc t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int affectedRows = 0;
		try {
			stmt = con.prepareStatement("insert into" + " Thuoc values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
//			stmt.setInt(1,t.getMaThuoc());
			stmt.setString(1, t.getTenThuoc());
			stmt.setInt(2, t.getNhaCungCap().getMaNhaCungCap());
			stmt.setString(3, t.getDonViTinh());
			stmt.setString(4, t.getThanhPhanChinh());
			stmt.setString(5, t.getDonViTinhLe());
			stmt.setDate(6, Date.valueOf(t.getHanSuDung()));
			stmt.setString(7, t.getDieuKienBaoQuan());
			stmt.setString(8, t.getDonViTinhChan());
			stmt.setString(9, t.getGhiChu());
			stmt.setDouble(10, t.getGiaNhapLe());
			stmt.setDouble(11, t.getGiaNhapChan());
			stmt.setDouble(12, t.getGiaBanLe());
			stmt.setDouble(13, t.getGiaBanChan());
			stmt.setInt(14, t.getNhomThuoc().getMaNhomThuoc());
			affectedRows = stmt.executeUpdate();

			if (affectedRows > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					t.setMaThuoc(generatedKeys.getInt(1));
					return t;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			return stmt.executeUpdate()>0;
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

		return false;

	}

	public Thuoc timThuocTheoTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "Select \r\n" + "MaThuoc, \r\n" + "TenThuoc, \r\n" + "ThanhPhanChinh, \r\n" + "HanSuDung, \r\n"
					+ "DieuKienBaoQuan, \r\n" + "DonViTinh, \r\n" + "DonViTinhLe, \r\n" + "DonViTinhChan, \r\n"
					+ "GiaNhapLe, \r\n" + "GiaNhapChan, \r\n" + "GiaBanLe, \r\n" + "GiaNhapChan, \r\n"
					+ "GiaBanChan, \r\n" + "GhiChu,\r\n" + "[Thuoc].MaNhomThuoc,\r\n" + "TenNhomThuoc,\r\n"
					+ "[Thuoc].MaNhaCungCap,\r\n" + "[NhaCungCap].[TenNhaCungCap]\r\n" + "FROM Thuoc \r\n"
					+ "LEFT JOIN [NhomThuoc] ON [Thuoc].MaNhomThuoc = [NhomThuoc].[MaNhomThuoc] \r\n"
					+ "LEFT JOIN [NhaCungCap] ON [Thuoc].MaNhaCungCap = [NhaCungCap].MaNhaCungCap\r\n"
					+ "where TenThuoc = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maThuoc = rs.getInt("MaThuoc");
				String tenThuoc = rs.getString("TenThuoc");
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

				int maNhomThuoc = rs.getInt("MaNhomThuoc");
				String tenNhomThuoc = rs.getString("TenNhomThuoc");
				NhomThuoc nhomThuoc = new NhomThuoc(maNhomThuoc, tenNhomThuoc);

				int maNhaCungCap = rs.getInt("MaNhaCungCap");
				String tenNhaCungCap = rs.getString("TenNhaCungCap");
				NhaCungCap nhaCC = new NhaCungCap(maNhaCungCap, tenNhaCungCap);

				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung,
						dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
				return thuoc;

			}
		} catch (Exception e) {
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
				NhomThuoc nhomThuoc = new NhomThuoc(rs.getInt("MaNhomThuoc"));

				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung,
						dkBaoQuan, donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
				dsThuoc.add(thuoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return dsThuoc;
	}

	public boolean suaThuoc(Thuoc t, int maThuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE Thuoc" + " SET " + "TenThuoc = ?, MaNhaCungCap = ?, DonViTinh = ?,"
					+ "ThanhPhanChinh = ?, DonViTinhLe = ?, HanSuDung = ?, DieuKienBaoQuan = ?, DonViTinhChan = ?,"
					+ "GhiChu = ?, GiaNhapLe = ?, GiaNhapChan = ?, GiaBanLe = ?, GiaBanChan = ?, MaNhomThuoc = ?"
					+ " where MaThuoc = ?");

			stmt.setString(1, t.getTenThuoc());
			stmt.setInt(2, t.getNhaCungCap().getMaNhaCungCap());
			stmt.setString(3, t.getDonViTinh());
			stmt.setString(4, t.getThanhPhanChinh());
			stmt.setString(5, t.getDonViTinhLe());
			stmt.setDate(6, Date.valueOf(t.getHanSuDung()));
			stmt.setString(7, t.getDieuKienBaoQuan());
			stmt.setString(8, t.getDonViTinhChan());
			stmt.setString(9, t.getGhiChu());
			stmt.setDouble(10, t.getGiaNhapLe());
			stmt.setDouble(11, t.getGiaNhapChan());
			stmt.setDouble(12, t.getGiaBanLe());
			stmt.setDouble(13, t.getGiaBanChan());
			stmt.setInt(14, t.getNhomThuoc().getMaNhomThuoc());
			stmt.setInt(15, maThuoc);
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

	
	public long getTongSoThuoc() {
		return getAllData().size();
	}
}
