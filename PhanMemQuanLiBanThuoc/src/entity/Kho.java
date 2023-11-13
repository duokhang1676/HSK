package entity;

public class Kho {
	private int soLuong;
	private Thuoc thuoc;
	public Kho() {
		super();
		// TODO Auto-generated constructor stub
		soLuong = 0;
	}
	public Kho(int soLuong) {
		super();
		this.soLuong = soLuong;
	}
	public Kho(int soLuong, Thuoc thuoc) {
		super();
		this.soLuong = soLuong;
		this.thuoc = thuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		if (!(soLuong >= 0)) {
			this.soLuong = soLuong;
		}else {
			this.soLuong = 0;
		}
	}
	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	@Override
	public String toString() {
		return "[Soluong=" + soLuong + ", Thuoc=" + thuoc + "]";
	}
}
