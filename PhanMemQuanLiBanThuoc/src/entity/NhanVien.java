package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private int maNhanVien;
	private String tenNhanVien;
	private LocalDate ngayVaoLam;
	private String caLamViec;
	private String soDienThoai;
	private Quay quay;
	
	public NhanVien(
			int maNhanVien,
			String tenNhanVien, 
			LocalDate ngayVaoLam, 
			String caLamViec, 
			String soDienThoai, 
			Quay quay
			) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngayVaoLam = ngayVaoLam;
		this.caLamViec = caLamViec;
		this.soDienThoai = soDienThoai;
		this.quay = quay;
	}
	
	public NhanVien(int maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public Quay getQuay() {
		return quay;
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

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return maNhanVien == other.maNhanVien;
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

}
