package components;

import entity.NhanVien;

public class TaiKhoanDangNhap {
	public static NhanVien nv;
	public static void setNV(NhanVien nhanVien) {
		nv = nhanVien;
	}
	public static NhanVien getNV() {
		return nv;
	}
}
