package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import dao.MaGiamGiaDao;
import entity.MaGiamGia;

public class CreateDiscountFrm extends JFrame implements ActionListener{
	private JTextField txt_maGiamGia;
	
	private JTextField txt_ngayKetThuc;
	private JTextField txt_phanTramGiamGia;
	private JTextArea txt_moTa;
	private JButton btn_them;
	private JButton btn_xoaTrang;

	private JTextField txt_ngayBatDau;

	private MaGiamGiaDao magiamgia_dao;

	public CreateDiscountFrm() {
		// TODO Auto-generated constructor stub
		this.setTitle("Tạo mã giảm giá");
		this.setSize(600, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		magiamgia_dao = new MaGiamGiaDao();
		
		/**
		 * Panel Information
		 */
		JPanel jp_info = new JPanel();
		jp_info.setLayout(new BorderLayout());
		
		JLabel jl_maGiamGia = new JLabel("Mã giảm giá: ");
		JLabel jl_ngayBatDau = new JLabel("Ngày bắt đầu: ");
		JLabel jl_ngayKetThuc = new JLabel("Ngày kết thúc: ");
		JLabel jl_moTa = new JLabel("Mô tả: ");
		JLabel jl_phanTramGiamGia = new JLabel("Phần trăm giảm giá: ");
		
		jl_maGiamGia.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayBatDau.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayKetThuc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_moTa.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		
		txt_maGiamGia = new JTextField();
		txt_maGiamGia.setEditable(false);
		
//		txt_ngayBatDau = new DatePicker();
		txt_ngayBatDau = new JTextField();
		
//		txt_ngayKetThuc = new DatePicker();
		txt_ngayKetThuc = new JTextField();
		
		txt_phanTramGiamGia = new JTextField();
		
		txt_moTa = new JTextArea(3,2);
		txt_moTa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		Box b, b1, b2, b3, b4, b5;
		
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		
		b1.add(jl_maGiamGia);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txt_maGiamGia);
		
		b2.add(jl_ngayBatDau);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txt_ngayBatDau);

		b3.add(jl_ngayKetThuc);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txt_ngayKetThuc);
		
		b4.add(jl_phanTramGiamGia);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_phanTramGiamGia);
		
		b5.add(jl_moTa);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txt_moTa);
		
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
			dispose();
		}else if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private void addRow() {
		// TODO Auto-generated method stub
		LocalDate ngayBD = LocalDate.parse(txt_ngayBatDau.getText());
		LocalDate ngayKT = LocalDate.parse(txt_ngayKetThuc.getText());
		double ptGiamGia = Double.parseDouble(txt_phanTramGiamGia.getText());
		String moTa = txt_moTa.getText();
		
		MaGiamGia mgg = new MaGiamGia( ngayBD, ngayKT, ptGiamGia, moTa);
		try {
			magiamgia_dao.themMaGiamGia(mgg);
			showMessage("Thêm thành công");
		} catch (Exception e) {
			// TODO: handle exception
			showMessage("Thêm không thành công");
		}
	}

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maGiamGia.setText("");
		txt_ngayBatDau.setText("");
		txt_ngayKetThuc.setText("");
		txt_phanTramGiamGia.setText("");
		txt_moTa.setText("");
	}
}
