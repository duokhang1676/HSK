package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

import org.jfree.ui.tabbedui.VerticalLayout;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import dao.KhachHangDao;
import dao.MaGiamGiaDao;
import entity.KhachHang;
import entity.MaGiamGia;

public class CreateCustomerFrm extends JFrame implements ActionListener{

	private JTextField tenKhachHangTxt;
	private JTextField sdtTxt;

	private JButton themBtn;
	private JButton xoaTrangBtn;

	private KhachHangDao khachHangDao;

	public CreateCustomerFrm() {
		setTitle("Tạo tài khoản khách hàng");
		setSize(600, 200);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		khachHangDao = new KhachHangDao();
		
		
		JLabel tenKhachHangLb = new JLabel("Tên khách hàng: ");
		tenKhachHangTxt = new JTextField();
		Box tenKhachHangBox = Box.createHorizontalBox();
		tenKhachHangBox.add(tenKhachHangLb);
		tenKhachHangBox.add(tenKhachHangTxt);
		
		JLabel soDienThoaiLb = new JLabel("Số điện thoại : ");
		soDienThoaiLb.setPreferredSize(tenKhachHangLb.getPreferredSize());
		sdtTxt = new JTextField();
		Box sdtBox = Box.createHorizontalBox();
		sdtBox.add(soDienThoaiLb);
		sdtBox.add(sdtTxt);
		
		JPanel verticalBox = new JPanel(new VerticalLayout());
		verticalBox.add(tenKhachHangBox);
		verticalBox.add(Box.createVerticalStrut(20));
		verticalBox.add(sdtBox);
		
		themBtn = new JButton("Thêm");
		themBtn.setIcon(new ImageIcon("icon\\ic_add.png"));
		themBtn.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		xoaTrangBtn = new JButton("Xóa thông tin");
		xoaTrangBtn.setIcon(new ImageIcon("icon\\ic_clearInfo.png"));
		xoaTrangBtn.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		JPanel groupButton = new JPanel();
		groupButton.setBackground(Color.decode(ColorConsts.PrimaryColor));
		groupButton.add(themBtn);
		groupButton.add(xoaTrangBtn);
		groupButton.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		
		themBtn.addActionListener(this);
		xoaTrangBtn.addActionListener(this);
		
		add(verticalBox, BorderLayout.CENTER);
		add(groupButton, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eSrc = e.getSource();
		
		if (eSrc.equals(themBtn)) {
			
			String tenKhachHang = tenKhachHangTxt.getText().trim();
			String soDienThoai = sdtTxt.getText().trim();
			
			KhachHang kh = new KhachHang(tenKhachHang, soDienThoai, LocalDate.now());
			
			if (khachHangDao.themKhachHang(kh)) {
				showMessage("Thêm thành công");
				dispose();
			}
		}
		
		if (eSrc.equals(xoaTrangBtn)) {
			clearData();
		}
	}

	private void showMessage(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

	private void clearData() {
		sdtTxt.setText("");
		tenKhachHangTxt.setText("");
	}
}
