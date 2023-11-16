package model;

public class TopSaleInCategory {
	private String categoryName;
	private double doanhThu;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}

	public TopSaleInCategory(String categoryName, double doanhThu) {
		super();
		this.categoryName = categoryName;
		this.doanhThu = doanhThu;
	}

}
