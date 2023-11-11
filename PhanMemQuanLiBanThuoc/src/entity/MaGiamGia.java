package entity;

import java.time.LocalDate;

public class MaGiamGia {
	private int maGiamGia;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private double phanTramGiamGia;
	private String moTa;
	private Thuoc thuoc;

	public MaGiamGia(int maGiamGia) {
		super();
		this.maGiamGia = maGiamGia;
	}

	public MaGiamGia(int maGiamGia, LocalDate ngayBatDau, LocalDate ngayKetThuc, double phanTramGiamGia, String moTa,
			Thuoc thuoc) {
		super();
		this.maGiamGia = maGiamGia;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.phanTramGiamGia = phanTramGiamGia;
		this.moTa = moTa;
		this.thuoc = thuoc;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public MaGiamGia(int maGiamGia, double phanTramGiamGia) {
		super();
		this.maGiamGia = maGiamGia;
		this.phanTramGiamGia = phanTramGiamGia;
	}

	public MaGiamGia(int maGiamGia, String moTa) {
		super();
		this.maGiamGia = maGiamGia;
		this.moTa = moTa;
	}

	public int getMaGiamGia() {
		return maGiamGia;
	}

	public void setMaGiamGia(int maGiamGia) {
		this.maGiamGia = maGiamGia;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public double getPhanTramGiamGia() {
		return phanTramGiamGia;
	}

	public void setPhanTramGiamGia(double phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public MaGiamGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MaGiamGia [maGiamGia=" + maGiamGia + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", phanTramGiamGia=" + phanTramGiamGia + ", moTa=" + moTa + "]";
	}

}
