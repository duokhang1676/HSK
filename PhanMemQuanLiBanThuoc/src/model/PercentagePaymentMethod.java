package model;

public class PercentagePaymentMethod {
	private String phuongThucThanhToan;
	private int soLuongDonHang;

	public PercentagePaymentMethod(String phuongThucThanhToan, int soLuongDonHang) {
		super();
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.soLuongDonHang = soLuongDonHang;
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public int getSoLuongDonHang() {
		return soLuongDonHang;
	}

	public void setSoLuongDonHang(int soLuongDonHang) {
		this.soLuongDonHang = soLuongDonHang;
	}
}
