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
import entity.KhachHang;
import entity.NhanVien;
import entity.NhomThuoc;
import entity.Quay;

public class NhanVienDao {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT [MaNhanVien], [TenNhanVien], [NgayVaoLam], [SoDienThoai], [CaLamViec], [ChucVu] ,[NhanVien].[MaQuay], [TenQuay]  FROM NhanVien LEFT JOIN Quay ON [NhanVien].[MaQuay] = [dbo].[Quay].[MaQuay]";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				int maNhanVien = rs.getInt("MaNhanVien");
				String tenNhanVien = rs.getString("TenNhanVien");
				LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
				String caLamViec = rs.getString("CaLamViec");
				String soDienThoai = rs.getString("SoDienThoai");

				int maQuay = rs.getInt("MaQuay");
				String tenQuay = rs.getString("TenQuay");
				String chucVu = rs.getString("ChucVu");

				dsNhanVien.add(new NhanVien(maNhanVien, tenNhanVien, ngayVaoLam, caLamViec, soDienThoai,
						new Quay(maQuay, tenQuay), chucVu));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsNhanVien;
	}

	public boolean addNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " NhanVien values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setDate(2, Date.valueOf(nv.getNgayVaoLam()));
			stmt.setString(3, nv.getCaLamViec());
			stmt.setString(4, nv.getSoDienThoai());
			stmt.setString(5, nv.getMatKhau());
			stmt.setInt(6, nv.getQuay().getMaQuay());
			stmt.setString(7, nv.getChucVu());

			n = stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaNhanVienTheoMa(int maNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM NhanVien WHERE MaNhanVien = ?");
			stmt.setInt(1, maNhanVien);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public NhanVien getNhanVienBySdtPwd(String sdt, String ma) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			PreparedStatement stmt = con.prepareStatement(
					"  SELECT * FROM [NhanVien] \r\n" + "  INNER JOIN Quay ON Quay.MaQuay = NhanVien.MaQuay\r\n"
							+ "  WHERE [SoDienThoai] = ? AND [MatKhau] = ?");
			stmt.setString(1, sdt);
			stmt.setString(2, ma);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maNhanVien = rs.getInt("MaNhanVien");
				String tenNhanVien = rs.getString("TenNhanVien");
				LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
				String caLamViec = rs.getString("CaLamViec");
				String soDienThoai = rs.getString("SoDienThoai");
				String matKhau = rs.getString("MatKhau");
				String chucVu = rs.getString("ChucVu");

				int maQuay = rs.getInt("MaQuay");
				String tenQuay = rs.getString("TenQuay");
				Quay quay = new Quay(maQuay, tenQuay);

				return new NhanVien(maNhanVien, tenNhanVien, ngayVaoLam, caLamViec, soDienThoai, matKhau, quay, chucVu);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean suaThongTinNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE NhanVien " + "SET TenNhanVien = ?, CaLamViec = ?, SoDienThoai = ?, MaQuay = ?, ChucVu = ?"
							+ " WHERE MaNhanVien = ?");

			stmt.setString(1, nhanVien.getTenNhanVien());
			stmt.setString(2, nhanVien.getCaLamViec());
			stmt.setString(3, nhanVien.getSoDienThoai());
			stmt.setInt(4, nhanVien.getQuay().getMaQuay());
			stmt.setString(5, nhanVien.getChucVu());
			stmt.setInt(6, nhanVien.getMaNhanVien());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doiMatKhau(int maNhanVien, String password) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE NhanVien " + "SET MatKhau = ?"
							+ " WHERE MaNhanVien = ?");

			stmt.setString(1, password);
			stmt.setInt(2, maNhanVien);
			
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
