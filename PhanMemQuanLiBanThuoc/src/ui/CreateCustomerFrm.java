package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.ColorConsts;
import dao.KhachHangDao;
import entity.KhachHang;

public class CreateCustomerFrm extends JFrame implements ActionListener{
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtHo;
	private JTextField txtSdt;
	
	
	
	private JButton btn_them;
	private JButton btn_xoaTrang;
	private KhachHangDao khachhang_dao;
	
	private List<KhachHang> dsKhachHang;
	
	public CreateCustomerFrm() {
		this.setTitle("Tạo sản phẩm");
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		/**
		 * connect database
		 */
		try {
			db.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		khachhang_dao = new KhachHangDao();
		dsKhachHang = khachhang_dao.getAllKhachHang();
		Font commonFont = new Font("Arial", Font.PLAIN, 14);
		Font titleFont = new Font("Arial", Font.BOLD, 30);
		JPanel jp_txtCus = new JPanel();
		jp_txtCus.setLayout(new BorderLayout());
		JPanel jp_title = new JPanel();
		jp_title.setLayout(new BorderLayout());
		JLabel title = new JLabel("Thêm");
		title.setFont(titleFont);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("img\\img_logo.png"));
		jp_title.add(title, BorderLayout.WEST);
		
		
		JLabel jl_maKH = new JLabel("Mã Khách Hàng: ");
		JLabel jl_tenKH = new JLabel("Tên: ");
		JLabel jl_hoKH = new JLabel("Họ:" );
		JLabel jl_sdtKH = new JLabel("Số Điện Thoại:");
		
		txtMa = new JTextField();
		txtMa.setEditable(false);
		txtMa = new JTextField();

		txtHo = new JTextField();
		txtTen = new JTextField();
		txtSdt = new JTextField();
		
		jl_maKH.setPreferredSize(jl_hoKH.getPreferredSize());
		jl_tenKH.setPreferredSize(jl_hoKH.getPreferredSize());
		jl_sdtKH.setPreferredSize(jl_hoKH.getPreferredSize());
		
		Box b, b1, b2, b3, b4;
		int heightStrut = 10, widthStrut = 10;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		
		
		b1.add(jl_maKH);
		b1.add(txtMa);
		b1.add(Box.createHorizontalStrut(widthStrut));
		b1.add(jl_maKH);
		b1.add(txtMa);
		
		b2.add(jl_hoKH);
		b2.add(txtHo);
		b2.add(Box.createHorizontalStrut(widthStrut));
		b2.add(jl_hoKH);
		b2.add(txtHo);
		
		b3.add(jl_tenKH);
		b3.add(txtHo);
		b3.add(Box.createHorizontalStrut(widthStrut));
		b3.add(jl_tenKH);
		b3.add(txtTen);
		
		
		b4.add(jl_sdtKH);
		b4.add(txtSdt);
		b4.add(Box.createHorizontalStrut(widthStrut));
		b4.add(jl_sdtKH);
		b4.add(txtSdt);
		
		b.add(b1);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b2);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b3);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b4);
		b.add(Box.createVerticalStrut(heightStrut));
		
		jp_txtCus.add(jp_title, BorderLayout.NORTH);
		jp_txtCus.add(b, BorderLayout.CENTER);
		jp_txtCus.setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		
		int width = 250, height = 40;
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
		
		btn_them.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(jp_txtCus, BorderLayout.CENTER);
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
		} 
		
		if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}
	private void clearData() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtSdt.setText("");
	}

	private void addRow() {
		String ho = txtHo.getText();
		String ten = txtTen.getText();
		String sdt = txtSdt.getText();
		
//		KhachHang khachhang = dsKhachHang.get(maKhachHang.selected);
		
		
	}

}
