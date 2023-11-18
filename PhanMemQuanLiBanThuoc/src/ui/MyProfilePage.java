package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.jfree.ui.tabbedui.VerticalLayout;

import components.ColorConsts;
import components.TaiKhoanDangNhap;
import entity.NhanVien;

public class MyProfilePage extends BasePage {

	private JTextField hoTenTxt;
	private JTextField sdtTxt;
	private JTextField caLamViecTxt;
	private JTextField chucVuTxt;
	private JTextField ngayVaoLamTxt;
	
	private JButton dangXuatBtn;
	private JButton btnDoiMatKhau;
	
	@Override
	protected JPanel onCreateNestedContainerView() {
		
		NhanVien nv = TaiKhoanDangNhap.getNV();

		JLabel ngayVaoLamBoxLb = new JLabel("Ngày vào làm");
		ngayVaoLamBoxLb.setFont(new Font("Arials", Font.PLAIN, 14));

		ngayVaoLamTxt = new JTextField("");
		ngayVaoLamTxt.setText(nv.getNgayVaoLam()+"");
		ngayVaoLamTxt.setEditable(false);
		Box ngayVaoLamBox = Box.createHorizontalBox();
		ngayVaoLamBox.add(ngayVaoLamBoxLb);
		ngayVaoLamBox.add(ngayVaoLamTxt);
		
		JLabel soDienThoaiLb = new JLabel("Số điện thoại");
		soDienThoaiLb.setFont(new Font("Arials", Font.PLAIN, 14));
		soDienThoaiLb.setPreferredSize(ngayVaoLamBoxLb.getPreferredSize());
		sdtTxt = new JTextField("");
		sdtTxt.setText(nv.getSoDienThoai()+"");
		sdtTxt.setEditable(false);
		Box sdtBox = Box.createHorizontalBox();
		sdtBox.add(soDienThoaiLb);
		sdtBox.add(sdtTxt);
		
		JLabel hoTenLb = new JLabel("Họ tên");
		hoTenLb.setFont(new Font("Arials", Font.PLAIN, 14));
		hoTenLb.setPreferredSize(ngayVaoLamBoxLb.getPreferredSize());
		hoTenTxt = new JTextField("");
		hoTenTxt.setText(nv.getTenNhanVien());
		hoTenTxt.setEditable(false);
		Box hoTenBox = Box.createHorizontalBox();
		hoTenBox.add(hoTenLb);
		hoTenBox.add(hoTenTxt);

		JLabel caLamViecLb = new JLabel("Ca làm việc");
		caLamViecLb.setFont(new Font("Arials", Font.PLAIN, 14));
		caLamViecLb.setPreferredSize(ngayVaoLamBoxLb.getPreferredSize());
		caLamViecTxt = new JTextField("");
		caLamViecTxt.setText(nv.getCaLamViec());
		caLamViecTxt.setEditable(false);
		Box caLmViecBox = Box.createHorizontalBox();
		caLmViecBox.add(caLamViecLb);
		caLmViecBox.add(caLamViecTxt);
		
		JLabel chucVuLb = new JLabel("Chức vụ");
		chucVuLb.setFont(new Font("Arials", Font.PLAIN, 14));
		chucVuLb.setPreferredSize(ngayVaoLamBoxLb.getPreferredSize());
		chucVuTxt = new JTextField("");
		chucVuTxt.setText(nv.getChucVu());
		chucVuTxt.setEditable(false);
		Box chucVuBox = Box.createHorizontalBox();
		chucVuBox.add(chucVuLb);
		chucVuBox.add(chucVuTxt);

		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageIcon("img\\img_avatar.jpg"));
		lblAvatar.setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel thongTinLb = new JLabel("Thông tin cá nhân");
		thongTinLb.setFont(new Font("Arials", Font.BOLD, 25));
		thongTinLb.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setBackground(Color.decode(ColorConsts.BackgroundColor));
		btnDoiMatKhau.setFont(new Font("Arials", Font.PLAIN, 16));
		btnDoiMatKhau.setForeground(Color.decode(ColorConsts.PrimaryColor));
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordFrm().setVisible(true);
			}
		});
		
		
		dangXuatBtn = new JButton("Đăng xuất");
		dangXuatBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		dangXuatBtn.setFont(new Font("Arials", Font.PLAIN, 16));
		dangXuatBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		dangXuatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootFrame rootFrame = (RootFrame) SwingUtilities.getWindowAncestor(MyProfilePage.this);
				rootFrame.setVisible(false);
				TaiKhoanDangNhap.setNV(null);
				LoginPage login = new LoginPage();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
			}
		});
		
		
		JPanel pLeft = new JPanel(new VerticalLayout());
		pLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		pLeft.setBackground(Color.decode(ColorConsts.ForegroundColor));
		pLeft.add(thongTinLb);
		pLeft.add(lblAvatar);
		pLeft.add(hoTenBox);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(sdtBox);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(caLmViecBox);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(chucVuBox);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(ngayVaoLamBox);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(btnDoiMatKhau);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(dangXuatBtn);

		JPanel pRight = new JPanel(new VerticalLayout());
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(pLeft, BorderLayout.WEST);
		panel.add(pRight, BorderLayout.CENTER);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		return null;
	}

}
