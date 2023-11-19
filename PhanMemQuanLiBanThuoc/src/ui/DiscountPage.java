package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import components.Header;
import dao.MaGiamGiaDao;
import entity.MaGiamGia;

public class DiscountPage extends BasePage implements MouseListener, ActionListener{

	private DefaultTableModel model_discount;
	private JTable table_discount;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_sua;
	private JButton btn_lamMoi;
	private JTextField txt_maGiamGia;
	private DatePicker txt_ngayBatDau;
	private DatePicker txt_ngayKetThuc;
	private JTextField txt_phanTramGiamGia;
	private JTextArea txt_moTa;
	private MaGiamGiaDao maGiamGia_dao;
	private JTextField txt_maThuoc;

	@Override
	protected JPanel onCreateNestedContainerView() {
		// TODO Auto-generated method stub
		maGiamGia_dao = new MaGiamGiaDao();
		JPanel jp_main = new JPanel();
		jp_main.setLayout(new BorderLayout());
		/**
		 * table model
		 */
		JPanel jp_table = new JPanel();
		jp_table.setLayout(new BorderLayout());
		String[] cols_name = {"Mã giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Phần trăm giảm giá","Thuốc", "Mô tả"};
		model_discount = new DefaultTableModel(cols_name, 0);
		table_discount = new JTable(model_discount);
		JScrollPane js_table = new JScrollPane(table_discount);
		
		/**
		 * doc du lieu vao bang
		 */
		docDuLieuVaoTable();
		
		/**
		 * panel button
		 */
		int width = 130, height = 40;
		JPanel jp_button = new JPanel();
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
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
		
		
		btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.setPreferredSize(new Dimension(width, height));
		btn_lamMoi.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		jp_button.add(btn_them);
		jp_button.add(btn_xoa);
		jp_button.add(btn_xoaTrang);
		jp_button.add(btn_lamMoi);
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		

		jp_table.add(js_table, BorderLayout.CENTER);
		jp_table.add(jp_button, BorderLayout.SOUTH);
		/**
		 * Panel Information
		 */
		JPanel jp_info = new JPanel();
		jp_info.setLayout(new BorderLayout());
		
		JLabel jl_maGiamGia = new JLabel("Mã giảm giá: ");
		JLabel jl_ngayBatDau = new JLabel("Ngày bắt đầu: ");
		JLabel jl_ngayKetThuc = new JLabel("Ngày kết thúc: ");
		JLabel jl_moTa = new JLabel("Mô tả: ");
		JLabel jl_phanTramGiamGia = new JLabel("Phần trăm giảm giá: ");
		JLabel jl_maThuoc = new JLabel("Thuốc: ");
		
		jl_maGiamGia.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayBatDau.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayKetThuc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_moTa.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_maThuoc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		
		txt_maGiamGia = new JTextField();
		txt_maGiamGia.setEditable(false);
		txt_ngayBatDau = new DatePicker();
		txt_ngayBatDau.setEnabled(false);
		txt_ngayKetThuc = new DatePicker();
		txt_ngayKetThuc.setEnabled(false);
		txt_phanTramGiamGia = new JTextField();
		txt_phanTramGiamGia.setEditable(false);
		txt_maThuoc = new JTextField();
		txt_maThuoc.setEditable(false);
		
		txt_moTa = new JTextArea(3,2);
		txt_moTa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txt_moTa.setEditable(false);
		
		Box b, b1, b2, b3, b4, b5, b6;
		
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		
		b1.add(jl_maGiamGia);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txt_maGiamGia);
		
		b2.add(jl_ngayBatDau);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txt_ngayBatDau);

		b3.add(jl_ngayKetThuc);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txt_ngayKetThuc);
		
		b4.add(jl_phanTramGiamGia);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_phanTramGiamGia);
		
		b5.add(jl_moTa);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txt_moTa);
		
		b6.add(jl_maThuoc);
		b6.add(Box.createHorizontalStrut(10));
		b6.add(txt_maThuoc);
		
		b.add(b1);
		b.add(Box.createVerticalStrut(15));
		b.add(b2);
		b.add(Box.createVerticalStrut(15));
		b.add(b3);
		b.add(Box.createVerticalStrut(15));
		b.add(b4);
		b.add(Box.createVerticalStrut(15));
		b.add(b6);
		b.add(Box.createVerticalStrut(15));
		b.add(b5);
		b.add(Box.createVerticalStrut(15));
		
		
		jp_info.setPreferredSize(new Dimension(500,200));
		
		jp_info.add(b, BorderLayout.NORTH);
		/**
		 * add event
		 */
		btn_lamMoi.addActionListener(this);
		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		table_discount.addMouseListener(this);
		
		jp_main.add(jp_table, BorderLayout.CENTER);
		jp_main.add(jp_info, BorderLayout.EAST);
		
		
		
		return jp_main;
	}

	private void docDuLieuVaoTable() {
		// TODO Auto-generated method stub
		ArrayList<MaGiamGia> listMaGiamGias = maGiamGia_dao.getAllTbMaGiamGia();
		while (model_discount.getRowCount() > 0) {
			model_discount.removeRow(0);
		}
		for (MaGiamGia maGiamGia : listMaGiamGias) {
			
			model_discount.addRow(new Object[] {maGiamGia.getMaGiamGia(), maGiamGia.getNgayBatDau(), maGiamGia.getNgayKetThuc(),
					maGiamGia.getPhanTramGiamGia(),maGiamGia.getThuoc().getTenThuoc(), maGiamGia.getMoTa()});
		}
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		return new Header().addTitle("Các Chương Trình Giảm Giá").createView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btn_lamMoi)) {
			docDuLieuVaoTable();
		}else if (src.equals(btn_them)) {
			new CreateDiscountFrm();
		}else if (src.equals(btn_xoa)) {
			deleteRow();
		}else if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maGiamGia.setText("");
		txt_ngayBatDau.setText("");
		txt_ngayKetThuc.setText("");
		txt_phanTramGiamGia.setText("");
		txt_maThuoc.setText("");
		txt_moTa.setText("");
	}

	private void deleteRow() {
		// TODO Auto-generated method stub
		int row = table_discount.getSelectedRow();
		if (row == -1) {
			showMessage("Phải chọn dòng xóa!");
		}
		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa dòng này không?", "Warning!!!",
				JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
			int ma = (int) model_discount.getValueAt(row, 0);
			if (maGiamGia_dao.xoaTheoMa(ma)) {
				model_discount.removeRow(row);
				showMessage("Xóa thành công!");
			}
		}
	}

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table_discount.getSelectedRow();
		
		txt_maGiamGia.setText(model_discount.getValueAt(row, 0).toString());
		txt_ngayBatDau.setDate(LocalDate.parse(model_discount.getValueAt(row, 1).toString()));
		txt_ngayKetThuc.setDate(LocalDate.parse(model_discount.getValueAt(row, 2).toString()));
		txt_phanTramGiamGia.setText(model_discount.getValueAt(row, 3).toString());
		txt_maThuoc.setText(model_discount.getValueAt(row, 4).toString());
		txt_moTa.setText(model_discount.getValueAt(row, 5).toString());
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

}
