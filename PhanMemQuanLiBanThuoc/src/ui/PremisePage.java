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
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import components.TaiKhoanDangNhap;
import components.TopSaleProductView;
import dao.QuayDao;
import entity.KhachHang;
import entity.Quay;

public class PremisePage extends BasePage implements MouseListener {

	private JTextField maQuayTxt;
	private JTextField tenQuayTxt;
	private JTextField diaChiTxt;
	private JTextField phuongTxt;
	private JTextField thanhPhoTxt;
	private JTextField tinhTxt;

	private JButton timBtn;

	private JButton xoaTrangBtn;
	private JButton themBtn;
	private JButton xoaBtn;
	private JButton suaBtn;
	private JButton lamMoiBtn;
	private JButton hoanThanhBtn;

	private DefaultTableModel quayModel;
	private JTable quayTable;

	private JList topSaleProductList;
	private DefaultListModel<Object> topSaleProductModel;

	private JFreeChart incomeIPeriodChart;
	private ChartPanel incomeIPeriodChartPanel;
	private QuayDao quayDao;

	public PremisePage() {
		super();

		quayDao = new QuayDao();

		getAllQuay();
	}

	@Override
	public JPanel onCreateNestedContainerView() {
		Font commonFont = new Font("Arials", Font.PLAIN, 14);
		Font commonButtonFont = new Font("Arials", Font.BOLD, 16);

		hoanThanhBtn = new JButton("Hoàn thành");
		hoanThanhBtn.setPreferredSize(new Dimension(130, 40));
		hoanThanhBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		hoanThanhBtn.setForeground(Color.decode(ColorConsts.BackgroundColor));
		hoanThanhBtn.setVisible(false);
		hoanThanhBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = quayTable.getSelectedRow();

				int maQuay = Integer.parseInt(quayTable.getValueAt(row, 0).toString());
				
				String tenQuay = tenQuayTxt.getText().trim();
				String diaChi = diaChiTxt.getText().trim();
				String phuong = phuongTxt.getText().trim();
				String thanhPho = thanhPhoTxt.getText().trim();
				String tinh = tinhTxt.getText().trim();
		
				Quay quay = new Quay(maQuay, tenQuay, diaChi, phuong, thanhPho, tinh);
				
				if (quayDao.suaThongTinQuay(quay)) {
					getAllQuay();
					
					maQuayTxt.setEditable(false);
					tenQuayTxt.setEditable(false);
					diaChiTxt.setEditable(false);
					phuongTxt.setEditable(false);
					thanhPhoTxt.setEditable(false);
					tinhTxt.setEditable(false);
					
					hoanThanhBtn.setVisible(false);
					
					JOptionPane.showMessageDialog(null, "Cập nhập thông tin thành công");
				}
			}
		});

		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.decode(ColorConsts.ForegroundColor));

		quayModel = new DefaultTableModel(
				new String[] { "Mã quầy", "Tên quầy", "Địa chỉ", "Phường", "Thành phố", "Tỉnh" }, 0);
		quayTable = new JTable(quayModel);

		JTableHeader headerTable = quayTable.getTableHeader();
		headerTable.setBackground(Color.decode(ColorConsts.BackgroundColor));
		headerTable.setFont(new Font("Arial", Font.BOLD, 14));
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		
		setCellEditable();

		quayTable.setRowHeight(40);

		quayTable.setShowVerticalLines(false);
		quayTable.setFont(new Font("Arial", Font.PLAIN, 14));
		quayTable.setRowHeight(40);
		quayTable.setIntercellSpacing(new Dimension(0, 0));
		quayTable.setGridColor(Color.decode("#696969"));
		quayTable.setTableHeader(headerTable);
		quayTable.addMouseListener(this);

		themBtn = new JButton("Thêm");
		themBtn.setIcon(new ImageIcon("icon\\ic_addLight.png"));
		themBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		themBtn.setFont(commonButtonFont);
		themBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!TaiKhoanDangNhap.getNV().getChucVu().equals("Quản lý")) {
					JOptionPane.showMessageDialog(null, "Chỉ quản lý mới có quyền này!");
					return;
				}
				new CreatePremisesFrm().setVisible(true);
			}
		});

		xoaBtn = new JButton("Xóa");
		xoaBtn.setIcon(new ImageIcon("icon\\ic_clearLight.png"));
		xoaBtn.setPreferredSize(new Dimension(0, 50));
		xoaBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		xoaBtn.setFont(commonButtonFont);
		xoaBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!TaiKhoanDangNhap.getNV().getChucVu().equals("Quản lý")) {
					JOptionPane.showMessageDialog(null, "Chỉ quản lý mới có quyền này!");
					return;
				}
				int row = quayTable.getSelectedRow();
				int maQuay = Integer.parseInt(quayModel.getValueAt(row, 0).toString());
				String tenQuay = quayModel.getValueAt(row, 1).toString();

				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Quầy " + tenQuay, "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					if (quayDao.deleteQuay(maQuay)) {
						quayModel.removeRow(row);
					}
				}
			}
		});

		xoaTrangBtn = new JButton("Xóa trắng");
		xoaTrangBtn.setIcon(new ImageIcon("icon\\ic_clearInfoLight.png"));
		xoaTrangBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		xoaTrangBtn.setFont(commonButtonFont);
		xoaTrangBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		xoaTrangBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maQuayTxt.setEditable(false);
				tenQuayTxt.setEditable(false);
				diaChiTxt.setEditable(false);
				phuongTxt.setEditable(false);
				thanhPhoTxt.setEditable(false);
				tinhTxt.setEditable(false);
				
				maQuayTxt.setText("");
				tenQuayTxt.setText("");
				diaChiTxt.setText("");
				phuongTxt.setText("");
				thanhPhoTxt.setText("");
				tinhTxt.setText("");
				
				incomeIPeriodChartPanel.setVisible(false);
				topSaleProductList.setVisible(false);
			}
		});

		suaBtn = new JButton("Sửa");
		suaBtn.setIcon(new ImageIcon("icon\\ic_writeLight.png"));
		suaBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		suaBtn.setFont(commonButtonFont);
		suaBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!TaiKhoanDangNhap.getNV().getChucVu().equals("Quản lý")) {
					JOptionPane.showMessageDialog(null, "Chỉ quản lý mới có quyền này!");
					return;
				}
				int row = quayTable.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Phải chọn dòng để sửa");
					return;
				}

				maQuayTxt.setEditable(false);
				
				tenQuayTxt.setEditable(true);
				diaChiTxt.setEditable(true);
				phuongTxt.setEditable(true);
				thanhPhoTxt.setEditable(true);
				tinhTxt.setEditable(true);

				hoanThanhBtn.setVisible(true);
			}
		});

		lamMoiBtn = new JButton("Làm mới");
		lamMoiBtn.setIcon(new ImageIcon("icon\\ic_refreshLight.png"));
		lamMoiBtn.setBackground(Color.decode(ColorConsts.PrimaryColor));
		lamMoiBtn.setFont(commonButtonFont);
		lamMoiBtn.setForeground(Color.decode(ColorConsts.ForegroundColor));
		lamMoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllQuay();
			}
		});

		JPanel footer = new JPanel(new GridLayout(1, 5));
		footer.add(themBtn);
		footer.add(xoaBtn);
		footer.add(xoaTrangBtn);
		footer.add(suaBtn);
		footer.add(lamMoiBtn);

		left.add(new JScrollPane(quayTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		left.add(footer, BorderLayout.SOUTH);

		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setMinimumSize(new Dimension(500, right.getPreferredSize().height));

		/**
		 * Tiêu đề
		 */
		JLabel tieuDeLabel = new JLabel("Quầy");
		tieuDeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		tieuDeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

		Box tpBox = Box.createHorizontalBox();
		JLabel tpLb = new JLabel("Thành phố");

		tpLb.setFont(commonFont);
		thanhPhoTxt = new JTextField("");
		thanhPhoTxt.setEditable(false);
		tpBox.add(tpLb);
		tpBox.add(thanhPhoTxt);

		Box maQuayBox = Box.createHorizontalBox();
		JLabel maQuayLb = new JLabel("Mã quầy");
		maQuayLb.setFont(commonFont);
		maQuayLb.setPreferredSize(tpLb.getPreferredSize());
		maQuayTxt = new JTextField("");
		maQuayTxt.setEditable(false);
		maQuayBox.add(maQuayLb);
		maQuayBox.add(maQuayTxt);

		Box tenQuayBox = Box.createHorizontalBox();
		JLabel tenQuayLb = new JLabel("Tên quầy");
		tenQuayLb.setFont(commonFont);
		tenQuayLb.setPreferredSize(tpLb.getPreferredSize());
		tenQuayTxt = new JTextField("");
		tenQuayTxt.setEditable(false);
		tenQuayBox.add(tenQuayLb);
		tenQuayBox.add(tenQuayTxt);

		Box diaChiBox = Box.createHorizontalBox();
		JLabel diaChiLb = new JLabel("Địa chỉ");
		diaChiLb.setFont(commonFont);
		diaChiLb.setPreferredSize(tpLb.getPreferredSize());
		diaChiTxt = new JTextField("");
		diaChiTxt.setEditable(false);
		diaChiBox.add(diaChiLb);
		diaChiBox.add(diaChiTxt);

		Box phuongBox = Box.createHorizontalBox();
		JLabel phuongLb = new JLabel("Phường");
		phuongLb.setFont(commonFont);
		phuongLb.setPreferredSize(tpLb.getPreferredSize());
		phuongTxt = new JTextField("");
		phuongTxt.setEditable(false);
		phuongBox.add(phuongLb);
		phuongBox.add(phuongTxt);

		Box tinhBox = Box.createHorizontalBox();
		JLabel tinhLb = new JLabel("Tỉnh");
		tinhLb.setFont(commonFont);
		tinhLb.setPreferredSize(tpLb.getPreferredSize());
		tinhTxt = new JTextField("");
		tinhTxt.setEditable(false);
		tinhBox.add(tinhLb);
		tinhBox.add(tinhTxt);

		JLabel doanhThuTheoThangLb = new JLabel("Doanh thu theo tháng");
		doanhThuTheoThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		doanhThuTheoThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));

		incomeIPeriodChart = ChartFactory.createBarChart("Doanh Thu chi nhánh trong 1 tháng", "Ngày trong tuần",
				"Doanh thu", getIncomeInPeriodDateset(), PlotOrientation.VERTICAL, false, false, false);

		incomeIPeriodChart.setBorderVisible(false);
		incomeIPeriodChart.setBackgroundPaint(Color.white);

		incomeIPeriodChartPanel = new ChartPanel(incomeIPeriodChart);
		incomeIPeriodChartPanel.setPreferredSize(new Dimension(500, 500));
		incomeIPeriodChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		incomeIPeriodChartPanel.setVisible(false);

		JLabel topSanPhamChayThangLb = new JLabel("Sản phẩm bán chạy tại chi nhánh");
		topSanPhamChayThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		topSanPhamChayThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));

		JPanel topSaleProductPanel = new JPanel(new BorderLayout());
		topSaleProductPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));
		topSaleProductPanel.setPreferredSize(new Dimension(500, 300));

		topSaleProductModel = new DefaultListModel<Object>();

		topSaleProductList = new JList<Object>(topSaleProductModel);
		topSaleProductList.setCellRenderer(new TopSaleProductView());
		topSaleProductPanel.add(topSanPhamChayThangLb, BorderLayout.NORTH);
		topSaleProductPanel.add(topSaleProductList, BorderLayout.CENTER);
		// topSaleProductPanel.setVisible(false);

		right.add(tieuDeLabel);
		right.add(maQuayBox);
		right.add(Box.createVerticalStrut(20));
		right.add(tenQuayBox);
		right.add(Box.createVerticalStrut(20));
		right.add(diaChiBox);
		right.add(Box.createVerticalStrut(20));
		right.add(phuongBox);
		right.add(Box.createVerticalStrut(20));
		right.add(tpBox);
		right.add(Box.createVerticalStrut(20));
		right.add(tinhBox);
		right.add(Box.createVerticalStrut(20));
		right.add(hoanThanhBtn);
		right.add(doanhThuTheoThangLb);
		right.add(incomeIPeriodChartPanel);
		right.add(topSanPhamChayThangLb);
		right.add(topSaleProductPanel);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(left, BorderLayout.CENTER);
		panel.add(new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
				BorderLayout.EAST);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header().addTitle("Quầy").createView();
	}

	private CategoryDataset getIncomeInPeriodDateset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < 31; i++) {
			dataset.addValue(1000000, "Doanh thu", String.valueOf(i + 1));
		}

		return dataset;
	}

	private void getAllQuay() {
		while (quayModel.getRowCount() > 0) {
			quayModel.removeRow(0);
		}

		for (Quay element : quayDao.getAllData()) {
			quayModel.addRow(new String[] { 
					String.valueOf(element.getMaQuay()), 
					element.getTenQuay(),
					element.getDiaChi(),
					element.getPhuong(), 
					element.getThanhPho(), 
					element.getTinh() 
					});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = quayTable.getSelectedRow();

		maQuayTxt.setEditable(false);
		tenQuayTxt.setEditable(false);
		diaChiTxt.setEditable(false);
		phuongTxt.setEditable(false);
		thanhPhoTxt.setEditable(false);
		tinhTxt.setEditable(false);
		
		maQuayTxt.setText(quayModel.getValueAt(row, 0).toString());
		tenQuayTxt.setText(quayModel.getValueAt(row, 1).toString());
		diaChiTxt.setText(quayModel.getValueAt(row, 2).toString());
		phuongTxt.setText(quayModel.getValueAt(row, 3).toString());
		thanhPhoTxt.setText(quayModel.getValueAt(row, 4).toString());
		tinhTxt.setText(quayModel.getValueAt(row, 5).toString());

		incomeIPeriodChartPanel.setVisible(true);

		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
		topSaleProductModel.addElement("Viên uống hỗ trợ giấc ngủ Blackmores Tranquil Night");
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
	public void setCellEditable() {
		for (int i = 0; i < quayTable.getColumnCount(); i++) {
				quayTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
					@Override
					public boolean isCellEditable(EventObject e) {
						// Trả về false để ngăn chặn chỉnh sửa trực tiếp
						return false;
					}
				});
			}
	}
}
