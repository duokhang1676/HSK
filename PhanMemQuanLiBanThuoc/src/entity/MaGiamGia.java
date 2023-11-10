package entity;

import java.time.LocalDate;
import java.util.Objects;

public class MaGiamGia {
	private int maGiamGia;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private double phanTramGiamGia;
	private String moTa;
	public MaGiamGia(int maGiamGia, LocalDate ngayBatDau, LocalDate ngayKetThuc, double phanTramGiamGia, String moTa) {
		super();
		this.maGiamGia = maGiamGia;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.phanTramGiamGia = phanTramGiamGia;
		this.moTa = moTa;
	}
	public MaGiamGia(int maGiamGia) {
		super();
		this.maGiamGia = maGiamGia;
	}
	public MaGiamGia() {
		super();
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
	@Override
	public int hashCode() {
		return Objects.hash(maGiamGia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaGiamGia other = (MaGiamGia) obj;
		return maGiamGia == other.maGiamGia;
	}
	@Override
	public String toString() {
		return "MaGiamGia [maGiamGia=" + maGiamGia + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", phanTramGiamGia=" + phanTramGiamGia + ", moTa=" + moTa + "]";
	}
	
	
}