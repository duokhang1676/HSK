package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Header {

	private JPanel headerPanel;

	public Header() {
		headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));
	}

	public Header addTitle(String title) {
		JLabel label = new JLabel(title);

		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		headerPanel.add(label, BorderLayout.NORTH);
		return this;
	}
	
	public Header addInsidePanel(JPanel panel) {
		headerPanel.add(panel, BorderLayout.CENTER);
		return this;
	}

	public JPanel createView() {
		return headerPanel;
	}
}
