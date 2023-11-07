package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import entity.ChiTietHoaDon;

public class OrderDetailView extends JPanel implements ListCellRenderer<ChiTietHoaDon>{

	private JLabel productNameLabel;
	private JLabel quantity;
	private JLabel priceLabel;
	
	public OrderDetailView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		quantity = new JLabel();
		quantity.setBorder(new EmptyBorder(0, 0, 0, 20));
		quantity.setFont(new Font("Arials", Font.BOLD, 15));
		
		productNameLabel = new JLabel();
		productNameLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
		productNameLabel.setFont(new Font("Arials", Font.BOLD, 15));
		
		priceLabel = new JLabel("450,000đ");
		priceLabel.setFont(new Font("Arials", Font.BOLD, 15));
        add(quantity, BorderLayout.WEST);
        add(productNameLabel, BorderLayout.CENTER);
        add(priceLabel, BorderLayout.EAST);
	}

	
	@Override
	public Component getListCellRendererComponent(JList<? extends ChiTietHoaDon> list, ChiTietHoaDon value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		
		
		
		quantity.setText(String.valueOf(value.getSoLuong()));
		productNameLabel.setText(value.getSanPham().getTenThuoc());
		priceLabel.setText(value.getDonViTinh());
		return this;
	}
	
	

}
