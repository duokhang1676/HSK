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
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Quay;
import model.PercentagePaymentMethod;

public class HoaDonDao {
	public HoaDonDao() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<HoaDon> getAllTBHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT HoaDon.MaHoaDon, HoaDon.NgayLapHoaDon, HoaDon.TrangThai, HoaDon.PhuongThucThanhToan,\r\n"
					+ "       HoaDon.TienNhan, HoaDon.TienDu, HoaDon.TongTienGiam, HoaDon.TongTien,\r\n"
					+ "       HoaDon.MaNhanVien, HoaDon.MaQuay, HoaDon.MaKhachHang,\r\n"
					+ "       KhachHang.TenKhachHang, KhachHang.SoDienThoai AS SoDienThoaiKhachHang,\r\n"
					+ "       NhanVien.TenNhanVien ,\r\n"
					+ "       Quay.TenQuay\r\n"
					+ "FROM HoaDon\r\n"
					+ "LEFT JOIN KhachHang ON HoaDon.MaKhachHang = KhachHang.MaKhachHang\r\n"
					+ "LEFT JOIN NhanVien ON HoaDon.MaNhanVien = NhanVien.MaNhanVien\r\n"
					+ "LEFT JOIN Quay ON HoaDon.MaQuay = Quay.MaQuay;\r\n"
					+ "";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt("MaHoaDon");
				LocalDate ngayLapHD = rs.getDate("NgayLapHoaDon").toLocalDate();
				String trangThai = rs.getString("TrangThai");
				String phuongThucTT = rs.getString("PhuongThucThanhToan");
				double tienNhan = rs.getDouble("TienNhan");
				double tienDu = rs.getDouble("TienDu");
				KhachHang kh = new KhachHang(rs.getInt("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("SoDienThoaiKhachHang"));
				NhanVien nv = new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), LocalDate.now(), "", "", new Quay(0), "");
				Quay quay = new Quay(rs.getInt("MaQuay"), rs.getString("TenQuay"));
				double tongTienGiam = rs.getDouble("TongTienGiam");
				double tongTien = rs.getDouble("TongTien");
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	
	public ArrayList<HoaDon> getHoaDonTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT HoaDon.MaHoaDon, HoaDon.NgayLapHoaDon, HoaDon.TrangThai, HoaDon.PhuongThucThanhToan,\r\n"
					+ "       HoaDon.TienNhan, HoaDon.TienDu, HoaDon.TongTienGiam, HoaDon.TongTien,\r\n"
					+ "       HoaDon.MaNhanVien, HoaDon.MaQuay, HoaDon.MaKhachHang,\r\n"
					+ "       KhachHang.TenKhachHang, KhachHang.SoDienThoai AS SoDienThoaiKhachHang,\r\n"
					+ "       NhanVien.TenNhanVien ,\r\n"
					+ "       Quay.TenQuay\r\n"
					+ "FROM HoaDon\r\n"
					+ "LEFT JOIN KhachHang ON HoaDon.MaKhachHang = KhachHang.MaKhachHang\r\n"
					+ "LEFT JOIN NhanVien ON HoaDon.MaNhanVien = NhanVien.MaNhanVien\r\n"
					+ "LEFT JOIN Quay ON HoaDon.MaQuay = Quay.MaQuay\r\n"
					+ "WHERE HoaDon.NgayLapHoaDon >= ? and HoaDon.NgayLapHoaDon <= ?";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(tuNgay));
			stmt.setDate(2, Date.valueOf(denNgay));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ma = rs.getInt("MaHoaDon");
				LocalDate ngayLapHD = rs.getDate("NgayLapHoaDon").toLocalDate();
				String trangThai = rs.getString("TrangThai");
				String phuongThucTT = rs.getString("PhuongThucThanhToan");
				double tienNhan = rs.getDouble("TienNhan");
				double tienDu = rs.getDouble("TienDu");
				KhachHang kh = new KhachHang(rs.getInt("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("SoDienThoaiKhachHang"));
				NhanVien nv = new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), LocalDate.now(), "", "", new Quay(0), "");
				Quay quay = new Quay(rs.getInt("MaQuay"), rs.getString("TenQuay"));
				double tongTienGiam = rs.getDouble("TongTienGiam");
				double tongTien = rs.getDouble("TongTien");
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	
	
	public ArrayList<HoaDon> getHoaDonTheoNgayTu(LocalDate tuNgay) {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT HoaDon.MaHoaDon, HoaDon.NgayLapHoaDon, HoaDon.TrangThai, HoaDon.PhuongThucThanhToan,\r\n"
					+ "       HoaDon.TienNhan, HoaDon.TienDu, HoaDon.TongTienGiam, HoaDon.TongTien,\r\n"
					+ "       HoaDon.MaNhanVien, HoaDon.MaQuay, HoaDon.MaKhachHang,\r\n"
					+ "       KhachHang.TenKhachHang, KhachHang.SoDienThoai AS SoDienThoaiKhachHang,\r\n"
					+ "       NhanVien.TenNhanVien ,\r\n"
					+ "       Quay.TenQuay\r\n"
					+ "FROM HoaDon\r\n"
					+ "LEFT JOIN KhachHang ON HoaDon.MaKhachHang = KhachHang.MaKhachHang\r\n"
					+ "LEFT JOIN NhanVien ON HoaDon.MaNhanVien = NhanVien.MaNhanVien\r\n"
					+ "LEFT JOIN Quay ON HoaDon.MaQuay = Quay.MaQuay\r\n"
					+ "WHERE HoaDon.NgayLapHoaDon >= ?";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(tuNgay));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ma = rs.getInt("MaHoaDon");
				LocalDate ngayLapHD = rs.getDate("NgayLapHoaDon").toLocalDate();
				String trangThai = rs.getString("TrangThai");
				String phuongThucTT = rs.getString("PhuongThucThanhToan");
				double tienNhan = rs.getDouble("TienNhan");
				double tienDu = rs.getDouble("TienDu");
				KhachHang kh = new KhachHang(rs.getInt("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("SoDienThoaiKhachHang"));
				NhanVien nv = new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), LocalDate.now(), "", "", new Quay(0), "");
				Quay quay = new Quay(rs.getInt("MaQuay"), rs.getString("TenQuay"));
				double tongTienGiam = rs.getDouble("TongTienGiam");
				double tongTien = rs.getDouble("TongTien");
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	
	public ArrayList<HoaDon> getHoaDonTheoNgayDen(LocalDate denNgay) {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT HoaDon.MaHoaDon, HoaDon.NgayLapHoaDon, HoaDon.TrangThai, HoaDon.PhuongThucThanhToan,\r\n"
					+ "       HoaDon.TienNhan, HoaDon.TienDu, HoaDon.TongTienGiam, HoaDon.TongTien,\r\n"
					+ "       HoaDon.MaNhanVien, HoaDon.MaQuay, HoaDon.MaKhachHang,\r\n"
					+ "       KhachHang.TenKhachHang, KhachHang.SoDienThoai AS SoDienThoaiKhachHang,\r\n"
					+ "       NhanVien.TenNhanVien ,\r\n"
					+ "       Quay.TenQuay\r\n"
					+ "FROM HoaDon\r\n"
					+ "LEFT JOIN KhachHang ON HoaDon.MaKhachHang = KhachHang.MaKhachHang\r\n"
					+ "LEFT JOIN NhanVien ON HoaDon.MaNhanVien = NhanVien.MaNhanVien\r\n"
					+ "LEFT JOIN Quay ON HoaDon.MaQuay = Quay.MaQuay\r\n"
					+ "WHERE  HoaDon.NgayLapHoaDon <= ?";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(denNgay));
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ma = rs.getInt("MaHoaDon");
				LocalDate ngayLapHD = rs.getDate("NgayLapHoaDon").toLocalDate();
				String trangThai = rs.getString("TrangThai");
				String phuongThucTT = rs.getString("PhuongThucThanhToan");
				double tienNhan = rs.getDouble("TienNhan");
				double tienDu = rs.getDouble("TienDu");
				KhachHang kh = new KhachHang(rs.getInt("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("SoDienThoaiKhachHang"));
				NhanVien nv = new NhanVien(rs.getInt("MaNhanVien"), rs.getString("TenNhanVien"), LocalDate.now(), "", "", new Quay(0), "");
				Quay quay = new Quay(rs.getInt("MaQuay"), rs.getString("TenQuay"));
				double tongTienGiam = rs.getDouble("TongTienGiam");
				double tongTien = rs.getDouble("TongTien");
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	

	public HoaDon themHoaDon(HoaDon hd) {
		int affectedRows = 0;
		PreparedStatement statement = null;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Insert into HoaDon values(?,?,?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, Date.valueOf(hd.getNgayLapHD()));
			statement.setString(2, hd.getTrangThai());
			statement.setString(3, hd.getPhuongThucThanhToan());
			statement.setDouble(4, hd.getTienNhan());
			statement.setDouble(5, hd.getTienDu());
			statement.setInt(6, hd.getKhachHang().getMaKhachHang());
			statement.setInt(7, hd.getNhanVien().getMaNhanVien());
			statement.setInt(8, hd.getQuay().getMaQuay());
			statement.setDouble(9, hd.getTongTienGiam());
			statement.setDouble(10, hd.getTongTien());
			affectedRows = statement.executeUpdate();

			if (affectedRows > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					hd.setMaHD(generatedKeys.getInt(1));
					return hd;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<PercentagePaymentMethod> getPercentageOfMethodPayment() {
		ArrayList<PercentagePaymentMethod> dsPers = new ArrayList<PercentagePaymentMethod>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT [PhuongThucThanhToan], COUNT([PhuongThucThanhToan]) AS SoLuongDonHang\r\n"
					+ "FROM [dbo].[HoaDon]\r\n" + "GROUP BY [PhuongThucThanhToan]";

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
				int soLuongDonHang = rs.getInt("SoLuongDonHang");

				dsPers.add(new PercentagePaymentMethod(phuongThucThanhToan, soLuongDonHang));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPers;
	}

	public int getTongSoDonHang(LocalDate from, LocalDate to) {
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) AS TongSoHoaDon FROM HoaDon " + "WHERE NgayLapHoaDon BETWEEN ? AND ?";

			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setDate(1, Date.valueOf(from));
			statement.setDate(2, Date.valueOf(to));
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt("TongSoHoaDon");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	} 

	
	public long getTongDoanhThu(LocalDate from, LocalDate to) {
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT SUM(TongTien) AS TongDoanhThu FROM HoaDon " + "WHERE NgayLapHoaDon BETWEEN ? AND ?";

			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setDate(1, Date.valueOf(from));
			statement.setDate(2, Date.valueOf(to));
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				return rs.getLong("TongDoanhThu");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	} 
}
