package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.tabbedui.VerticalLayout;

import components.ColorConsts;
import components.TaiKhoanDangNhap;
import dao.NhanVienDao;

public class ChangePasswordFrm extends JFrame{
	private JTextField txtMatKhauCu;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtMatKhauMoi2;
	private JButton btnCapNhat;
	
	private NhanVienDao nhanVienDao;
	
	private boolean flag = true;
	private boolean flag2 = true;
	
	public ChangePasswordFrm() {
		nhanVienDao = new NhanVienDao();
		setTitle("Đổi mật khẩu");
		setSize(600,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Font font1 = new Font("Arial", Font.BOLD, 30);
		Font font2 = new Font("Arial", Font.BOLD, 17);
		Font font3 = new Font("Arial", Font.PLAIN, 15);
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(Color.decode(ColorConsts.ForegroundColor));
		JLabel lblTitle;
		pnlTitle.add(lblTitle =  new JLabel("Đổi mật khẩu"));
		lblTitle.setFont(font1);
		lblTitle.setForeground(Color.decode(ColorConsts.PrimaryColor));
		JLabel lblMatKhauCu = new JLabel("Mật khẩu cũ");
		lblMatKhauCu.setFont(font2);
		lblMatKhauCu.setPreferredSize(new Dimension(190,30));
		txtMatKhauCu = new JTextField("");
		txtMatKhauCu.setFont(font3);
		Box matKhauCuBox = Box.createHorizontalBox();
		matKhauCuBox.add(lblMatKhauCu);
		matKhauCuBox.add(txtMatKhauCu);
		
		
		JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới");
		lblMatKhauMoi.setFont(font2);
		lblMatKhauMoi.setPreferredSize(new Dimension(190,30));
		txtMatKhauMoi = new JPasswordField("");
		txtMatKhauMoi.setFont(font3);
		Box matKhauMoiBox = Box.createHorizontalBox();
		matKhauMoiBox.add(lblMatKhauMoi);
		matKhauMoiBox.add(txtMatKhauMoi);
		txtMatKhauMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)) {
					if(flag)
						txtMatKhauMoi.setEchoChar((char)0);
					else
						txtMatKhauMoi.setEchoChar('\u2022');
					flag = !flag;
				}
			}
		});
		
		JLabel lblMatKhauMoi2 = new JLabel("Nhập lại mật khẩu mới");
		lblMatKhauMoi2.setFont(font2);
		lblMatKhauMoi2.setPreferredSize(new Dimension(190,30));
		txtMatKhauMoi2 = new JPasswordField("");
		txtMatKhauMoi2.setFont(font3);
		Box matKhauMoiBox2 = Box.createHorizontalBox();
		matKhauMoiBox2.add(lblMatKhauMoi2);
		matKhauMoiBox2.add(txtMatKhauMoi2);
		txtMatKhauMoi2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)) {
					if(flag2)
						txtMatKhauMoi2.setEchoChar((char)0);
					else
						txtMatKhauMoi2.setEchoChar('\u2022');
					flag2 = !flag2;
				}
			}
		});
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnCapNhat.setFont(new Font("Arial", font1.BOLD, 20));
		btnCapNhat.setPreferredSize(new Dimension(0,50));
		btnCapNhat.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnCapNhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtMatKhauCu.getText().isEmpty()) {
					txtMatKhauCu.requestFocus();
					return;
				}
				if(!TaiKhoanDangNhap.getNV().getMatKhau().equals(txtMatKhauCu.getText())) {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(ChangePasswordFrm.this),
							"Mật khẩu cũ không đúng!");
					txtMatKhauCu.requestFocus();
					return;
				}
				if(txtMatKhauMoi.getText().isEmpty()) {
					txtMatKhauMoi.requestFocus();
					return;
				} 
				if(!txtMatKhauMoi2.getText().equals(txtMatKhauMoi.getText())) {	
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(ChangePasswordFrm.this),
							"Nhập lại mật khẩu mới không đúng!");
					txtMatKhauMoi2.requestFocus();
					return;
				}
				if(nhanVienDao.doiMatKhau(TaiKhoanDangNhap.getNV().getMaNhanVien(), txtMatKhauMoi.getText()))
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(ChangePasswordFrm.this),
							"Đổi mật khẩu thành công");
				setVisible(false);
			}
			
		});
		
		JPanel pnl = new JPanel(new VerticalLayout());
		pnl.setBorder(new EmptyBorder(30, 30, 30, 30));
		pnl.setBackground(Color.decode(ColorConsts.ForegroundColor));
		pnl.add(pnlTitle);
		pnl.add(Box.createVerticalStrut(30));
		pnl.add(matKhauCuBox);
		pnl.add(Box.createVerticalStrut(20));
		pnl.add(matKhauMoiBox);
		pnl.add(Box.createVerticalStrut(20));
		pnl.add(matKhauMoiBox2);
		pnl.add(Box.createVerticalStrut(30));
		pnl.add(btnCapNhat);
		add(pnl);
	}
}
