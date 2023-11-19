package model;

public class TopSaleInCategory {
	private String categoryName;
	private long soLuongBanRa;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getSoLuongBanRa() {
		return soLuongBanRa;
	}

	public void setSoLuongBanRa(long soLuongBanRa) {
		this.soLuongBanRa = soLuongBanRa;
	}

	public TopSaleInCategory(String categoryName, long soLuongBanRa) {
		super();
		this.categoryName = categoryName;
		this.soLuongBanRa = soLuongBanRa;
	}

}
