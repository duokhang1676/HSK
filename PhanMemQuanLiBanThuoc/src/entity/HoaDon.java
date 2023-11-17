package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
	private int maHD;
	private LocalDate ngayLapHD;
	private String trangThai;
	private String phuongThucThanhToan;
	private double tienNhan;
	private double tienDu;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Quay quay;
	private double tongTienGiam;
	private double tongTien;
	//private String ghiChu;

	public HoaDon() {
	}

	public HoaDon(int maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon(int maHD, LocalDate ngayLapHD, String trangThai, String phuongThucThanhToan, double tienNhan,
			double tienDu, KhachHang khachHang, NhanVien nhanVien, Quay quay, double tongTienGiam, double tongTien/*, String ghiChu*/) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.trangThai = trangThai;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.tienNhan = tienNhan;
		this.tienDu = tienDu;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.quay = quay;
		this.tongTienGiam = tongTienGiam;
		this.tongTien = tongTien;
		//this.ghiChu = ghiChu;
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public double getTienNhan() {
		return tienNhan;
	}

	public void setTienNhan(double tienNhan) {
		this.tienNhan = tienNhan;
	}

	public double getTienDu() {
		return tienDu;
	}

	public void setTienDu(double tienDu) {
		this.tienDu = tienDu;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Quay getQuay() {
		return quay;
	}

	public void setQuay(Quay quay) {
		this.quay = quay;
	}

	public double getTongTienGiam() {
		return tongTienGiam;
	}

	public void setTongTienGiam(double tongTienGiam) {
		this.tongTienGiam = tongTienGiam;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

//	public String getGhiChu() {
//		return ghiChu;
//	}
//
//	public void setGhiChu(String ghiChu) {
//		this.ghiChu = ghiChu;
//	}

	
}
