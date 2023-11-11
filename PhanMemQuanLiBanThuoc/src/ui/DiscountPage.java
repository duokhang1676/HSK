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

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import components.Header;

public class DiscountPage extends BasePage{

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

	@Override
	protected JPanel onCreateNestedContainerView() {
		// TODO Auto-generated method stub
		JPanel jp_main = new JPanel();
		jp_main.setLayout(new BorderLayout());
		/**
		 * table model
		 */
		JPanel jp_table = new JPanel();
		jp_table.setLayout(new BorderLayout());
		String[] cols_name = {"Mã giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Phần trăm giảm giá", "Mô tả"};
		model_discount = new DefaultTableModel(cols_name, 0);
		table_discount = new JTable(model_discount);
		JScrollPane js_table = new JScrollPane(table_discount);
		
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
		
		btn_sua = new JButton("Sửa");
		btn_sua.setIcon(new ImageIcon("icon\\ic_sort.png"));
		btn_sua.setPreferredSize(new Dimension(width, height));
		btn_sua.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.setPreferredSize(new Dimension(width, height));
		btn_lamMoi.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		jp_button.add(btn_them);
		jp_button.add(btn_xoa);
		jp_button.add(btn_xoaTrang);
		jp_button.add(btn_sua);
		jp_button.add(btn_lamMoi);
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		

		jp_table.add(js_table, BorderLayout.CENTER);
		jp_table.add(jp_button, BorderLayout.SOUTH);
		/**
		 * Panel Information
		 */
		JPanel jp_info = new JPanel();
		
		JLabel jl_maGiamGia = new JLabel("Mã giảm giá: ");
		JLabel jl_ngayBatDau = new JLabel("Ngày bắt đầu: ");
		JLabel jl_ngayKetThuc = new JLabel("Ngày kết thúc: ");
		JLabel jl_moTa = new JLabel("Mô tả: ");
		JLabel jl_phanTramGiamGia = new JLabel("Phần trăm giảm giá: ");
		
		jl_maGiamGia.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayBatDau.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayKetThuc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_moTa.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		
		txt_maGiamGia = new JTextField();
		txt_maGiamGia.setEditable(false);
		txt_ngayBatDau = new DatePicker();
		txt_ngayBatDau.setEnabled(false);
		txt_ngayKetThuc = new DatePicker();
		txt_ngayKetThuc.setEnabled(false);
		txt_phanTramGiamGia = new JTextField();
		txt_phanTramGiamGia.setEditable(false);
		
		txt_moTa = new JTextArea(3,2);
		txt_moTa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		Box b, b1, b2, b3, b4, b5;
		
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		
//		b1.add(jl_maGiamGia);
//		b1
		
		
		
		jp_main.add(jp_table, BorderLayout.CENTER);
		jp_main.add(jp_info, BorderLayout.EAST);
		
		return jp_main;
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		return new Header().addTitle("Các Chương Trình Giảm Giá").createView();
	}

}
