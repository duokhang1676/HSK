package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import components.ColorConsts;
import dao.MaGiamGiaDao;
import dao.QuayDao;
import entity.Quay;

public class CreatePremisesFrm extends JFrame implements ActionListener{
	private QuayDao quay_dao;
	private JTextField txt_maQuay;
	private JTextField txt_tenQuay;
	private JTextField txt_diaChi;
	private JTextField txt_phuong;
	private JTextField txt_thanhPho;
	private JTextField txt_tinh;
	private JButton btn_them;
	private JButton btn_xoaTrang;

	public CreatePremisesFrm() {
		this.setTitle("Tạo mã giảm giá");
		this.setSize(800, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Font titleFont = new Font("Arial", Font.BOLD, 30);
		
		quay_dao = new QuayDao();
		
		JLabel title = new JLabel("Thêm quầy thuốc mới ");
		title.setFont(titleFont);
		
		/**
		 * Panel Information
		 */
		JPanel jp_info = new JPanel();
		jp_info.setLayout(new BorderLayout());
		
		JLabel jl_maQuay = new JLabel("Mã quầy: ");
		JLabel jl_tenQuay = new JLabel("Tên quầy: ");
		JLabel jl_diaChi = new JLabel("Địa chỉ: ");
		JLabel jl_phuong = new JLabel("Phường: ");
		JLabel jl_thanhPho = new JLabel("Thành phố: ");
		JLabel jl_tinh = new JLabel("Tỉnh: ");
		
		jl_maQuay.setPreferredSize(jl_thanhPho.getPreferredSize());
		jl_tenQuay.setPreferredSize(jl_thanhPho.getPreferredSize());
		jl_diaChi.setPreferredSize(jl_thanhPho.getPreferredSize());
		jl_phuong.setPreferredSize(jl_thanhPho.getPreferredSize());
		jl_tinh.setPreferredSize(jl_thanhPho.getPreferredSize());
		
		txt_maQuay = new JTextField();
		txt_maQuay.setEditable(false);
		
		txt_tenQuay = new JTextField();
		
		txt_diaChi = new JTextField();
		
		txt_phuong = new JTextField();
		
		txt_thanhPho = new JTextField();
		
		txt_tinh = new JTextField();
		
		Box b, b1, b2, b3, b4, b5;
		
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		
		b1.add(jl_maQuay);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txt_maQuay);
		
		b2.add(jl_tenQuay);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txt_tenQuay);

		b3.add(jl_diaChi);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txt_diaChi);
		
		b4.add(jl_phuong);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_phuong);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(jl_thanhPho);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_thanhPho);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(jl_tinh);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_tinh);
		
		b.add(b1);
		b.add(Box.createVerticalStrut(15));
		b.add(b2);
		b.add(Box.createVerticalStrut(15));
		b.add(b3);
		b.add(Box.createVerticalStrut(15));
		b.add(b4);
		b.add(Box.createVerticalStrut(15));
		b.add(b5);
		b.add(Box.createVerticalStrut(15));
		
		jp_info.add(b, BorderLayout.CENTER);
		
		int width = 130, height = 40;
		JPanel jp_button = new JPanel();
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		btn_them = new JButton("Thêm");
		btn_them.setIcon(new ImageIcon("icon\\ic_add.png"));
		btn_them.setPreferredSize(new Dimension(width, height));
		btn_them.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_xoaTrang = new JButton("Xóa thông tin");
		btn_xoaTrang.setIcon(new ImageIcon("icon\\ic_clearInfo.png"));
		btn_xoaTrang.setPreferredSize(new Dimension(width, height));
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		jp_button.add(btn_them);
		jp_button.add(btn_xoaTrang);
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		/**
		 * add event
		 */
		btn_them.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(jp_info, BorderLayout.CENTER);
		this.add(jp_button, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btn_them)) {
			addRow();
		}else if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private void addRow() {
		// TODO Auto-generated method stub
		String tenQuay = txt_tenQuay.getText();
		String diaChi = txt_diaChi.getText();
		String phuong = txt_phuong.getText();
		String thanhPho = txt_thanhPho.getText();
		String tinh = txt_tinh.getText();
		
		Quay q = new Quay(tenQuay, diaChi, phuong, thanhPho, tinh);
		
		if (quay_dao.themQuay(q)) {
			ShowMesage("Thêm thành công");
			
			dispose();
			return;
		}
		ShowMesage("Thêm không thành công");
	}

	private void ShowMesage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maQuay.setText("");
		txt_tenQuay.setText("");
		txt_diaChi.setText("");
		txt_phuong.setText("");
		txt_thanhPho.setText("");
		txt_tinh.setText("");
	}
}
