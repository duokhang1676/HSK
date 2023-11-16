package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KhachHang {
	private int maKhachHang;
	private String tenKhachHang;
	private String soDienThoai;
	private LocalDate ngayTao;

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(int maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai, LocalDate ngayTao) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
	}

	public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
	}

	public KhachHang(String tenKhachHang, String soDienThoai, LocalDate ngayTao) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.ngayTao = ngayTao;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", soDienThoai="
				+ soDienThoai + ", ngayTao=" + ngayTao + "]";
	}

}
