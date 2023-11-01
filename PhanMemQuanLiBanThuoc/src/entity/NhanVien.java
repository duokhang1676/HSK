package entity;

public class NhanVien {
	private int maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private boolean phai;
	private double luong;
	private PhongBan pBan;
	
	public NhanVien(int maNV, String ho, String ten, int tuoi, boolean phai, double luong, PhongBan pBan) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.luong = luong;
		this.pBan = pBan;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public boolean getPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public PhongBan getpBan() {
		return pBan;
	}

	public void setpBan(PhongBan pBan) {
		this.pBan = pBan;
	}
}
