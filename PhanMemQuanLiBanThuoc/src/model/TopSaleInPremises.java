package model;

public class TopSaleInPremises {
	private int maQuay;
	private String tenQuay;
	private double doanhThu;
	
	
	
	public TopSaleInPremises(int maQuay, String tenQuay, double doanhThu) {
		super();
		this.maQuay = maQuay;
		this.tenQuay = tenQuay;
		this.doanhThu = doanhThu;
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
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	
	
}
