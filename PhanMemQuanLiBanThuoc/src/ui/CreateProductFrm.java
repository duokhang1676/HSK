package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import com.github.lgooddatepicker.components.DatePicker;
import com.toedter.calendar.JDateChooser;

import components.ColorConsts;
import dao.KhoDao;
import dao.MaGiamGiaDao;
import dao.NhaCungCapDao;
import dao.NhomThuocDao;
import dao.ThuocDao;
import entity.Kho;
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
	private DatePicker txt_hanSuDung;
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

	private List<NhomThuoc> dsNhomThuoc;
	private List<NhaCungCap> dsNhaCungCap;
	private KhoDao kho_dao;

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
		kho_dao = new KhoDao();
		
		dsNhomThuoc = nhomThuoc_dao.getAllData();
		dsNhaCungCap = nhaCungCap_dao.getAllNhaCungCap();
		/**
		 * infomation panel
		 * 
		 */
		Font commonFont = new Font("Arial", Font.PLAIN, 14);
		Font titleFont = new Font("Arial", Font.BOLD, 30);
		JPanel jp_txtProd = new JPanel();
		jp_txtProd.setLayout(new BorderLayout());

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
		txt_maNCC.setEditable(false);

		txt_donViTinh = new JTextField();
		txt_thanhPhan = new JTextField();
		txt_donViTinhLe = new JTextField();
		txt_donViTinhChan = new JTextField();
//		txt_hanSuDung = new JTextField();
		txt_hanSuDung = new DatePicker();
		txt_dieuKienBQ = new JTextField();
		txt_ghiChu = new JTextArea(3, 2);
		txt_giaNhapLe = new JTextField();
		txt_giaNhapChan = new JTextField();
		txt_giaBanLe = new JTextField();
		txt_giaBanChan = new JTextField();

//		txt_maNhom = new JTextField();
		txt_maNhom = new JComboBox<String>();
		txt_maNhom.setEditable(false);

		txt_ghiChu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		txt_hanSuDung.setPreferredSize(txt_tenThuoc.getPreferredSize());
		
		

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
		for (NhomThuoc nhomThuoc : dsNhomThuoc) {
			txt_maNhom.addItem(nhomThuoc.getTenNhomThuoc());
		}

		for (NhaCungCap nhaCungCap : dsNhaCungCap) {
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
			if (validData()) {
				addRow();
				dispose();
			}
		} 
		
		if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private boolean validData() {
		// TODO Auto-generated method stub
		String tenThuoc = txt_tenThuoc.getText().trim();
		String thanhPhanChinh = txt_thanhPhan.getText().trim();
		String donViTinh = txt_donViTinh.getText().trim();
		String donViTinhLe = txt_donViTinhLe.getText().trim();
		String donViTinhChan = txt_donViTinhChan.getText().trim();
		String dieuKienBQ = txt_dieuKienBQ.getText().trim();
		String giaNhapLe = txt_giaNhapLe.getText();
		String giaNhapChan = txt_giaNhapChan.getText();
		String giaBanLe = txt_giaBanLe.getText();
		String giaBanChan = txt_giaBanChan.getText();
		
//		Pattern p = Pattern.compile("[a-zA-Z0-9. ]+", Pattern.UNICODE_CHARACTER_CLASS);
		
		//check ten thuoc
		if (tenThuoc.length() <= 0) {
//			if (!(tenThuoc.matches("[a-zA-Z0-9. ]+"))) {
//				showMessage("Tên thuốc không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
//				txt_tenThuoc.requestFocus();
//				txt_tenThuoc.selectAll();
//				return false;
//			}
			showMessage("Tên thuốc không được để trống!!!");
			return false;
		}
		//check thanh phan chinh
		if (thanhPhanChinh.length() > 0) {
			if (!(thanhPhanChinh.matches("[^!@#$%^&*()_+]+"))) {
				showMessage("Thành phần chính không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
				txt_thanhPhan.requestFocus();
				txt_thanhPhan.selectAll();
				return false;
			}
		}else {
			showMessage("Thành phần chính không được để trống!!!");
			return false;
		}
		// check dk bao quan
		if (dieuKienBQ.length() > 0) {
			if (!(dieuKienBQ.matches("[^!@#$%^&*()_+]+"))) {
				showMessage("Điều kiện bảo quản không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
				txt_dieuKienBQ.requestFocus();
				txt_dieuKienBQ.selectAll();
				return false;
			}
		} else {
			showMessage("Điều kiện bảo quản không được để trống!!!");
			return false;
		}
		//check don vi tinh
		if (donViTinh.length() > 0) {
			if (!(donViTinh.matches("[^!@#$%^&*()_+]+"))) {
				showMessage("Đơn vị tính không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
				txt_donViTinh.requestFocus();
				txt_donViTinh.selectAll();
				return false;
			}
		}else {
			showMessage("Đơn vị tính không được để trống!!!");
			return false;
		}
		//check don vi tinh le
		if (donViTinhLe.length() > 0) {
			if (!(donViTinhLe.matches("[^!@#$%^&*()_+]+"))) {
				showMessage("Đơn vị tính lẻ không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
				txt_donViTinhLe.requestFocus();
				txt_donViTinhLe.selectAll();
				return false;
			}
		}else {
			showMessage("Đơn vị tính lẻ không được để trống!!!");
			return false;
		}
		//check don vi tinh chan
		if (donViTinhChan.length() > 0) {
			if (!(donViTinhChan.matches("[^!@#$%^&*()_+]+"))) {
				showMessage("Đơn vị tính chẵn không đúng định dạng (không được dùng kí tự đặc biệt)!!!");
				txt_donViTinhChan.requestFocus();
				txt_donViTinhChan.selectAll();
				return false;
			}
		}else {
			showMessage("Đơn vị tính chẵn không được để trống!!!");
			return false;
		}
		//check gia nhap le
		if (giaNhapLe.length() > 0) {
			try {
				double nhapLe = Double.parseDouble(giaNhapLe);
				if (!(nhapLe > 0)) {
					showMessage("Giá nhập lẻ phải lớn hơn 0!!!");
					txt_giaNhapLe.requestFocus();
					txt_giaNhapLe.selectAll();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Giá nhập lẻ phải nhập số!!!");
				txt_giaNhapLe.requestFocus();
				txt_giaNhapLe.selectAll();
				return false;
			}
		}else {
			showMessage("Giá nhập lẻ không được để trống!!!");
			return false;
		}
		// check gia nhap le
		if (giaNhapChan.length() > 0) {
			try {
				double nhapChan = Double.parseDouble(giaNhapChan);
				if (!(nhapChan > 0)) {
					showMessage("Giá nhập chẵn phải lớn hơn 0!!!");
					txt_giaNhapChan.requestFocus();
					txt_giaNhapChan.selectAll();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Giá nhập chẵn phải nhập số!!!");
				txt_giaNhapChan.requestFocus();
				txt_giaNhapChan.selectAll();
				return false;
			}
		} else {
			showMessage("Giá nhập chẵn không được để trống!!!");
			return false;
		}
		//gia ban le
		if (giaBanLe.length() > 0) {
			try {
				double banLe = Double.parseDouble(giaBanLe);
				if (!(banLe > 0)) {
					showMessage("Giá bán lẻ phải lớn hơn 0!!!");
					txt_giaBanLe.requestFocus();
					txt_giaBanLe.selectAll();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Giá bán lẻ phải nhập số!!!");
				txt_giaBanLe.requestFocus();
				txt_giaBanLe.selectAll();
				return false;
			}
		} else {
			showMessage("Giá bán lẻ không được để trống!!!");
			return false;
		}
		//gia ban chan
		if (giaBanChan.length() > 0) {
			try {
				double banChan = Double.parseDouble(giaBanChan);
				if (!(banChan > 0)) {
					showMessage("Giá bán chẵn phải lớn hơn 0!!!");
					txt_giaBanChan.requestFocus();
					txt_giaBanChan.selectAll();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Giá bán chẵn phải nhập số!!!");
				txt_giaBanChan.requestFocus();
				txt_giaBanChan.selectAll();
				return false;
			}
		} else {
			showMessage("Giá bán chẵn không được để trống!!!");
			return false;
		}
		
		return true;
	}

	private void clearData() {
		txt_maThuoc.setText("");
		txt_tenThuoc.setText("");
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
	}

	private void addRow() {
		String tenThuoc = txt_tenThuoc.getText();

		String donViTinh = txt_donViTinh.getText();
		String thanhPhanChinh = txt_thanhPhan.getText();
		String donViTinhLe = txt_donViTinhLe.getText();
		LocalDate hanSuDung = txt_hanSuDung.getDate();
		String dkBaoQuan = txt_dieuKienBQ.getText();
		String donViTinhChan = txt_donViTinhChan.getText();
		String ghiChu = txt_ghiChu.getText();
		double giaNhapLe = Double.parseDouble(txt_giaNhapLe.getText());
		double giaNhapChan = Double.parseDouble(txt_giaNhapChan.getText());
		double giaBanLe = Double.parseDouble(txt_giaBanLe.getText());
		double giaBanChan = Double.parseDouble(txt_giaNhapChan.getText());
		
		NhomThuoc nhomThuoc = dsNhomThuoc.get(txt_maNhom.getSelectedIndex());
		NhaCungCap nhaCC = dsNhaCungCap.get(txt_maNCC.getSelectedIndex());
		
		Thuoc thuocMoi = new Thuoc(tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan,
				donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
		
		try {
			thuocMoi = thuoc_dao.themThuoc(thuocMoi);
			System.out.println(thuocMoi);
			Kho kho = new Kho(0, thuocMoi);
			System.out.println(kho);
			kho_dao.themThuocKho(kho);
			showMessage("Thêm thành công");
		} catch (Exception e) {
			showMessage("Thêm không thành công");
		}
	}

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}
}