package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.print.event.PrintJobListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.ColorConsts;
import components.Header;

public class CustomerPage extends BasePage {
	
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtHo;
	private JTextField txtSdt;
	private JTextField txt_timKiemTheoMa;
	private JButton btn_timKiem;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_luu;
	private DefaultTableModel cust_model;
	private JTable cust_table;
	
	
	public CustomerPage() {
		super();
	}
	
	@Override
	protected JPanel onCreateNestedContainerView() {
		JPanel jp_cusBody = new JPanel();
		jp_cusBody.setLayout(new BorderLayout());
		jp_cusBody.setBackground(Color.decode(ColorConsts.BackgroundColor));

		JLabel jl_Ma = new JLabel("Mã  khách hàng :");
		JLabel jl_Ho = new JLabel("Họ :");
		JLabel jl_Ten = new JLabel("Tên :");
		JLabel jl_Sdt = new JLabel("Số điện thoại :");
		JLabel jl_timkiem = new JLabel("Tìm theo mã khách :");
		
		txtMa = new JTextField();
		txtHo = new JTextField();
		txtTen = new JTextField();
		txtSdt = new JTextField();
		txt_timKiemTheoMa = new JTextField();
		
		jl_Ma.setPreferredSize(jl_Ma.getPreferredSize());
		jl_Ho.setPreferredSize(jl_Ma.getPreferredSize());
		jl_Ten.setPreferredSize(jl_Ma.getPreferredSize());
		jl_Sdt.setPreferredSize(jl_Ma.getPreferredSize());
		jl_timkiem.setPreferredSize(jl_Ma.getPreferredSize());
		
		Box b , b1 , b2 , b3 ;
		int heightStrut = 10 , widthStrut = 10;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		
		
		b1.add(jl_Ma);
		b1.add(txtMa);
		b1.add(Box.createHorizontalStrut(widthStrut));
		
		b2.add(jl_Ho);
		b2.add(txtHo);
		b2.add(Box.createHorizontalStrut(widthStrut));
		b2.add(jl_Ten);
		b2.add(txtTen);
		
		
		b3.add(jl_Sdt);
		b3.add(txtSdt);
		b3.add(Box.createHorizontalStrut(widthStrut));

		b.add(b1);
		b.add(b2);
		b.add(b3);
		
	

		String [] cols_name = {"Mã khách hàng" ,"Tên khách hàng" ,"Số điện thoại" ,"Quầy mua"};
		cust_model = new DefaultTableModel(cols_name , 0);
		cust_table = new JTable(cust_model);
		/*
		 * button
		 * 
		 */
		int width = 130 , height = 40;
		JPanel jp_button = new JPanel();
		JPanel jp_left = new JPanel();
		JPanel jp_right = new JPanel();
		txt_timKiemTheoMa = new  JTextField();
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
		
		
		jp_left.add(btn_timKiem);
		jp_left.add(txt_timKiemTheoMa);
		
		
		jp_right.add(btn_them);
		jp_right.add(btn_xoa);
		jp_right.add(btn_xoaTrang);
		jp_right.add(btn_luu);
		
		
		jp_button.setLayout(new BorderLayout());
		jp_button.add(jp_left, BorderLayout.WEST);
		jp_button.add(jp_right, BorderLayout.EAST);
		
		jp_cusBody.add(b, BorderLayout.NORTH);
		jp_cusBody.add(new JScrollPane(cust_table), BorderLayout.CENTER);
		jp_cusBody.add(jp_button, BorderLayout.SOUTH);
		return jp_cusBody;
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		
		return new Header()
				.addTitle("Khách hàng")
				.createView();
	}
}
