package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.tabbedui.VerticalLayout;

import components.ColorConsts;
import components.Header;
import components.TopSaleProductView;
import dao.NhanVienDao;
import dao.QuayDao;
import entity.KhachHang;
import entity.NhanVien;
import entity.Quay;

public class EmployeeManagerPage extends BasePage implements MouseListener {

	private JTextField txtManv;
	private JTextField txtTennv;
	private JTextField txtNgayVaoLam;
	private JTextField txtSdt;

	private JComboBox<String> caLamViecComboBox;
	private JComboBox<String> quayComboBox;
	private JComboBox<String> chucVuComboBox;

	private DefaultTableModel nhanVienModel;
	private JTable nhanVienTable;

	private JList topSaleProductList;
	private DefaultListModel<Object> topSaleProductModel;

	private JFreeChart incomeIPeriodChart;
	private ChartPanel incomeIPeriodChartPanel;

	private NhanVienDao nhanVienDao;
	private QuayDao quayDao;

	private JButton xoaTrangBtn;
	private JButton themBtn;
	private JButton xoaBtn;
	private JButton suaBtn;
	private JButton lamMoiBtn;
	private JButton hoanThanhBtn;

	private List<Quay> quays;
	
	public EmployeeManagerPage() {
		super();

		nhanVienDao = new NhanVienDao();
		quayDao = new QuayDao();

		getAllNhanVien();
		getAllQuay();
		getAllCaLamViec();
		getAllChucVu();
	}

	@Override
	public JPanel onCreateNestedContainerView() {
		Font commonFont = new Font("Arial", Font.PLAIN, 14);
		Font commonButtonFont = new Font("Arials", Font.BOLD, 16);
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.decode(ColorConsts.ForegroundColor));

		hoanThanhBtn = new JButton("Hoàn thành");
		hoanThanhBtn.setPreferredSize(new Dimension(130, 40));
		hoanThanhBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		hoanThanhBtn.setForeground(Color.decode(ColorConsts.BackgroundColor));
		hoanThanhBtn.setVisible(false);
		hoanThanhBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = nhanVienTable.getSelectedRow();

				int maNhanVien = Integer.parseInt(nhanVienModel.getValueAt(row, 0).toString());

				String tenNhanVien = txtTennv.getText().trim();
				String caLamViec = caLamViecComboBox.getSelectedItem().toString();
				String soDienThoai = txtSdt.getText().trim();
				
				Quay quay = quays.get(quayComboBox.getSelectedIndex());
				
				
				String chucVu = chucVuComboBox.getSelectedItem().toString();
				NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, null, caLamViec, soDienThoai, quay, chucVu);
				
				if (nhanVienDao.suaThongTinNhanVien(nhanVien)) {

					txtManv.setEditable(false);
					txtTennv.setEditable(false);
					txtNgayVaoLam.setEditable(false);
					txtSdt.setEditable(false);

					caLamViecComboBox.setEditable(false);
					quayComboBox.setEditable(false);
					chucVuComboBox.setEditable(false);

					hoanThanhBtn.setVisible(false);

					JOptionPane.showMessageDialog(null, "Cập nhập thông tin thành công");
					
					getAllNhanVien();
				}
			}
		});

		nhanVienModel = new DefaultTableModel(new String[] { "Mã nhân viên", "Tên nhân viên", "Ngày vào làm",
				"Ca làm việc", "Số điện thoại", "Quầy", "Chức vụ" }, 0);
		nhanVienTable = new JTable(nhanVienModel);

		JTableHeader headerTable = nhanVienTable.getTableHeader();
		headerTable.setBackground(Color.decode(ColorConsts.BackgroundColor));
		headerTable.setFont(new Font("Arial", Font.BOLD, 14));
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));

		nhanVienTable.setRowHeight(40);

		nhanVienTable.setShowVerticalLines(false);
		nhanVienTable.setFont(new Font("Arial", Font.PLAIN, 14));
		nhanVienTable.setRowHeight(40);
		nhanVienTable.setIntercellSpacing(new Dimension(0, 0));
		nhanVienTable.setGridColor(Color.decode("#696969"));
		nhanVienTable.setTableHeader(headerTable);
		nhanVienTable.addMouseListener(this);

		themBtn = new JButton("Thêm");
		themBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		themBtn.setFont(commonButtonFont);
		themBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateEmployeeFrm().setVisible(true);
			}
		});

		xoaBtn = new JButton("Xóa");
		xoaBtn.setPreferredSize(new Dimension(0, 50));
		xoaBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		xoaBtn.setFont(commonButtonFont);
		xoaBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = nhanVienTable.getSelectedRow();
				int maNhanVien = Integer.parseInt(nhanVienTable.getValueAt(row, 0).toString());

				String tenNhanVien = nhanVienModel.getValueAt(row, 1).toString();

				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Nhân viên " + tenNhanVien, "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					if (nhanVienDao.xoaNhanVienTheoMa(maNhanVien)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						nhanVienModel.removeRow(row);
					}
				}
			}
		});

		xoaTrangBtn = new JButton("Xóa trắng");
		xoaTrangBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		xoaTrangBtn.setFont(commonButtonFont);
		xoaTrangBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		xoaTrangBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtManv.setText("");
				txtTennv.setText("");
				txtNgayVaoLam.setText("");
				txtSdt.setText("");

				hoanThanhBtn.setVisible(false);

				txtManv.setEditable(false);
				txtTennv.setEditable(false);
				txtNgayVaoLam.setEditable(false);
				txtSdt.setEditable(false);

				caLamViecComboBox.setEditable(false);
				quayComboBox.setEditable(false);
				chucVuComboBox.setEditable(false);

				caLamViecComboBox.setSelectedIndex(0);
				quayComboBox.setSelectedIndex(0);
				chucVuComboBox.setSelectedIndex(0);
				incomeIPeriodChartPanel.setVisible(false);
			}
		});

		suaBtn = new JButton("Sửa");
		suaBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		suaBtn.setFont(commonButtonFont);
		suaBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = nhanVienTable.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Phải chọn dòng để sửa");
					return;
				}

				hoanThanhBtn.setVisible(true);

				txtManv.setEditable(false);
				txtTennv.setEditable(true);
				txtSdt.setEditable(true);

				caLamViecComboBox.setEditable(true);
				quayComboBox.setEditable(true);
				chucVuComboBox.setEditable(true);
			}
		});

		lamMoiBtn = new JButton("Làm mới");
		lamMoiBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		lamMoiBtn.setFont(commonButtonFont);
		lamMoiBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		lamMoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllNhanVien();
			}
		});

		JPanel footer = new JPanel(new GridLayout(1, 5));
		footer.add(themBtn);
		footer.add(xoaBtn);
		footer.add(xoaTrangBtn);
		footer.add(suaBtn);
		footer.add(lamMoiBtn);

		left.add(new JScrollPane(nhanVienTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

		left.add(footer, BorderLayout.SOUTH);

		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setMinimumSize(new Dimension(500, right.getPreferredSize().height));

		JLabel tieuDeLabel = new JLabel("Thông tin nhân viên");
		tieuDeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		tieuDeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

		Box maNVBox = Box.createHorizontalBox();
		JLabel lblManv = new JLabel("Mã nhân viên  ");

		lblManv.setFont(commonFont);
		txtManv = new JTextField("");
		txtManv.setEditable(false);
		maNVBox.add(lblManv);
		maNVBox.add(txtManv);

		Box sdtBox = Box.createHorizontalBox();
		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setPreferredSize(lblManv.getPreferredSize());
		lblSdt.setFont(commonFont);
		txtSdt = new JTextField("");
		txtSdt.setEditable(false);
		sdtBox.add(lblSdt);
		sdtBox.add(txtSdt);

		Box tenNVBox = Box.createHorizontalBox();
		JLabel lblTennv = new JLabel("Tên nhân viên");

		lblTennv.setFont(commonFont);
		lblTennv.setPreferredSize(lblManv.getPreferredSize());
		txtTennv = new JTextField("");
		txtTennv.setEditable(false);
		tenNVBox.add(lblTennv);
		tenNVBox.add(txtTennv);

		Box ngayVaoLamBox = Box.createHorizontalBox();
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm");

		lblNgayVaoLam.setFont(commonFont);
		lblNgayVaoLam.setPreferredSize(lblManv.getPreferredSize());
		txtNgayVaoLam = new JTextField("");
		txtNgayVaoLam.setEditable(false);
		ngayVaoLamBox.add(lblNgayVaoLam);
		ngayVaoLamBox.add(txtNgayVaoLam);

		Box caLamViecBox = Box.createHorizontalBox();
		JLabel lblCaLamViec = new JLabel("Ca làm việc");

		lblCaLamViec.setFont(commonFont);
		lblCaLamViec.setPreferredSize(lblManv.getPreferredSize());
		caLamViecComboBox = new JComboBox<String>();
		caLamViecComboBox.setEditable(false);
		caLamViecBox.add(lblCaLamViec);
		caLamViecBox.add(caLamViecComboBox);

		Box quayBox = Box.createHorizontalBox();
		JLabel lblQuay = new JLabel("Quầy");

		lblQuay.setFont(commonFont);
		lblQuay.setPreferredSize(lblManv.getPreferredSize());
		quayComboBox = new JComboBox<String>();
		quayComboBox.setEditable(false);
		quayBox.add(lblQuay);
		quayBox.add(quayComboBox);

		Box chucVuBox = Box.createHorizontalBox();
		JLabel lblChucVu = new JLabel("Chức vụ");

		lblChucVu.setFont(commonFont);
		lblChucVu.setPreferredSize(lblManv.getPreferredSize());
		chucVuComboBox = new JComboBox<String>();
		chucVuComboBox.setEditable(false);
		chucVuBox.add(lblChucVu);
		chucVuBox.add(chucVuComboBox);

		JLabel doanhThuTheoThangLb = new JLabel("Doanh thu theo tháng");
		doanhThuTheoThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		doanhThuTheoThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));
		doanhThuTheoThangLb.setPreferredSize(new Dimension(500, 100));

		incomeIPeriodChart = ChartFactory.createBarChart("Doanh Thu chi nhánh trong 1 tháng", "Ngày trong tuần",
				"Doanh thu", getIncomeInPeriodDateset(), PlotOrientation.VERTICAL, false, false, false);

		incomeIPeriodChart.setBorderVisible(false);
		incomeIPeriodChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		incomeIPeriodChart.setBackgroundPaint(Color.white);

		incomeIPeriodChartPanel = new ChartPanel(incomeIPeriodChart);
		incomeIPeriodChartPanel.setPreferredSize(new Dimension(500, 500));
		incomeIPeriodChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		incomeIPeriodChartPanel.setVisible(false);

		right.add(tieuDeLabel);
		right.add(maNVBox);
		right.add(Box.createVerticalStrut(20));
		right.add(tenNVBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ngayVaoLamBox);
		right.add(Box.createVerticalStrut(20));
		right.add(caLamViecBox);
		right.add(Box.createVerticalStrut(20));
		right.add(sdtBox);
		right.add(Box.createVerticalStrut(20));
		right.add(quayBox);
		right.add(Box.createVerticalStrut(20));
		right.add(chucVuBox);
		right.add(Box.createVerticalStrut(20));
		right.add(hoanThanhBtn);

		right.add(doanhThuTheoThangLb);
		right.add(incomeIPeriodChartPanel);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(left, BorderLayout.CENTER);
		panel.add(new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
				BorderLayout.EAST);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		JPanel jp_test = new JPanel();
		return new Header().addTitle("Quản lý nhân viên").addInsidePanel(jp_test).createView();
	}

	private CategoryDataset getIncomeInPeriodDateset() {
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

	private void getAllNhanVien() {
		while (nhanVienModel.getRowCount() > 0) {
			nhanVienModel.removeRow(0);
		}

		List<NhanVien> list = nhanVienDao.getAllNhanVien();
		for (NhanVien nv : list) {
			nhanVienModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getNgayVaoLam(),
					nv.getCaLamViec(), nv.getSoDienThoai(), nv.getQuay().getTenQuay(), nv.getChucVu() });
		}
	}

	private void getAllQuay() {
		quays = quayDao.getAllData();
		
		for (Quay quay : quays) {
			quayComboBox.addItem(quay.getTenQuay());
		}
	}

	private void getAllCaLamViec() {
		String listCa[] = "Sáng;Trưa;Chiều;Tối".split(";");
		for (String ca : listCa) {
			caLamViecComboBox.addItem(ca);
		}

	}

	private void getAllChucVu() {
		String listChucvu[] = "Quản lý;Nhân viên".split(";");
		for (String chucVu : listChucvu) {
			chucVuComboBox.addItem(chucVu);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		hoanThanhBtn.setVisible(false);

		txtManv.setEditable(false);
		txtTennv.setEditable(false);
		txtNgayVaoLam.setEditable(false);
		txtSdt.setEditable(false);

		caLamViecComboBox.setEditable(false);
		quayComboBox.setEditable(false);
		chucVuComboBox.setEditable(false);

		caLamViecComboBox.setSelectedIndex(0);
		quayComboBox.setSelectedIndex(0);
		chucVuComboBox.setSelectedIndex(0);

		int row = nhanVienTable.getSelectedRow();

		txtManv.setText(nhanVienModel.getValueAt(row, 0).toString());
		txtTennv.setText(nhanVienModel.getValueAt(row, 1).toString());
		txtNgayVaoLam.setText(nhanVienModel.getValueAt(row, 2).toString());
		caLamViecComboBox.setSelectedItem(nhanVienModel.getValueAt(row, 3).toString());
		txtSdt.setText(nhanVienModel.getValueAt(row, 4).toString());
		quayComboBox.setSelectedItem(nhanVienModel.getValueAt(row, 5).toString());
		chucVuComboBox.setSelectedItem(nhanVienModel.getValueAt(row, 6).toString());

		System.out.println(nhanVienModel.getValueAt(row, 6).toString());
		incomeIPeriodChartPanel.setVisible(true);
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