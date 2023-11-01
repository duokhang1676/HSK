package ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.ColorConsts;
import components.Header;

public class DashboardPage extends BasePage {
	
	public DashboardPage() {
		super();
	}
	
	@Override
	public JPanel onCreateNestedContainerView() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.decode(ColorConsts.BackgroundColor));
		panel.add(new JLabel("Nested Container"));
		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header()
				.addTitle("Trang chủ")
				.createView();
	}

}
