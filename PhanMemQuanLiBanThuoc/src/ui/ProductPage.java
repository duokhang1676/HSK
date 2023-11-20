package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

//import com.toedter.calendar.JDateChooser;

import components.ColorConsts;
import components.Header;
import dao.MaGiamGiaDao;
import dao.NhaCungCapDao;
import dao.NhomThuocDao;
import dao.ThuocDao;
import entity.MaGiamGia;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NhomThuoc;
import entity.Thuoc;

public class ProductPage extends BasePage implements ActionListener, MouseListener {

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
	private DefaultTableModel prod_model;
	private JTable prod_table;
	private JButton btn_timKiem;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_luu;
	private NhomThuocDao nhomThuoc_dao;
	private ThuocDao thuoc_dao;
	private JTextField txt_timKiem;
	private NhaCungCapDao nhaCungCap_dao;
	private JComboBox<String> txt_maNhom_timKiem;
	private JComboBox<String> txt_maNCC_timKiem;
	private JLabel incomeLabel;
	private JLabel orderCountLabel;
	private JLabel productCountLabel;
	private Object incomeInWeekChart;
	private MaGiamGiaDao maGiamGia_dao;
	private JButton btn_lamMoi;
	private JButton btn_sua;
	private JButton btn_hoanThanh;
	private ArrayList<NhomThuoc> listNhomThuoc;
	private ArrayList<NhaCungCap> listNhaCungCap;
	private JList suggestionList;
	private ArrayList<Thuoc> dsThuoc;
	private DefaultListModel<String> suggestionListModel;
	private ArrayList<Thuoc> dsThuocTemp = new ArrayList<Thuoc>();
	private JScrollPane listTimKiem;

	public ProductPage() {
		super();
	}

	@Override
	protected JPanel onCreateNestedContainerView() {
		nhomThuoc_dao = new NhomThuocDao();
		thuoc_dao = new ThuocDao();
		nhaCungCap_dao = new NhaCungCapDao();
		maGiamGia_dao = new MaGiamGiaDao();
		
		dsThuoc = thuoc_dao.getAllData();
		
		JPanel jp_prodBody = new JPanel();
		jp_prodBody.setLayout(new BorderLayout());
		Font commonFont = new Font("Arial", Font.PLAIN, 14);

		Box jp_timKiem, timKiem_left, timKiem_right;
		jp_timKiem = Box.createHorizontalBox();
		timKiem_left = Box.createHorizontalBox();
		timKiem_right = Box.createHorizontalBox();

		JLabel jl_timKiem = new JLabel("Tìm thuốc: ");
		jl_timKiem.setFont(commonFont);
		txt_timKiem = new JTextField();
		btn_timKiem = new JButton("Tìm kiếm");
		btn_timKiem.setIcon(new ImageIcon("icon\\ic_search.png"));
		btn_timKiem.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_timKiem.setForeground(Color.decode(ColorConsts.ForegroundColor));

		JLabel jl_maNhom = new JLabel("Mã nhóm thuốc: ");
		txt_maNhom_timKiem = new JComboBox<String>();

		JLabel jl_maNCC = new JLabel("Mã nhà cung cấp: ");
		txt_maNCC_timKiem = new JComboBox<String>();
		
		
		suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);
        listTimKiem = new JScrollPane(suggestionList);
        
		timKiem_left.add(jl_timKiem);
		timKiem_left.add(txt_timKiem);
		timKiem_left.add(btn_timKiem);
		timKiem_right.add(jl_maNhom);
		timKiem_right.add(txt_maNhom_timKiem);

		jp_timKiem.add(timKiem_left);
		jp_timKiem.add(Box.createHorizontalStrut(15));
		jp_timKiem.add(timKiem_right);

		/**
		 * doc du lieu nhom thuoc
		 */
		ArrayList<NhomThuoc> listNhomThuoc_timKiem = nhomThuoc_dao.getAllData();
		for (NhomThuoc nhomThuoc : listNhomThuoc_timKiem) {
			txt_maNhom_timKiem.addItem(nhomThuoc.getTenNhomThuoc());
		}

		/**
		 * table panel
		 */
		String[] cols_name = { "Mã thuốc", "Tên thuốc", "Hạn sử dụng", "Đơn vị tính", "Đơn vị tính lẻ",
				"Đơn vị tính chẵn", "Giá nhập lẻ", "Giá nhập chẵn", "Giá bán lẻ", "Giá bán chẵn" };
		prod_model = new DefaultTableModel(cols_name, 0);
		prod_table = new JTable(prod_model);
		
		JTableHeader headerTable =  prod_table.getTableHeader();
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));

		prod_table.setShowVerticalLines(false);
		prod_table.setRowHeight(40);
		prod_table.setFont(new Font("Arial", Font.PLAIN, 14));
		prod_table.setIntercellSpacing(new Dimension(0, 0));
		prod_table.setGridColor(Color.decode("#696969"));
		prod_table.setTableHeader(headerTable);
		JScrollPane js_prodTable = new JScrollPane(prod_table);

		setCellEditable();
		docDuLieuVaoTable();

		/**
		 * button panel
		 */
		int width = 130, height = 40;
		JPanel jp_button = new JPanel();
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		JPanel jp_btnRight = new JPanel();

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

		btn_luu = new JButton("Lọc");
		btn_luu.setIcon(new ImageIcon("icon\\ic_sort.png"));
		btn_luu.setPreferredSize(new Dimension(width, height));
		btn_luu.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_sua = new JButton("Sửa");
		btn_sua.setPreferredSize(new Dimension(width, height));
		btn_sua.setBackground(Color.decode(ColorConsts.BackgroundColor));
		btn_sua.setIcon(new ImageIcon("icon\\ic_write.png"));
		
		btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.setPreferredSize(new Dimension(width, height));
		btn_lamMoi.setBackground(Color.decode(ColorConsts.BackgroundColor));
		btn_lamMoi.setIcon(new ImageIcon("icon\\ic_refresh.png"));
		
		btn_hoanThanh = new JButton("Hoàn thành");
		btn_hoanThanh.setPreferredSize(new Dimension(width, height));
		btn_hoanThanh.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_hoanThanh.setForeground(Color.decode(ColorConsts.BackgroundColor));
		btn_hoanThanh.setVisible(false);
		
		jp_btnRight.add(btn_them);
		jp_btnRight.add(btn_xoa);
		jp_btnRight.add(btn_xoaTrang);
		jp_btnRight.add(btn_luu);
		jp_btnRight.add(btn_sua);
		jp_btnRight.add(btn_lamMoi);
		jp_btnRight.setBackground(Color.decode(ColorConsts.PrimaryColor));

		jp_button.add(jp_btnRight);

		

		JLayeredPane jp_tableSearch = new JLayeredPane();
	
		
		jp_tableSearch.add(js_prodTable, JLayeredPane.DEFAULT_LAYER);
		js_prodTable.setBounds(0, 0, 800, 635);
		jp_tableSearch.add(listTimKiem,JLayeredPane.PALETTE_LAYER);
		listTimKiem.setBounds(72, 0, 345, 250);
		listTimKiem.setVisible(false);
		
		txt_timKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_timKiem.getText().trim().toLowerCase()))
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
				}
				if (txt_timKiem.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_timKiem.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
		});
		txt_timKiem.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_timKiem.getText().trim().toLowerCase())) {
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
						dsThuocTemp.add(dsThuoc.get(i));
					}

				}
				if (txt_timKiem.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_timKiem.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_timKiem.getText().trim().toLowerCase())) {
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
						dsThuocTemp.add(dsThuoc.get(i));
					}
				}
				if (txt_timKiem.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_timKiem.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		suggestionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					txt_timKiem.setText(suggestionList.getSelectedValue().toString());
					listTimKiem.setVisible(false);
				}
			}
		});
		
		JPanel jp_tableProd = new JPanel();
		jp_tableProd.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_tableProd.setLayout(new BorderLayout());
		jp_tableProd.add(jp_timKiem, BorderLayout.NORTH);
		jp_tableProd.add(jp_tableSearch, BorderLayout.CENTER);
		jp_tableProd.add(jp_button, BorderLayout.SOUTH);

		/**
		 * infomation panel
		 * 
		 */

		JPanel jp_txtProd = new JPanel();
		jp_txtProd.setLayout(new BorderLayout());

		JLabel jl_maThuoc = new JLabel("Mã thuốc: ");
		JLabel jl_tenThuoc = new JLabel("Tên thuốc: ");
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
		JLabel jl_maGiamGia = new JLabel("Giảm giá: ");
		
		txt_maThuoc = new JTextField();
		txt_maThuoc.setEditable(false);

		txt_tenThuoc = new JTextField();
		txt_tenThuoc.setEditable(false);
//		txt_maNCC = new JTextField();

		txt_maNCC = new JComboBox<String>();
		txt_maNCC.setEditable(false);

		txt_donViTinh = new JTextField();
		txt_donViTinh.setEditable(false);

		txt_thanhPhan = new JTextField();
		txt_thanhPhan.setEditable(false);

		txt_donViTinhLe = new JTextField();
		txt_donViTinhLe.setEditable(false);

		txt_donViTinhChan = new JTextField();
		txt_donViTinhChan.setEditable(false);

		txt_hanSuDung = new JTextField();
		txt_hanSuDung.setEditable(false);
//		txt_hanSuDung = new JDateChooser();

		txt_dieuKienBQ = new JTextField();
		txt_dieuKienBQ.setEditable(false);

		txt_ghiChu = new JTextArea(3, 2);
		txt_ghiChu.setEditable(false);

		txt_giaNhapLe = new JTextField();
		txt_giaNhapLe.setEditable(false);

		txt_giaNhapChan = new JTextField();
		txt_giaNhapChan.setEditable(false);

		txt_giaBanLe = new JTextField();
		txt_giaBanLe.setEditable(false);

		txt_giaBanChan = new JTextField();
		txt_giaBanChan.setEditable(false);

//		txt_maNhom = new JTextField();
		txt_maNhom = new JComboBox<String>();
		txt_maNhom.setEditable(false);

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
		
		Box b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13;
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
		b9 = Box.createHorizontalBox();
		b10 = Box.createHorizontalBox();
		b11 = Box.createHorizontalBox();
		b12 = Box.createHorizontalBox();
		b13 = Box.createHorizontalBox();
		
		b1.add(jl_maThuoc);
		b1.add(txt_maThuoc);

		b2.add(jl_maNhom);
		b2.add(txt_maNhom);

		b3.add(jl_maNCC);
		b3.add(txt_maNCC);

		b4.add(jl_tenThuoc);
		b4.add(txt_tenThuoc);

		b5.add(jl_thanhPhan);
		b5.add(txt_thanhPhan);

		b6.add(jl_hanSuDung);
		b6.add(txt_hanSuDung);
//		b3.add(Box.createHorizontalStrut(50));

		b7.add(jl_dieuKienBQ);
		b7.add(txt_dieuKienBQ);

		b8.add(jl_donViTinh);
		b8.add(txt_donViTinh);

		b9.add(jl_donViTinhLe);
		b9.add(txt_donViTinhLe);
		b9.add(Box.createHorizontalStrut(widthStrut));
		b9.add(jl_donViTinhChan);
		b9.add(txt_donViTinhChan);

		b10.add(jl_giaNhapLe);
		b10.add(txt_giaNhapLe);
		b10.add(Box.createHorizontalStrut(widthStrut));
		b10.add(jl_giaNhapChan);
		b10.add(txt_giaNhapChan);

		b11.add(jl_giaBanLe);
		b11.add(txt_giaBanLe);
		b11.add(Box.createHorizontalStrut(widthStrut));
		b11.add(jl_giaBanChan);
		b11.add(txt_giaBanChan);

		
		b13.add(jl_ghiChu);
		b13.add(txt_ghiChu);
		
		
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
		b.add(b9);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b10);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b11);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b12);
		b.add(Box.createVerticalStrut(heightStrut));
		b.add(b13);
		b.add(Box.createVerticalStrut(heightStrut));
		
		JPanel jp_hoanThanh = new JPanel();
		jp_hoanThanh.setPreferredSize(new Dimension(width, height ));
		jp_hoanThanh.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_hoanThanh.add(btn_hoanThanh);
		
		b.add(jp_hoanThanh);
		
		
		
		/**
		 * doc du lieu nhom thuoc
		 */
		listNhomThuoc = nhomThuoc_dao.getAllData();
		for (NhomThuoc nhomThuoc : listNhomThuoc) {
			txt_maNhom.addItem(nhomThuoc.getTenNhomThuoc());
		}
		/**
		 * doc du lieu nhom nha cung cap
		 */
		listNhaCungCap = nhaCungCap_dao.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : listNhaCungCap) {
			txt_maNCC.addItem(nhaCungCap.getTenNhaCungCap());
		}
		
		


		incomeInWeekChart = ChartFactory.createBarChart("Doanh Thu 7 Ngày Gần Nhất", "Ngày trong tuần", "Doanh thu",
				getIncomeInWeekDateset(), PlotOrientation.VERTICAL, false, false, false);
		((JFreeChart) incomeInWeekChart).setBorderVisible(false);
		((JFreeChart) incomeInWeekChart).setBackgroundPaint(Color.white);

		CategoryPlot cplot = (CategoryPlot) ((JFreeChart) incomeInWeekChart).getPlot();
		BarRenderer r = (BarRenderer) ((JFreeChart) incomeInWeekChart).getCategoryPlot().getRenderer();
		r.setSeriesPaint(0, new Color(22, 120, 254));

		ChartPanel incomeInWeekChartPanel = new ChartPanel((JFreeChart) incomeInWeekChart);
		incomeInWeekChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		incomeInWeekChartPanel.setPreferredSize(new Dimension(300, 300));

		jp_txtProd.add(b, BorderLayout.NORTH);
		jp_txtProd.add(incomeInWeekChartPanel, BorderLayout.CENTER);
		jp_txtProd.setBackground(Color.decode(ColorConsts.ForegroundColor));

		/**
		 * add event
		 */
		prod_table.addMouseListener(this);
		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		btn_luu.addActionListener(this);
		btn_timKiem.addActionListener(this);
		btn_sua.addActionListener(this);
		btn_lamMoi.addActionListener(this);
		btn_hoanThanh.addActionListener(this);
		
		jp_prodBody.add(jp_tableProd, BorderLayout.CENTER);
		jp_prodBody.add(jp_txtProd, BorderLayout.EAST);
		return jp_prodBody;
	}

	private CategoryDataset getIncomeInWeekDateset() {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(1000000, "Doanh thu", "T2");
		dataset.addValue(500000, "Doanh thu", "T3");
		dataset.addValue(200000, "Doanh thu", "T4");

		dataset.addValue(2000000, "Doanh thu", "T5");
		dataset.addValue(300000, "Doanh thu", "T6");
		dataset.addValue(500000, "Doanh thu", "T7");
		dataset.addValue(2000000, "Doanh thu", "CN");

		return dataset;

	}

	private void docDuLieuVaoTable() {
		while (prod_model.getRowCount() > 0) {
			prod_model.removeRow(0);
		}
		
		List<Thuoc> dsThuoc = thuoc_dao.getAllData();
		for (Thuoc t : dsThuoc) {
		
			prod_model.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getHanSuDung(), t.getDonViTinh(),
					t.getDonViTinhLe(), t.getDonViTinhChan(), t.getGiaNhapLe(), t.getGiaNhapChan(), t.getGiaBanLe(),
					t.getGiaBanChan() });
		}
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub

		return new Header().addTitle("Sản phẩm").createView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = prod_table.getSelectedRow();
		String tenThuoc = prod_model.getValueAt(row, 1).toString();
		Thuoc thuoc = thuoc_dao.timThuocTheoTen(tenThuoc);
		
		
		int maNCC = thuoc.getNhaCungCap().getMaNhaCungCap();
		txt_maNCC.setSelectedItem(thuoc.getNhaCungCap().getTenNhaCungCap());
		
		txt_maThuoc.setText(String.valueOf(thuoc.getMaThuoc()));
		txt_tenThuoc.setText(thuoc.getTenThuoc());
		txt_thanhPhan.setText(thuoc.getThanhPhanChinh());

			String hanSuDung = String.valueOf(thuoc.getHanSuDung());
			txt_hanSuDung.setText(hanSuDung);

			txt_dieuKienBQ.setText(thuoc.getDieuKienBaoQuan());
			txt_donViTinh.setText(thuoc.getDonViTinh());
			txt_donViTinhLe.setText(thuoc.getDonViTinhLe());
			txt_donViTinhChan.setText(thuoc.getDonViTinhChan());

			String giaNhapLe = String.valueOf(thuoc.getGiaNhapLe());
			String giaNhapChan = String.valueOf(thuoc.getGiaNhapChan());
			String giaBanLe = String.valueOf(thuoc.getGiaBanLe());
			String giaBanChan = String.valueOf(thuoc.getGiaBanChan());

			txt_giaNhapLe.setText(giaNhapLe);
			txt_giaNhapChan.setText(giaNhapChan);
			txt_giaBanLe.setText(giaBanLe);
			txt_giaBanChan.setText(giaBanChan);

			txt_ghiChu.setText(thuoc.getGhiChu());

		txt_maNhom.setSelectedItem(thuoc.getNhomThuoc().getTenNhomThuoc());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btn_them)) {
//			addRow();
			new CreateProductFrm();
		} else if (src.equals(btn_xoa)) {
			deleteRow();
		} else if (src.equals(btn_xoaTrang)) {
			clearData();
		} else if (src.equals(btn_luu)) {
			groupByName();
		} else if (src.equals(btn_timKiem)) {
			searchData();
		}else if (src.equals(btn_lamMoi)) {
			docDuLieuVaoTable();
			clearData();
		}else if (src.equals(btn_sua)) {
			setEditTextField();
		}else if (src.equals(btn_hoanThanh)) {
			updateData();
		}
	}

	private void updateData() {
		// TODO Auto-generated method stub
		int selectedRow = prod_table.getSelectedRow();
		int maThuoc = (int) prod_model.getValueAt(selectedRow, 0);
		
		String tenThuoc = txt_tenThuoc.getText();

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
		
		NhomThuoc nhomThuoc = listNhomThuoc.get(txt_maNhom.getSelectedIndex());
		NhaCungCap nhaCC = listNhaCungCap.get(txt_maNCC.getSelectedIndex());
		
		Thuoc t = new Thuoc(tenThuoc, nhaCC, donViTinh, thanhPhanChinh, donViTinhLe, hanSuDung, dkBaoQuan,
				donViTinhChan, ghiChu, giaNhapLe, giaNhapChan, giaBanLe, giaBanChan, nhomThuoc);
		try {
			thuoc_dao.suaThuoc(t, maThuoc);
			showMessage("Sửa thành công");
			setUneditable();
		} catch (Exception e) {
			showMessage("Sửa không thành công");
		}
	}

	private void setUneditable() {
		// TODO Auto-generated method stub
		txt_maNCC.setEditable(false);
		txt_maNhom.setEditable(false);
		txt_tenThuoc.setEditable(false);
		txt_thanhPhan.setEditable(false);
		txt_dieuKienBQ.setEditable(false);
		txt_donViTinh.setEditable(false);
		txt_donViTinhChan.setEditable(false);
		txt_donViTinhLe.setEditable(false);
		txt_ghiChu.setEditable(false);
		txt_giaBanChan.setEditable(false);
		txt_giaBanLe.setEditable(false);
		txt_giaNhapChan.setEditable(false);
		txt_giaNhapLe.setEditable(false);
		txt_hanSuDung.setEditable(false);
		
		btn_hoanThanh.setVisible(false);
	}

	private void setEditTextField() {
		// TODO Auto-generated method stub
		int selectedRow = prod_table.getSelectedRow();
		if (selectedRow == -1) {
			showMessage("Phải chọn dòng để sửa!");
		}else {
			txt_maNCC.setEditable(true);
			txt_maNhom.setEditable(true);
			txt_tenThuoc.setEditable(true);
			txt_thanhPhan.setEditable(true);
			txt_dieuKienBQ.setEditable(true);
			txt_donViTinh.setEditable(true);
			txt_donViTinhChan.setEditable(true);
			txt_donViTinhLe.setEditable(true);
			txt_ghiChu.setEditable(true);
			txt_giaBanChan.setEditable(true);
			txt_giaBanLe.setEditable(true);
			txt_giaNhapChan.setEditable(true);
			txt_giaNhapLe.setEditable(true);
			txt_hanSuDung.setEditable(true);
			
			btn_hoanThanh.setVisible(true);
		}
	}

	private void groupByName() {
		// TODO Auto-generated method stub
		int index_item = txt_maNhom_timKiem.getSelectedIndex() + 1;
		List<Thuoc> dsThuoc = thuoc_dao.locThuocTheoTenNhom(index_item);
		while (prod_table.getRowCount() > 0) {
			prod_model.removeRow(0);
		}
		for (Thuoc t : dsThuoc) {
			prod_model.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getHanSuDung(), t.getDonViTinh(),
					t.getDonViTinhLe(), t.getDonViTinhChan(), t.getGiaNhapLe(), t.getGiaNhapChan(), t.getGiaBanLe(),
					t.getGiaBanChan() });
		}
	}

	private void searchData() {
		// TODO Auto-generated method stub
		listTimKiem.setVisible(false);
		String ma = txt_timKiem.getText().trim();
		if (ma.isEmpty()) {
			showMessage("Nhập thông tin cần tìm!");
		} else {
			Thuoc t = thuoc_dao.timThuocTheoTen(ma);
			if(t==null)return;
			while (prod_table.getRowCount() > 0) {
				prod_model.removeRow(0);
			}
			prod_model.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getHanSuDung(), t.getDonViTinh(),
					t.getDonViTinhLe(), t.getDonViTinhChan(), t.getGiaNhapLe(), t.getGiaNhapChan(), t.getGiaBanLe(),
					t.getGiaBanChan() });
		}
		txt_timKiem.requestFocus();
		txt_timKiem.selectAll();
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maThuoc.setText("");
//		txt_maNCC.setSelectedIndex(0);
		txt_tenThuoc.setText("");
		txt_thanhPhan.setText("");
		txt_dieuKienBQ.setText("");
		txt_hanSuDung.setText("");
		txt_donViTinh.setText("");
		txt_donViTinhLe.setText("");
		txt_donViTinhChan.setText("");
		txt_giaNhapLe.setText("");
		txt_giaNhapChan.setText("");
		txt_giaBanLe.setText("");
		txt_giaBanChan.setText("");
		txt_ghiChu.setText("");
//		txt_maNhom.setSelectedIndex(0);
	}

	private void deleteRow() {
		// TODO Auto-generated method stub
		int row = prod_table.getSelectedRow();
		if (row == -1) {
			showMessage("Phải chọn dòng xóa!");
		}else if (JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa dòng này không??", "Warning!!!",
				JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			int ma = (int) prod_model.getValueAt(row, 0);
			if (thuoc_dao.xoaTheoMa(ma)) {
				prod_model.removeRow(row);
				showMessage("Xóa thành công!");
			}

		}
		
	}

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}
	public void setCellEditable() {
		for (int i = 0; i < prod_table.getColumnCount(); i++) {
				prod_table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
					@Override
					public boolean isCellEditable(EventObject e) {
						// Trả về false để ngăn chặn chỉnh sửa trực tiếp
						return false;
					}
				});
			}
	}

}
