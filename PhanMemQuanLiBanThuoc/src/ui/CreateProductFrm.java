package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import components.ColorConsts;
import dao.MaGiamGiaDao;
import dao.NhaCungCapDao;
import dao.NhomThuocDao;
import dao.ThuocDao;
import entity.MaGiamGia;
import entity.NhaCungCap;
import entity.NhomThuoc;
import entity.Thuoc;

public class CreateProductFrm extends JFrame implements ActionListener {
	private JTextField txt_maThuoc;
	private JTextField txt_tenThuoc;
	private JComboBox<String> txt_maNCC;
	private JTextField txt_donViTinh;
	private JTextField txt_thanhPhan;
	private JTextField txt_donViTinhLe;
	private JTextField txt_donViTinhChan;
	private JTextField txt_hanSuDung;
	private JTextField txt_dieuKienBQ;
	private JTextArea txt_ghiChu;
	private JTextField txt_giaNhapLe;
	private JTextField txt_giaNhapChan;
	private JTextField txt_giaBanLe;
	private JTextField txt_giaBanChan;
	private JComboBox<String> txt_maNhom;
	private NhomThuocDao nhomThuoc_dao;
	private ThuocDao thuoc_dao;
	private NhaCungCapDao nhaCungCap_dao;
	private JButton btn_them;
	private JButton btn_xoaTrang;



	public CreateProductFrm() {
		// TODO Auto-generated constructor stub
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
		nhomThuoc_dao = new NhomThuocDao();
		thuoc_dao = new ThuocDao();
		nhaCungCap_dao = new NhaCungCapDao();
		

		/**
		 * infomation panel
		 * 
		 */
		Font commonFont = new Font("Arial", Font.PLAIN, 14);
		Font titleFont = new Font("Arial", Font.BOLD, 30);
		JPanel jp_txtProd = new JPanel();
		jp_txtProd.setLayout(new BorderLayout());
//		JPanel jp_hinhAnh = new JPanel();
//		jp_hinhAnh.setBackground(Color.decode(ColorConsts.ForegroundColor));
//		jp_hinhAnh.setBorder(BorderFactory.createTitledBorder("Hình ảnh"));
//		JLabel jl_hinhAnh = new JLabel();
//		jl_hinhAnh.setIcon(new ImageIcon("img\\img_logo.png"));
//		
//		jp_hinhAnh.add(jl_hinhAnh);

		JPanel jp_title = new JPanel();
		jp_title.setLayout(new BorderLayout());
		JLabel title = new JLabel("Thêm Thuốc");
		title.setFont(titleFont);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("img\\img_logo.png"));

		jp_title.add(title, BorderLayout.WEST);
//		jp_title.add(logo, BorderLayout.EAST);

		JLabel jl_maThuoc = new JLabel("Mã thuốc: ");
		JLabel jl_tenThuoc = new JLabel("Tên thuốc: ");
		JLabel jl_maNCC = new JLabel("Mã nhà cung cấp: ");
		JLabel jl_donViTinh = new JLabel("Đơn vị tính: ");
		JLabel jl_thanhPhan = new JLabel("Thành phần chính: ");
		JLabel jl_donViTinhLe = new JLabel("Đơn vị tính lẻ: ");
		JLabel jl_donViTinhChan = new JLabel("Đơn vị tính chẵn: ");
		JLabel jl_hanSuDung = new JLabel("Hạn sử dụng: ");
		JLabel jl_dieuKienBQ = new JLabel("Điều kiện bảo quản: ");
		JLabel jl_ghiChu = new JLabel("Ghi chú: ");
		JLabel jl_giaNhapLe = new JLabel("Giá nhập lẻ: ");
		JLabel jl_giaNhapChan = new JLabel("Giá nhập chẵn: ");
		JLabel jl_giaBanLe = new JLabel("Giá bán lẻ: ");
		JLabel jl_giaBanChan = new JLabel("Giá bán chẵn: ");
		JLabel jl_maNhom = new JLabel("Mã nhóm thuốc: ");
		JLabel jl_maGiamGia = new JLabel("Giảm giá: ");

		txt_maThuoc = new JTextField();
		txt_maThuoc.setEditable(false);
		txt_tenThuoc = new JTextField();
//		txt_maNCC = new JTextField();

		txt_maNCC = new JComboBox<String>();
		txt_maNCC.setEditable(true);

		txt_donViTinh = new JTextField();
		txt_thanhPhan = new JTextField();
		txt_donViTinhLe = new JTextField();
		txt_donViTinhChan = new JTextField();
		txt_hanSuDung = new JTextField();
//		txt_hanSuDung = new JDateChooser();
		txt_dieuKienBQ = new JTextField();
		txt_ghiChu = new JTextArea(3, 2);
		txt_giaNhapLe = new JTextField();
		txt_giaNhapChan = new JTextField();
		txt_giaBanLe = new JTextField();
		txt_giaBanChan = new JTextField();

//		txt_maNhom = new JTextField();
		txt_maNhom = new JComboBox<String>();
		txt_maNhom.setEditable(true);

		txt_ghiChu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		

		jl_maThuoc.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_maNhom.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_maNCC.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_tenThuoc.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_donViTinh.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_donViTinhChan.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_donViTinhLe.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_thanhPhan.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_hanSuDung.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_ghiChu.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_giaNhapLe.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_giaNhapChan.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_giaBanLe.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_giaBanChan.setPreferredSize(jl_dieuKienBQ.getPreferredSize());
		jl_maGiamGia.setPreferredSize(jl_dieuKienBQ.getPreferredSize());

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		int heightStrut = 10, widthStrut = 10;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		b7 = Box.createHorizontalBox();
		b8 = Box.createHorizontalBox();

		b1.add(jl_maNhom);
		b1.add(txt_maNhom);
		b1.add(Box.createHorizontalStrut(widthStrut));
		b1.add(jl_maNCC);
		b1.add(txt_maNCC);

		b2.add(jl_tenThuoc);
		b2.add(txt_tenThuoc);
		b2.add(Box.createHorizontalStrut(widthStrut));
		b2.add(jl_thanhPhan);
		b2.add(txt_thanhPhan);

		b3.add(jl_hanSuDung);
		b3.add(txt_hanSuDung);
//		b3.add(Box.createHorizontalStrut(50));
		b3.add(Box.createHorizontalStrut(widthStrut));
		b3.add(jl_dieuKienBQ);
		b3.add(txt_dieuKienBQ);

		b4.add(jl_donViTinh);
		b4.add(txt_donViTinh);
		b4.add(Box.createHorizontalStrut(widthStrut));
		b4.add(jl_donViTinhLe);
		b4.add(txt_donViTinhLe);
		b4.add(Box.createHorizontalStrut(widthStrut));
		b4.add(jl_donViTinhChan);
		b4.add(txt_donViTinhChan);

		b5.add(jl_giaNhapLe);
		b5.add(txt_giaNhapLe);
		b5.add(Box.createHorizontalStrut(widthStrut));
		b5.add(jl_giaNhapChan);
		b5.add(txt_giaNhapChan);

		b6.add(jl_giaBanLe);
		b6.add(txt_giaBanLe);
		b6.add(Box.createHorizontalStrut(widthStrut));
		b6.add(jl_giaBanChan);
		b6.add(txt_giaBanChan);

		
		b8.add(jl_ghiChu);
		b8.add(txt_ghiChu);

		b.add(b1);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b2);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b3);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b4);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b5);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b6);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b7);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b8);
		b.add(Box.createVerticalStrut(heightStrut));

//		jp_txtProd.add(jp_hinhAnh, BorderLayout.WEST);
		jp_txtProd.add(jp_title, BorderLayout.NORTH);
		jp_txtProd.add(b, BorderLayout.CENTER);
		jp_txtProd.setBackground(Color.decode(ColorConsts.ForegroundColor));

		/**
		 * doc du lieu nhom thuoc
		 */
		ArrayList<NhomThuoc> listNhomThuoc = nhomThuoc_dao.getAllData();
		for (NhomThuoc nhomThuoc : listNhomThuoc) {
			txt_maNhom.addItem(nhomThuoc.getTenNhomThuoc());
		}
		/**
		 * doc du lieu nhom nha cung cap
		 */
		ArrayList<NhaCungCap> listNhaCungCap = nhaCungCap_dao.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : listNhaCungCap) {
			txt_maNCC.addItem(nhaCungCap.getTenNhaCungCap());
		}
		/**
		 * button panel
		 */
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

		/**
		 * add event
		 */
		btn_them.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		/*
		 * add to screen
		 */
		this.setLayout(new BorderLayout());
		this.add(jp_txtProd, BorderLayout.CENTER);
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
		} else if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maThuoc.setText("");
		txt_tenThuoc.setText("");
//		txt_maNCC.setSelectedIndex(0);
		txt_donViTinh.setText("");
		txt_thanhPhan.setText("");
		txt_donViTinhLe.setText("");
		txt_donViTinhChan.setText("");
		txt_hanSuDung.setText("");
		txt_dieuKienBQ.setText("");
		txt_ghiChu.setText("");
		txt_giaNhapLe.setText("");
		txt_giaNhapChan.setText("");
		txt_giaBanLe.setText("");
		txt_giaBanChan.setText("");
//		txt_maNhom.setSelectedIndex(0);
	}

	private void addRow() {
		// TODO Auto-generated method stub

		String tenThuoc = txt_tenThuoc.getText();

		int maNCC = txt_maNCC.getSelectedIndex();
		NhaCungCap nhaCC = new NhaCungCap(maNCC+1);

		String donViTinh = txt_donViTinh.getText();
		String thanhPhanChinh = txt_thanhPhan.getText();
		String donViTinhLe = txt_donViTinhLe.getText();
		LocalDate hanSuDung = LocalDate.parse(txt_hanSuDung.getText());
		String dkBaoQuan = txt_dieuKienBQ.getText();
		String donViTinhChan = txt_donViTinhChan.getText();
		String ghiChu = txt_ghiChu.getText();
		double giaNhapLe = Double.parseDouble(txt_giaNhapLe.getText());
		double giaNhapChan = Double.parseDouble(txt_giaNhapChan.getText());
		double giaBanLe = Double.parseDouble(txt_giaBanLe.getText());
		double giaBanChan = Double.parseDouble(txt_giaBanChan.getText());

		int maNhomThuoc = txt_maNhom.getSelectedIndex();
		NhomThuoc nhomThuoc = new NhomThuoc(maNhomThuoc + 1);
		Thuoc t = new Thuoc(tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan,
				donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
		try {
			thuoc_dao.themThuoc(t);
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
}