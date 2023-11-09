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
import entity.NhanVien;
import entity.NhomThuoc;
import entity.Quay;

public class NhanVienDao {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT * FROM NhanVien";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);


			while (rs.next()) {
				int maNhanVien = rs.getInt("MaNhanVien");
				String tenNhanVien = rs.getString("TenNhanVien");
				LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
				String caLamViec = rs.getString("CaLamViec");
				String soDienThoai = rs.getString("SoDienThoai");

				String matKhau = rs.getString("MatKhau");
				Quay quay = new Quay(rs.getInt("MaQuay"));
				String chucVu = rs.getString("ChucVu");
				
				dsNhanVien.add(new NhanVien(maNhanVien, tenNhanVien, ngayVaoLam, caLamViec, soDienThoai, matKhau, quay, chucVu));

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
			stmt = con.prepareStatement("insert into" + " NhanVien values(?, ?, ?, ?)");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setDate(2, Date.valueOf(nv.getNgayVaoLam()));
			stmt.setString(4, nv.getCaLamViec());
			stmt.setString(5, nv.getSoDienThoai());
			;
			n = stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public NhanVien getNhanVienBySdtPwd(String sdt, String ma) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM [NhanVien] WHERE [SoDienThoai] = ? AND [MatKhau] = ?");
			stmt.setString(1, sdt);
			stmt.setString(2, ma);
			;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maNhanVien = rs.getInt("MaNhanVien");
				String tenNhanVien = rs.getString("TenNhanVien");
				LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
				String caLamViec = rs.getString("CaLamViec");
				String soDienThoai = rs.getString("SoDienThoai");

				//return new NhanVien(maNhanVien, tenNhanVien, ngayVaoLam, caLamViec, soDienThoai);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
