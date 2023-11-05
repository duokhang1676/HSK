package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import com.toedter.calendar.JDateChooser;

import components.ColorConsts;
import components.Header;

public class ProductPage extends BasePage {
	
	private JTextField txt_maThuoc;
	private JTextField txt_tenThuoc;
	private JTextField txt_maNCC;
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
	private JTextField txt_maNhom;
	private DefaultTableModel prod_model;
	private JTable prod_table;
	private JTextField txt_timKiemTheoMa;
	private JButton btn_timKiem;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_luu;

	public ProductPage() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected JPanel onCreateNestedContainerView() {
		// TODO Auto-generated method stub
		JPanel jp_prodBody = new JPanel();
		jp_prodBody.setLayout(new BorderLayout());
		
		/**
		 * infomation panel
		 * 
		 */
		JPanel jp_txtProd = new JPanel();
		jp_txtProd.setLayout(new BorderLayout());
		JPanel jp_hinhAnh = new JPanel();
		jp_hinhAnh.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_hinhAnh.setBorder(BorderFactory.createTitledBorder("Hình ảnh"));
		JLabel jl_hinhAnh = new JLabel();
		jl_hinhAnh.setIcon(new ImageIcon("img\\img_logo.png"));
		
		jp_hinhAnh.add(jl_hinhAnh);
		
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
		
		txt_maThuoc = new JTextField();
		txt_tenThuoc = new JTextField();
		txt_maNCC = new JTextField();
		txt_donViTinh = new JTextField();
		txt_thanhPhan = new JTextField();
		txt_donViTinhLe = new JTextField();
		txt_donViTinhChan = new JTextField();
		txt_hanSuDung = new JTextField();
//		txt_hanSuDung = new JDateChooser();
		txt_dieuKienBQ = new JTextField();
		txt_ghiChu = new JTextArea(3,2);
		txt_giaNhapLe = new JTextField();
		txt_giaNhapChan = new JTextField();
		txt_giaBanLe = new JTextField();
		txt_giaBanChan = new JTextField();
		txt_maNhom = new JTextField();
		
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
		
		Box b, b1, b2, b3, b4, b5, b6, b7;
		int heightStrut = 10, widthStrut = 10;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		b7 = Box.createHorizontalBox();
		
		b1.add(jl_maThuoc);
		b1.add(txt_maThuoc);
		b1.add(Box.createHorizontalStrut(widthStrut));
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

		b7.add(jl_ghiChu);
		b7.add(txt_ghiChu);
		
		
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
		
		
		jp_txtProd.add(jp_hinhAnh, BorderLayout.WEST);
		jp_txtProd.add(b, BorderLayout.CENTER);
		jp_txtProd.setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		/**
		 * table panel
		 */
		String[] cols_name = {"Mã thuốc", "Tên thuốc", "Hạn sử dụng", "Đơn vị tính",
				"Đơn vị tính lẻ", "Đơn vị tính chẵn", "Giá nhập lẻ", "Giá nhập chẵn",
				"Giá bán lẻ", "Giá bán chẵn"};
		prod_model = new DefaultTableModel(cols_name, 0);
		prod_table = new JTable(prod_model);
		JScrollPane js_prodTable = new JScrollPane(prod_table);
		
		JPanel jp_tableProd = new JPanel();
		jp_tableProd.setLayout(new BorderLayout());
		jp_tableProd.add(js_prodTable, BorderLayout.CENTER);
		
		/**
		 * button panel
		 */
		int width = 130, height = 40;
		JPanel jp_button = new JPanel();
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		JPanel jp_btnLeft = new JPanel();
		JPanel jp_btnRight = new JPanel();
		
		txt_timKiemTheoMa = new JTextField();
		txt_timKiemTheoMa.setPreferredSize(new Dimension(450,30));
		btn_timKiem = new JButton("Tìm kiếm");
		btn_timKiem.setIcon(new ImageIcon("icon\\ic_search.png"));
		btn_timKiem.setPreferredSize(new Dimension(width, height));
		btn_timKiem.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_them = new JButton("Thêm");
		btn_them.setIcon(new ImageIcon("icon\\ic_add.png"));
		btn_them.setPreferredSize(new Dimension(width, height));
		btn_them.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_xoa = new JButton("Xóa");
		btn_xoa.setIcon(new ImageIcon("icon\\ic_clear.png"));
		btn_xoa.setPreferredSize(new Dimension(width, height));
		btn_xoa.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_xoaTrang = new JButton("Xóa thông tin");
		btn_xoaTrang.setIcon(new ImageIcon("icon\\ic_clearInfo.png"));
		btn_xoaTrang.setPreferredSize(new Dimension(width, height));
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_luu = new JButton("Lưu");
		btn_luu.setIcon(new ImageIcon("icon\\ic_save.png"));
		btn_luu.setPreferredSize(new Dimension(width, height));
		btn_luu.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		jp_btnLeft.add(btn_timKiem);
		jp_btnLeft.add(txt_timKiemTheoMa);
		jp_btnLeft.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		jp_btnRight.add(btn_them);
		jp_btnRight.add(btn_xoa);
		jp_btnRight.add(btn_xoaTrang);
		jp_btnRight.add(btn_luu);
		jp_btnRight.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		jp_button.setLayout(new BorderLayout());
		jp_button.add(jp_btnLeft, BorderLayout.WEST);
		jp_button.add(jp_btnRight, BorderLayout.EAST);
		
		jp_prodBody.add(jp_txtProd, BorderLayout.NORTH);
		jp_prodBody.add(jp_tableProd, BorderLayout.CENTER);
		jp_prodBody.add(jp_button, BorderLayout.SOUTH);
		return jp_prodBody;
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		
		return new Header()
				.addTitle("Sản phẩm")
				.createView();
	}

}
