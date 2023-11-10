package entity;

public class NhaCungCap {
	private int maNhaCungCap;
	private String tenNhaCungCap;
	private String soDienThoai;
	private String diaChi;
	private String eMail;
	private String quocGia;
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhaCungCap(int maNhaCungCap, String tenNhaCungCap, String soDienThoai, String diaChi, String eMail,
			String quocGia) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.eMail = eMail;
		this.quocGia = quocGia;
	}
	
	
	
	public NhaCungCap(int maNhaCungCap) {
		super();
	    this.maNhaCungCap = maNhaCungCap;
	}
	
	public NhaCungCap(int maNhaCungCap, String tenNhaCungCap) {
		super();
	    this.maNhaCungCap = maNhaCungCap;
	    this.tenNhaCungCap = tenNhaCungCap;
	}
	public int getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(int maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getQuocGia() {
		return quocGia;
	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", soDienThoai="
				+ soDienThoai + ", diaChi=" + diaChi + ", eMail=" + eMail + ", quocGia=" + quocGia + "]";
	}
	
}