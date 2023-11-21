package entity;

import java.util.Objects;

public class ChiTietHoaDon {
	private int maCTHD;
	private int soLuong;
	private String donViTinh;
	private double donGia;
	private Thuoc sanPham;
	private double thue;
	private double thanhTien;
	private double giamGia;
	private HoaDon hoaDon;
	private MaGiamGia maGiamGia;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(int maCTHD) {
		super();
		this.maCTHD = maCTHD;
	}

	public ChiTietHoaDon(int maCTHD, int soLuong, String donViTinh, double donGia, Thuoc sanPham, double thue,
			double thanhTien, double giamGia, HoaDon hoaDon, MaGiamGia maGiamGia) {
		super();
		this.maCTHD = maCTHD;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.sanPham = sanPham;
		this.thue = thue;
		this.thanhTien = thanhTien;
		this.giamGia = giamGia;
		this.hoaDon = hoaDon;
		this.maGiamGia = maGiamGia;
	}

	public int getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public Thuoc getSanPham() {
		return sanPham;
	}

	public void setSanPham(Thuoc sanPham) {
		this.sanPham = sanPham;
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) {
		this.thue = thue;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public double getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(double giamGia) {
		this.giamGia = giamGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public MaGiamGia getMaGiamGia() {
		return maGiamGia;
	}

	public void setMaGiamGia(MaGiamGia maGiamGia) {
		this.maGiamGia = maGiamGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCTHD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return maCTHD == other.maCTHD;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [maCTHD=" + maCTHD + ", soLuong=" + soLuong + ", donViTinh=" + donViTinh + ", donGia="
				+ donGia + ", sanPham=" + sanPham + ", thue=" + thue + ", thanhTien=" + thanhTien + ", giamGia="
				+ giamGia + ", hoaDon=" + hoaDon + ", maGiamGia=" + maGiamGia + "]";
	}

}
