package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationBar implements IComponent {
	
	private final int MAX_ROW_COUNT = 25;
	private final int COLUMN_COUNT = 1;
	
	private JPanel navigationBarPanel;
	private List<JButton> buttons;
	
	private INavigateListener navigateListener;
	
	public NavigationBar() {
		buttons = new ArrayList<JButton>();
		
		navigationBarPanel = new JPanel();
		navigationBarPanel.setLayout(new GridLayout(MAX_ROW_COUNT, COLUMN_COUNT));
		navigationBarPanel.setBackground(Color.decode(ColorConsts.BackgroundColor));
	}
	
	public NavigationBar addNaviButton(String text, String urlImg) {
		JButton naviButton = new JButton(text);
		naviButton.setBackground(Color.decode(ColorConsts.ForegroundColor));
		naviButton.setPreferredSize(new Dimension(250, 120));
		
		if (!urlImg.isEmpty()) {
			naviButton.setIcon(new ImageIcon(urlImg));
		}
		naviButton.setPreferredSize(new Dimension(250, 120));
		
		if (!urlImg.isEmpty()) {
			
		}
		navigationBarPanel.add(naviButton);
		
		
		naviButton.addActionListener(e -> {
			JButton clickedButton = ((JButton) e.getSource());
			navigateListener.onNavigated(clickedButton.getText());
		});
		
		buttons.add(naviButton);
		
		try {
//		    Image img = ImageIO.read(getClass().getResource("resources/water.bmp"));
//		    button.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    ex.printStackTrace();
		}
		return this;
	}
	
	public JPanel createView() {
		return navigationBarPanel;
	}
	
	public NavigationBar addNavigateListener(INavigateListener navigateListener) {
		this.navigateListener = navigateListener;
		
		return this;
	}
}
