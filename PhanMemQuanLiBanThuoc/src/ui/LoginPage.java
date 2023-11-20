package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.ColorConsts;
import components.TaiKhoanDangNhap;
import dao.NhanVienDao;
import entity.NhanVien;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

public class LoginPage extends JFrame implements ActionListener {
	private JTextField txtSdt;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnSignUp;
	private JButton btnExit;
	
	
	
	private NhanVienDao nhanVienDao;
	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		
		nhanVienDao = new NhanVienDao();
		
		Image img = Toolkit.getDefaultToolkit().createImage("img\\img_logoSmall.png");
		setIconImage(img);
		getContentPane().setLayout(null);
		
		JPanel jp_bg1 = new JPanel();
		jp_bg1.setBounds(0, 0, 686, 663);
		getContentPane().add(jp_bg1);
		jp_bg1.setLayout(null);
		
		JPanel jp_bg2 = new JPanel();
		jp_bg2.setBounds(10, 10, 666, 643);
		jp_bg2.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_bg1.add(jp_bg2);
		jp_bg2.setLayout(null);
		
		JPanel jp_login = new JPanel();
		jp_login.setBounds(25, 25, 615, 591);
		jp_login.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_bg2.add(jp_login);
		jp_login.setLayout(null);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setBounds(129, 10, 395, 180);
		lbllogo.setIcon(new ImageIcon("img\\img_logo.png"));
		jp_login.add(lbllogo);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setForeground(new Color(22, 65, 135));
		lblNewLabel.setFont(new Font("Letterpress Script", Font.BOLD, 40));
		lblNewLabel.setBounds(262, 200, 151, 72);
		jp_login.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("Số điện thoại");
		lblUserName.setFont(new Font("Arials", Font.BOLD, 15));
		lblUserName.setBounds(90, 280, 99, 26);
		jp_login.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setFont(new Font("Arials", Font.BOLD, 15));
		lblPassword.setBounds(90, 354, 99, 26);
		jp_login.add(lblPassword);
		
		txtSdt = new JTextField();
		txtSdt.setBounds(90, 303, 434, 41);
		jp_login.add(txtSdt);
		txtSdt.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(90, 378, 434, 41);
		jp_login.add(txtPassword);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Arials", Font.PLAIN, 14));
		btnLogin.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnLogin.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnLogin.setBounds(250, 442, 150, 41);
		jp_login.add(btnLogin);
		
//		btnSignUp = new JButton("Sign Up");
//		btnSignUp.setFont(new Font("Arials", Font.PLAIN, 14));
//		btnSignUp.setBackground(Color.decode(ColorConsts.PrimaryColor));
//		btnSignUp.setForeground(Color.decode(ColorConsts.ForegroundColor));
//		btnSignUp.setBounds(253, 442, 99, 41);
//		jp_login.add(btnSignUp);
		
		btnExit = new JButton("Thoát");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnExit.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnExit.setBounds(250, 490, 150, 41);
		jp_login.add(btnExit);
		
		//add event
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnLogin)) {
			String sdt = txtSdt.getText().trim();
			String pwd = txtPassword.getText().trim();
			
			if (sdt.isEmpty() || pwd.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
				return;
			}
			
			if (dangNhap(sdt, pwd)) {
				new RootFrame().setVisible(true);
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Đăng nhập không thành công.");
				return;
			}
		}
		if (src.equals(btnExit)) {
			System.exit(0);
		}
	}
	
	private boolean dangNhap(String sdt, String pwd) {
		NhanVien nv = nhanVienDao.getNhanVienBySdtPwd(sdt, pwd);
		TaiKhoanDangNhap.setNV(nv);
		if (nv == null) {
			return false;
		}
		
		System.out.println(nv.getTenNhanVien());
		return true;
	}
	
	
}