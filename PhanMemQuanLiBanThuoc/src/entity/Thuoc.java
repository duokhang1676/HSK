package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Thuoc {
	private int MaThuoc;
	private String TenThuoc;
	private NhaCungCap nhaCungCap;
	private String DonViTinh;
	private String ThanhPhanChinh;
	private String DonViTinhLe;
	private LocalDate HanSuDung;
	private String DieuKienBaoQuan;
	private String DonViTinhChan;
	private String GhiChu;
	private double GiaNhapLe;
	private double GiaNhapChan;
	private double GiaBanLe;
	private double GiaBanChan;
	private NhomThuoc nhomThuoc;
	public Thuoc() {
		super();
	}
	public Thuoc(int maThuoc, String tenThuoc, NhaCungCap nhaCungCap, String donViTinh, String thanhPhanChinh,
			String donViTinhLe, LocalDate hanSuDung, String dieuKienBaoQuan, String donViTinhChan, String ghiChu,
			double giaNhapLe, double giaNhapChan, double giaBanLe, double giaBanChan, NhomThuoc nhomThuoc) {
		super();
		MaThuoc = maThuoc;
		TenThuoc = tenThuoc;
		this.nhaCungCap = nhaCungCap;
		DonViTinh = donViTinh;
		ThanhPhanChinh = thanhPhanChinh;
		DonViTinhLe = donViTinhLe;
		HanSuDung = hanSuDung;
		DieuKienBaoQuan = dieuKienBaoQuan;
		DonViTinhChan = donViTinhChan;
		GhiChu = ghiChu;
		GiaNhapLe = giaNhapLe;
		GiaNhapChan = giaNhapChan;
		GiaBanLe = giaBanLe;
		GiaBanChan = giaBanChan;
		this.nhomThuoc = nhomThuoc;
	}
	public Thuoc(int maThuoc) {
		super();
		MaThuoc = maThuoc;
	}
	public int getMaThuoc() {
		return MaThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		MaThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return TenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		TenThuoc = tenThuoc;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public String getDonViTinh() {
		return DonViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		DonViTinh = donViTinh;
	}
	public String getThanhPhanChinh() {
		return ThanhPhanChinh;
	}
	public void setThanhPhanChinh(String thanhPhanChinh) {
		ThanhPhanChinh = thanhPhanChinh;
	}
	public String getDonViTinhLe() {
		return DonViTinhLe;
	}
	public void setDonViTinhLe(String donViTinhLe) {
		DonViTinhLe = donViTinhLe;
	}
	public LocalDate getHanSuDung() {
		return HanSuDung;
	}
	public void setHanSuDung(LocalDate hanSuDung) {
		HanSuDung = hanSuDung;
	}
	public String getDieuKienBaoQuan() {
		return DieuKienBaoQuan;
	}
	public void setDieuKienBaoQuan(String dieuKienBaoQuan) {
		DieuKienBaoQuan = dieuKienBaoQuan;
	}
	public String getDonViTinhChan() {
		return DonViTinhChan;
	}
	public void setDonViTinhChan(String donViTinhChan) {
		DonViTinhChan = donViTinhChan;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	public double getGiaNhapLe() {
		return GiaNhapLe;
	}
	public void setGiaNhapLe(double giaNhapLe) {
		GiaNhapLe = giaNhapLe;
	}
	public double getGiaNhapChan() {
		return GiaNhapChan;
	}
	public void setGiaNhapChan(double giaNhapChan) {
		GiaNhapChan = giaNhapChan;
	}
	public double getGiaBanLe() {
		return GiaBanLe;
	}
	public void setGiaBanLe(double giaBanLe) {
		GiaBanLe = giaBanLe;
	}
	public double getGiaBanChan() {
		return GiaBanChan;
	}
	public void setGiaBanChan(double giaBanChan) {
		GiaBanChan = giaBanChan;
	}
	public NhomThuoc getNhomThuoc() {
		return nhomThuoc;
	}
	public void setNhomThuoc(NhomThuoc nhomThuoc) {
		this.nhomThuoc = nhomThuoc;
	}
	@Override
	public String toString() {
		return "Thuoc [MaThuoc=" + MaThuoc + ", TenThuoc=" + TenThuoc + ", DonViTinh=" + DonViTinh + ", ThanhPhanChinh="
				+ ThanhPhanChinh + ", DonViTinhLe=" + DonViTinhLe + ", HanSuDung=" + HanSuDung + ", DieuKienBaoQuan="
				+ DieuKienBaoQuan + ", DonViTinhChan=" + DonViTinhChan + ", GhiChu=" + GhiChu + ", GiaNhapLe="
				+ GiaNhapLe + ", GiaNhapChan=" + GiaNhapChan + ", GiaBanLe=" + GiaBanLe + ", GiaBanChan=" + GiaBanChan
				+ ", nhomThuoc=" + nhomThuoc + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(MaThuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thuoc other = (Thuoc) obj;
		return MaThuoc == other.MaThuoc;
	}
	
	
}
