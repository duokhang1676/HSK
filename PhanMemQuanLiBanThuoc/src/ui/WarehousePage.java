package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.tabbedui.VerticalLayout;

import components.ColorConsts;
import components.Header;
import dao.KhoDao;
import dao.MaGiamGiaDao;
import dao.NhaCungCapDao;
import dao.NhomThuocDao;
import dao.ThuocDao;
import entity.Kho;
import entity.NhaCungCap;
import entity.NhomThuoc;
import entity.Thuoc;

public class WarehousePage extends BasePage implements ActionListener, MouseListener {

	private JComboBox<String> txt_maNCC;
	private JComboBox<String> txt_maNhom_timKiem;

	private JTextField txt_maThuoc;
	private JTextField txt_tenThuoc;
	private JTextField txt_donViTinh;
	private JTextField txt_donViTinhLe;
	private JTextField txt_donViTinhChan;
	private JTextField txt_ngayHetHan;
	private JTextField txt_giaNhapLe;
	private JTextField txt_giaNhapChan;
	private JTextField txt_timKiem;
	private JTextField txt_soLuongTrongKho;

	private DefaultTableModel prod_model;
	private JTable prod_table;

	private JButton btn_timKiem;
	private JButton btn_them;
	private JButton btn_suaSoLuong;
	private JButton btn_xoaTrang;
	private JButton btn_luu;
	private JButton btn_lamMoi;

	private KhoDao khoDao;
	private NhomThuocDao nhomThuocDao;

	public WarehousePage() {
		super();

	}

	@Override
	protected JPanel onCreateNestedContainerView() {
		khoDao = new KhoDao();
		nhomThuocDao = new NhomThuocDao();

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

		timKiem_left.add(jl_timKiem);
		timKiem_left.add(txt_timKiem);
		timKiem_left.add(btn_timKiem);
		timKiem_right.add(jl_maNhom);
		timKiem_right.add(txt_maNhom_timKiem);
		timKiem_right.add(Box.createHorizontalStrut(10));

		jp_timKiem.add(timKiem_left);
		jp_timKiem.add(Box.createHorizontalStrut(15));
		jp_timKiem.add(timKiem_right);
		/**
		 * table panel
		 */
		String[] cols_name = { "Mã thuốc", "Tên thuốc", "Nhóm thuốc", "Ngày hết hạn", "Đơn vị tính", "Số lượng" };
		prod_model = new DefaultTableModel(cols_name, 0);
		prod_table = new JTable(prod_model);
		JScrollPane js_prodTable = new JScrollPane(prod_table);

		getAllNhomThuoc();
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

		btn_suaSoLuong = new JButton("Sử số lượng");
		btn_suaSoLuong.setIcon(new ImageIcon("icon\\ic_clear.png"));
		btn_suaSoLuong.setPreferredSize(new Dimension(width, height));
		btn_suaSoLuong.setBackground(Color.decode(ColorConsts.BackgroundColor));

		btn_xoaTrang = new JButton("Xóa thông tin");
		btn_xoaTrang.setIcon(new ImageIcon("icon\\ic_clearInfo.png"));
		btn_xoaTrang.setPreferredSize(new Dimension(width, height));
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.BackgroundColor));

		btn_luu = new JButton("Lọc");
		btn_luu.setIcon(new ImageIcon("icon\\ic_sort.png"));
		btn_luu.setPreferredSize(new Dimension(width, height));
		btn_luu.setBackground(Color.decode(ColorConsts.BackgroundColor));

		btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.setPreferredSize(new Dimension(width, height));
		btn_lamMoi.setBackground(Color.decode(ColorConsts.BackgroundColor));

		jp_btnRight.add(btn_them);
		jp_btnRight.add(btn_suaSoLuong);
		jp_btnRight.add(btn_xoaTrang);
		jp_btnRight.add(btn_luu);
		jp_btnRight.add(btn_lamMoi);
		jp_btnRight.setBackground(Color.decode(ColorConsts.PrimaryColor));

		jp_button.add(jp_btnRight);

		JPanel jp_tableProd = new JPanel();
		jp_tableProd.setBackground(Color.decode(ColorConsts.ForegroundColor));
		jp_tableProd.setLayout(new BorderLayout());
		jp_tableProd.add(jp_timKiem, BorderLayout.NORTH);
		jp_tableProd.add(js_prodTable, BorderLayout.CENTER);
		jp_tableProd.add(jp_button, BorderLayout.SOUTH);

		JPanel jp_txtProd = new JPanel();
		jp_txtProd.setLayout(new BorderLayout());

		JLabel jl_maThuoc = new JLabel("Mã thuốc: ");
		JLabel jl_tenThuoc = new JLabel("Tên thuốc: ");
		JLabel jl_donViTinh = new JLabel("Đơn vị tính: ");
		JLabel jl_donViTinhLe = new JLabel("Đơn vị tính lẻ: ");
		JLabel jl_donViTinhChan = new JLabel("Đơn vị tính chẵn: ");
		JLabel jl_giaNhapLe = new JLabel("Giá nhập lẻ: ");
		JLabel jl_giaNhapChan = new JLabel("Giá nhập chẵn: ");
		JLabel jl_ngayHetHan = new JLabel("Ngày hết hạn: ");
		JLabel jl_soLuongTrongKho = new JLabel("Số lượng trong kho: ");

		txt_maThuoc = new JTextField();
		txt_maThuoc.setEditable(false);

		txt_tenThuoc = new JTextField();
		txt_tenThuoc.setEditable(false);

		txt_maNCC = new JComboBox<String>();
		txt_maNCC.setEditable(false);

		txt_donViTinh = new JTextField();
		txt_donViTinh.setEditable(false);

		txt_donViTinhLe = new JTextField();
		txt_donViTinhLe.setEditable(false);

		txt_donViTinhChan = new JTextField();
		txt_donViTinhChan.setEditable(false);

		txt_ngayHetHan = new JTextField();
		txt_ngayHetHan.setEditable(false);

		txt_giaNhapLe = new JTextField();
		txt_giaNhapLe.setEditable(false);

		txt_giaNhapChan = new JTextField();
		txt_giaNhapChan.setEditable(false);

		txt_soLuongTrongKho = new JTextField();
		txt_soLuongTrongKho.setEditable(false);

		Box b1, b2, b4, b8, b9, b10, b11, b12, b3, b112;
		int heightStrut = 10, widthStrut = 10;
		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setPreferredSize(new Dimension(500, right.getPreferredSize().height));

		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b8 = Box.createHorizontalBox();
		b9 = Box.createHorizontalBox();
		b10 = Box.createHorizontalBox();
		b11 = Box.createHorizontalBox();
		b12 = Box.createHorizontalBox();
		b112 = Box.createHorizontalBox();

		b1.add(jl_maThuoc);
		b1.add(txt_maThuoc);

		b4.add(jl_tenThuoc);
		b4.add(txt_tenThuoc);

		b8.add(jl_donViTinh);
		b8.add(txt_donViTinh);

		b9.add(jl_donViTinhLe);
		b9.add(txt_donViTinhLe);

		b11.add(jl_donViTinhChan);
		b11.add(txt_donViTinhChan);

		b10.add(jl_giaNhapLe);
		b10.add(txt_giaNhapLe);

		b12.add(jl_giaNhapChan);
		b12.add(txt_giaNhapChan);

		b3.add(jl_ngayHetHan);
		b3.add(txt_ngayHetHan);

		b112.add(jl_soLuongTrongKho);
		b112.add(txt_soLuongTrongKho);

		right.add(b1);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b4);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b8);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b9);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b11);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b10);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b12);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b3);
		right.add(Box.createVerticalStrut(heightStrut));
		right.add(b112);

		prod_table.addMouseListener(this);
		btn_them.addActionListener(this);
		btn_suaSoLuong.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		btn_luu.addActionListener(this);
		btn_timKiem.addActionListener(this);
		btn_lamMoi.addActionListener(this);

		jp_prodBody.add(jp_tableProd, BorderLayout.CENTER);
		jp_prodBody.add(right, BorderLayout.EAST);
		return jp_prodBody;
	}

	private void docDuLieuVaoTable() {
		while (prod_model.getRowCount() > 0) {
			prod_model.removeRow(0);
		}
		for (Kho kho : khoDao.getAllTonKhoTheoThuoc()) {
			prod_model.addRow(new String[] { String.valueOf(kho.getThuoc().getMaThuoc()), kho.getThuoc().getTenThuoc(),
					kho.getThuoc().getNhomThuoc().getTenNhomThuoc(), kho.getThuoc().getHanSuDung().toString(),
					kho.getThuoc().getDonViTinh(), String.valueOf(kho.getSoLuong()) });
		}
	}

	public void getAllNhomThuoc() {
		txt_maNhom_timKiem.removeAll();
		for (NhomThuoc nhomThuoc : nhomThuocDao.getAllData()) {
			txt_maNhom_timKiem.addItem(nhomThuoc.getTenNhomThuoc());
		}
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header().addTitle("Kho").createView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = prod_table.getSelectedRow();
		int maThuoc = Integer.parseInt(prod_model.getValueAt(row, 0).toString());

		Kho kho = khoDao.getTonKhoTheoThuoc(maThuoc);

		if (kho != null) {
			txt_maThuoc.setText(String.valueOf(kho.getThuoc().getMaThuoc()));
			txt_tenThuoc.setText(kho.getThuoc().getTenThuoc());

			txt_donViTinh.setText(kho.getThuoc().getDonViTinh());
			txt_donViTinhLe.setText(kho.getThuoc().getDonViTinhLe());
			txt_donViTinhChan.setText(kho.getThuoc().getDonViTinhChan());

			txt_giaNhapLe.setText(String.valueOf(kho.getThuoc().getGiaNhapLe()));
			txt_giaNhapChan.setText(String.valueOf(kho.getThuoc().getGiaNhapChan()));

			txt_ngayHetHan.setText(kho.getThuoc().getHanSuDung().toString());

			txt_soLuongTrongKho.setText(String.valueOf(kho.getSoLuong()));
		}
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
			new CreateProductFrm();
		}
		if (src.equals(btn_xoaTrang)) {
			clearData();
		}
		if (src.equals(btn_timKiem)) {
			searchData();
		}
		if (src.equals(btn_lamMoi)) {
			docDuLieuVaoTable();
		}

		if (src.equals(btn_suaSoLuong)) {
			int row = prod_table.getSelectedRow();
			
			int maThuoc = Integer.parseInt(prod_model.getValueAt(row, 0).toString());

			try {
				String input = JOptionPane.showInputDialog("Nhập số lượng thuốc: ");
				
				int soLuong = Integer.parseInt(input);
				if (soLuong < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
					return;
				}
			
				
				if (khoDao.updateSoLuongTonKho(maThuoc, soLuong)) {
					txt_soLuongTrongKho.setText(String.valueOf(soLuong));
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					docDuLieuVaoTable();
					
					return;
				}	
				
				JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
			}
		}
	}

	private void groupByName() {
//		int index_item = txt_maNhom_timKiem.getSelectedIndex() + 1;
//		List<Thuoc> dsThuoc = thuoc_dao.locThuocTheoTenNhom(index_item);
//		while (prod_table.getRowCount() > 0) {
//			prod_model.removeRow(0);
//		}
//		for (Thuoc t : dsThuoc) {
//			prod_model.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getHanSuDung(), t.getDonViTinh(),
//					t.getDonViTinhLe(), t.getDonViTinhChan(), t.getGiaNhapLe(), t.getGiaNhapChan(), t.getGiaBanLe(),
//					t.getGiaBanChan() });
//		}
	}

	private void searchData() {
//		// TODO Auto-generated method stub
//		String ma = txt_timKiem.getText().trim();
//		if (ma.isEmpty()) {
//			showMessage("Nhập thông tin cần tìm!");
//		} else {
//			Thuoc t = thuoc_dao.timThuocTheoTen(ma);
//			while (prod_table.getRowCount() > 0) {
//				prod_model.removeRow(0);
//			}
//			prod_model.addRow(new Object[] { t.getMaThuoc(), t.getTenThuoc(), t.getHanSuDung(), t.getDonViTinh(),
//					t.getDonViTinhLe(), t.getDonViTinhChan(), t.getGiaNhapLe(), t.getGiaNhapChan(), t.getGiaBanLe(),
//					t.getGiaBanChan() });
//		}
	}

	private void clearData() {
		txt_maThuoc.setText("");
		txt_tenThuoc.setText("");
		txt_ngayHetHan.setText("");
		txt_donViTinh.setText("");
		txt_donViTinhLe.setText("");
		txt_donViTinhChan.setText("");
		txt_giaNhapLe.setText("");
		txt_giaNhapChan.setText("");
		txt_soLuongTrongKho.setText("");
	}

	private void showMessage(String string) {
		JOptionPane.showMessageDialog(this, string);
	}
}
