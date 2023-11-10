package ui;

import javax.swing.JPanel;

import components.Header;

public class WarehousePage extends BasePage {

	@Override
	protected JPanel onCreateNestedContainerView() {
		JPanel panel = new JPanel();
		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header().addTitle("Quầy").createView();
	}

}
