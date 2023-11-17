package model;

public class IncomeInPeriod {
	private String yLabel;
	private long value;

	public IncomeInPeriod(String yLabel, long value) {
		super();
		this.yLabel = yLabel;
		this.value = value;
	}

	public String getyLabel() {
		return yLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
