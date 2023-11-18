package model;

public class TopThuocBanChay {
	private int maThuoc;
	private String tenThuoc;
	private int soLuongBanRa;

	public TopThuocBanChay(int maThuoc, String tenThuoc, int soLuongBanRa) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.soLuongBanRa = soLuongBanRa;
	}

	public int getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public int getSoLuongBanRa() {
		return soLuongBanRa;
	}

	public void setSoLuongBanRa(int soLuongBanRa) {
		this.soLuongBanRa = soLuongBanRa;
	}
}
