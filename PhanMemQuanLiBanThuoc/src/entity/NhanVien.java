package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private int maNhanVien;
	private String tenNhanVien;
	private LocalDate ngayVaoLam;
	private String caLamViec;
	private String soDienThoai;
	private String matKhau;
	private Quay quay;
	private String chucVu;
	
	public NhanVien(int maNhanVien, String tenNhanVien, LocalDate ngayVaoLam, String caLamViec, String soDienThoai, Quay quay, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngayVaoLam = ngayVaoLam;
		this.caLamViec = caLamViec;
		this.soDienThoai = soDienThoai;
		this.quay = quay;
		this.chucVu = chucVu;
	}
	
	public NhanVien(int maNhanVien, String tenNhanVien, LocalDate ngayVaoLam, String caLamViec, String soDienThoai,
			String matKhau, Quay quay, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngayVaoLam = ngayVaoLam;
		this.caLamViec = caLamViec;
		this.soDienThoai = soDienThoai;
		this.matKhau = matKhau;
		this.quay = quay;
		this.chucVu = chucVu;
	}
	
	
	public NhanVien(String tenNhanVien, LocalDate ngayVaoLam, String caLamViec, String soDienThoai,
			String matKhau, Quay quay, String chucVu) {
		
		// used for insert entity
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngayVaoLam = ngayVaoLam;
		this.caLamViec = caLamViec;
		this.soDienThoai = soDienThoai;
		this.matKhau = matKhau;
		this.quay = quay;
		this.chucVu = chucVu;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public String getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Quay getQuay() {
		return quay;
	}

	public void setQuay(Quay quay) {
		this.quay = quay;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public NhanVien(int maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	
	
}
	