package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class TopSaleProductView extends JPanel implements ListCellRenderer<Object>{

	private JLabel productNameLabel;
	private JLabel idxLabel;
	private JLabel priceLabel;
	
	public TopSaleProductView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		idxLabel = new JLabel();
		idxLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
		idxLabel.setFont(new Font("Arials", Font.BOLD, 15));
		
		productNameLabel = new JLabel();
		productNameLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
		productNameLabel.setFont(new Font("Arials", Font.BOLD, 15));
		
		priceLabel = new JLabel("450,000đ");
		priceLabel.setFont(new Font("Arials", Font.BOLD, 15));
        add(idxLabel, BorderLayout.WEST);
        add(productNameLabel, BorderLayout.CENTER);
        add(priceLabel, BorderLayout.EAST);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		idxLabel.setText(String.valueOf(index + 1));
		productNameLabel.setText(value.toString());
		
		return this;
	}

}
