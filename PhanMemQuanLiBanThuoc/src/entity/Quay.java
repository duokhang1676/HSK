package entity;

public class Quay {
	private int maQuay;
	private String tenQuay;
	private String diaChi;
	private String phuong;
	private String thanhPho;
	private String tinh;

	public Quay(int maQuay) {
		this.maQuay = maQuay;
	}
	
	public int getMaQuay() {
		return maQuay;
	}

	public void setMaQuay(int maQuay) {
		this.maQuay = maQuay;
	}

	public String getTenQuay() {
		return tenQuay;
	}

	public void setTenQuay(String tenQuay) {
		this.tenQuay = tenQuay;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getPhuong() {
		return phuong;
	}

	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}

	public String getTinh() {
		return tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	public Quay(int maQuay, String tenQuay, String diaChi, String phuong, String thanhPho, String tinh) {
		super();
		this.maQuay = maQuay;
		this.tenQuay = tenQuay;
		this.diaChi = diaChi;
		this.phuong = phuong;
		this.thanhPho = thanhPho;
		this.tinh = tinh;
	}

}
