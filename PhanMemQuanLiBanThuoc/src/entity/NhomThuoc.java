package entity;

import java.util.Objects;

public class NhomThuoc {
	private int MaNhomThuoc;
	private String TenNhomThuoc;
	public NhomThuoc() {
		super();
	}
	public NhomThuoc(int maNhomThuoc, String tenNhomThuoc) {
		super();
		MaNhomThuoc = maNhomThuoc;
		TenNhomThuoc = tenNhomThuoc;
	}
	public NhomThuoc(int maNhomThuoc) {
		super();
		MaNhomThuoc = maNhomThuoc;
	}
	public int getMaNhomThuoc() {
		return MaNhomThuoc;
	}
	public void setMaNhomThuoc(int maNhomThuoc) {
		MaNhomThuoc = maNhomThuoc;
	}
	public String getTenNhomThuoc() {
		return TenNhomThuoc;
	}
	public void setTenNhomThuoc(String tenNhomThuoc) {
		TenNhomThuoc = tenNhomThuoc;
	}
	@Override
	public String toString() {
		return "NhomThuoc [MaNhomThuoc=" + MaNhomThuoc + ", TenNhomThuoc=" + TenNhomThuoc + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(MaNhomThuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhomThuoc other = (NhomThuoc) obj;
		return MaNhomThuoc == other.MaNhomThuoc;
	}
	
	
}
