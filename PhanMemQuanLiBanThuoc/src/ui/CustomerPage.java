package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.print.event.PrintJobListener;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.tabbedui.VerticalLayout;

import components.ColorConsts;
import components.Header;
import components.TaiKhoanDangNhap;
import components.TopSaleProductView;
import dao.KhachHangDao;
import dao.QuayDao;
import entity.KhachHang;

public class CustomerPage extends BasePage implements MouseListener {

	private JTextField txtMa;
	private JTextField txtHoTen;
	private JTextField txtSdt;
	private JTextField ngayTaoSdt;
	private JButton btn_tim;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_sua;
	private JButton btn_luu;
	private JButton btn_lamMoi;
	private JButton hoanThanhBtn;

	private DefaultTableModel cusModel;
	private JTable cusTable;

	private JList topCustomerList;
	private DefaultListModel<Object> topCustomerModel;

	private JFreeChart incomeIPeriodChart;
	private ChartPanel incomeIPeriodChartPanel;

	private KhachHangDao khachHangDao;

	public CustomerPage() {
		super();

		khachHangDao = new KhachHangDao();

		getAllCustomers();
	}

	@Override
	protected JPanel onCreateNestedContainerView() {
		Font commonFont = new Font("Arials", Font.PLAIN, 14);
		Font commonButtonFont = new Font("Arials", Font.BOLD, 16);

		
		hoanThanhBtn = new JButton("Hoàn thành");
		hoanThanhBtn.setPreferredSize(new Dimension(130, 40));
		hoanThanhBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		hoanThanhBtn.setForeground(Color.decode(ColorConsts.BackgroundColor));
		hoanThanhBtn.setVisible(false);
		hoanThanhBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = cusTable.getSelectedRow();
				
				int maKhachHang = Integer.parseInt(cusModel.getValueAt(row, 0).toString());
				String soDienThoai = txtSdt.getText().trim();
				String hoVaTen = txtHoTen.getText().trim();
				
				KhachHang khachHang = new KhachHang(maKhachHang, hoVaTen, soDienThoai);
				
				if (khachHangDao.suaThongTinKhachHang(khachHang)) {
					getAllCustomers();
					
					txtMa.setEditable(false);
					txtHoTen.setEditable(false);
					txtSdt.setEditable(false);
					ngayTaoSdt.setEditable(false);
		
					hoanThanhBtn.setVisible(false);
					
					JOptionPane.showMessageDialog(null, "Cập nhập thông tin thành công");
				}
			}
		});
		
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.decode(ColorConsts.ForegroundColor));

		cusModel = new DefaultTableModel(
				new String[] { "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Ngày Tạo" }, 0);
		cusTable = new JTable(cusModel);

		JTableHeader headerTable = cusTable.getTableHeader();
		headerTable.setBackground(Color.decode(ColorConsts.BackgroundColor));
		headerTable.setFont(new Font("Arial", Font.BOLD, 14));
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		setCellEditable();

		cusTable.setRowHeight(40);
		cusTable.setShowVerticalLines(false);
		cusTable.setFont(new Font("Arial", Font.PLAIN, 14));
		cusTable.setRowHeight(40);
		cusTable.setIntercellSpacing(new Dimension(0, 0));
		cusTable.setGridColor(Color.decode("#696969"));
		cusTable.setTableHeader(headerTable);
		cusTable.addMouseListener(this);

		btn_them = new JButton("Thêm");
		btn_them.setIcon(new ImageIcon("icon\\ic_addLight.png"));
		btn_them.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_them.setFont(commonButtonFont);
		btn_them.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCustomerFrm().setVisible(true);
			}
		});
		btn_xoa = new JButton("Xóa");
		btn_xoa.setIcon(new ImageIcon("icon\\ic_clearLight.png"));
		btn_xoa.setPreferredSize(new Dimension(0, 50));
		btn_xoa.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_xoa.setFont(commonButtonFont);
		btn_xoa.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!TaiKhoanDangNhap.getNV().getChucVu().equals("Quản lý")) {
					JOptionPane.showMessageDialog(null, "Chỉ quản lý mới có quyền này!");
					return;
				}
				int row = cusTable.getSelectedRow();
				
				int maKhachHang = Integer.parseInt(cusModel.getValueAt(row, 0).toString());
				String tenKhachHang = cusModel.getValueAt(row, 1).toString();
				
				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Khách Hàng " + tenKhachHang, "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					if (khachHangDao.xoaKhachHang(maKhachHang)) {
						cusModel.removeRow(row);
					}
				}
			}
		});

		btn_xoaTrang = new JButton("Xóa trắng");
		btn_xoaTrang.setIcon(new ImageIcon("icon\\ic_clearInfoLight.png"));
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_xoaTrang.setFont(commonButtonFont);
		btn_xoaTrang.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_xoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMa.setText("");
				txtHoTen.setText("");
				txtSdt.setText("");
				ngayTaoSdt.setText("");
				
				hoanThanhBtn.setVisible(false);
				
				txtMa.setEditable(false);
				txtHoTen.setEditable(false);
				txtSdt.setEditable(false);
				ngayTaoSdt.setEditable(false);
			}
		});
		btn_sua = new JButton("Sửa");
		btn_sua.setIcon(new ImageIcon("icon\\ic_writeLight.png"));
		btn_sua.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_sua.setFont(commonButtonFont);
		btn_sua.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = cusTable.getSelectedRow();
				
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Phải chọn dòng để sửa");
					return;
				}
				
				txtMa.setEditable(false);
				txtHoTen.setEditable(true);
				txtSdt.setEditable(true);
				ngayTaoSdt.setEditable(false);
	
				hoanThanhBtn.setVisible(true);
			}
		});

		btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.setIcon(new ImageIcon("icon\\ic_refreshLight.png"));
		btn_lamMoi.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_lamMoi.setFont(commonButtonFont);
		btn_lamMoi.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllCustomers();
			}
		});

		JPanel footer = new JPanel(new GridLayout(1, 4));
		footer.add(btn_them);
		footer.add(btn_xoa);
		footer.add(btn_xoaTrang);
		footer.add(btn_sua);
		footer.add(btn_lamMoi);

		left.add(new JScrollPane(cusTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		left.add(footer, BorderLayout.SOUTH);

		JLabel tieuDeLabel = new JLabel("Khách Hàng");
		tieuDeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		tieuDeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

		Box maKhBox = Box.createHorizontalBox();
		JLabel maKHLb = new JLabel("Mã Khách Hàng");

		maKHLb.setFont(commonFont);
		txtMa = new JTextField("");
		txtMa.setEditable(false);
		maKhBox.add(maKHLb);
		maKhBox.add(txtMa);

		Box hoTenKHBox = Box.createHorizontalBox();
		JLabel hoTenLb = new JLabel("Họ và tên");

		hoTenLb.setFont(commonFont);
		hoTenLb.setPreferredSize(maKHLb.getPreferredSize());
		txtHoTen = new JTextField("");
		txtHoTen.setEditable(false);
		hoTenKHBox.add(hoTenLb);
		hoTenKHBox.add(txtHoTen);

		Box sdtBox = Box.createHorizontalBox();
		JLabel sdtLb = new JLabel("Số Điện Thoại");
		sdtLb.setFont(commonFont);
		sdtLb.setPreferredSize(maKHLb.getPreferredSize());
		txtSdt = new JTextField("");
		txtSdt.setEditable(false);
		sdtBox.add(sdtLb);
		sdtBox.add(txtSdt);
		
		
		Box ngayTaoBox = Box.createHorizontalBox();
		JLabel ngayTaoLb = new JLabel("Ngày tạo");
		ngayTaoLb.setFont(commonFont);
		ngayTaoLb.setPreferredSize(maKHLb.getPreferredSize());
		ngayTaoSdt = new JTextField("");
		ngayTaoSdt.setEditable(false);
		ngayTaoBox.add(ngayTaoLb);
		ngayTaoBox.add(ngayTaoSdt);

		JLabel doanhThuTheoThangLb = new JLabel("Tổng đơn hàng đã mua theo tháng");
		doanhThuTheoThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		doanhThuTheoThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));

		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setPreferredSize(new Dimension(500, left.getPreferredSize().height));
		right.add(tieuDeLabel);
		right.add(maKhBox);
		right.add(Box.createVerticalStrut(20));
		right.add(hoTenKHBox);
		right.add(Box.createVerticalStrut(20));
		right.add(sdtBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ngayTaoBox);
		right.add(Box.createVerticalStrut(20));
		right.add(hoanThanhBtn);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(left, BorderLayout.CENTER);
		panel.add(new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
				BorderLayout.EAST);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header().addTitle("Khách hàng").createView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		hoanThanhBtn.setVisible(false);
		
		txtMa.setEditable(false);
		txtHoTen.setEditable(false);
		txtSdt.setEditable(false);
		ngayTaoSdt.setEditable(false);
		
		
		int row = cusTable.getSelectedRow();
		
		txtMa.setText(cusModel.getValueAt(row, 0).toString());
		txtHoTen.setText(cusModel.getValueAt(row, 1).toString());
		txtSdt.setText(cusModel.getValueAt(row, 2).toString());
		ngayTaoSdt.setText(cusModel.getValueAt(row, 3).toString());
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

	private void getAllCustomers() {
		while (cusModel.getRowCount() > 0)
			cusModel.removeRow(0);

		for (KhachHang khachHang : khachHangDao.getAllKhachHang()) {
			cusModel.addRow(new String[] { String.valueOf(khachHang.getMaKhachHang()), khachHang.getTenKhachHang(),
					khachHang.getSoDienThoai(), khachHang.getNgayTao().toString() });
		}
	}
	public void setCellEditable() {
		for (int i = 0; i < cusTable.getColumnCount(); i++) {
				cusTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
					@Override
					public boolean isCellEditable(EventObject e) {
						// Trả về false để ngăn chặn chỉnh sửa trực tiếp
						return false;
					}
				});
			}
	}
}
