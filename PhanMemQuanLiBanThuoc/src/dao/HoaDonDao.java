package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


import db.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Quay;

public class HoaDonDao {
	public ArrayList<HoaDon> getAllTBHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "Select * from HoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt(0);
				LocalDate ngayLapHD = rs.getDate(1).toLocalDate();
				String trangThai = rs.getString(2);
				String phuongThucTT = rs.getString(3);
				double tienNhan = rs.getDouble(4);
				double tienDu = rs.getDouble(5);
				KhachHang kh = new KhachHang(rs.getInt(6));
				NhanVien nv = new NhanVien(rs.getInt(7));
				Quay quay = new Quay(rs.getInt(8));
				double tongTienGiam = rs.getDouble(9);
				double tongTien = rs.getDouble(10);
				HoaDon hd = new HoaDon(ma, ngayLapHD, trangThai, phuongThucTT, tienNhan, tienDu, kh, nv, quay,
						tongTienGiam, tongTien);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	public boolean themHoaDon(HoaDon hd) throws Exception {
		int n = 0;
		PreparedStatement statement = null;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Insert into HoaDon values(?,?,?,?,?,?,?,?,?,?,?)";
		statement = con.prepareStatement(sql);
		statement.setInt(1, hd.getMaHD());
		statement.setDate(2, Date.valueOf(hd.getNgayLapHD()));
		statement.setString(3, hd.getTrangThai());
		statement.setString(4, hd.getPhuongThucThanhToan());
		statement.setDouble(5, hd.getTienNhan());
		statement.setDouble(6, hd.getTienDu());
		statement.setInt(7, hd.getKhachHang().getMaKhachHang());
		statement.setInt(8, hd.getNhanVien().getMaNhanVien());
		statement.setInt(9, hd.getQuay().getMaQuay());
		statement.setDouble(10, hd.getTongTienGiam());
		statement.setDouble(11, hd.getTongTien());
		n = statement.executeUpdate();

		return n > 0;
	}
}
