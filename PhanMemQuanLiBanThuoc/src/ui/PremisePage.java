package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
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
import dao.QuayDao;
import entity.Quay;

public class PremisePage extends BasePage implements MouseListener {

	private JTextField maQuayTxt;
	private JTextField tenQuayTxt;
	private JTextField diaChiTxt;
	private JTextField phuongTxt;
	private JTextField thanhPhoTxt;
	private JTextField tinhTxt;
	
	private JButton timBtn;
	private JButton themBtn;

	private JButton xoaTrangBtn;
	private JRadioButton phaiRadioBtn;
	private JButton xoaBtn;
	private JButton luuBtn;

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
		Font commonFont = new Font("Arial", Font.PLAIN, 14);
		
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.decode(ColorConsts.ForegroundColor));

		
		
		
		quayModel = new DefaultTableModel(new String[] { "Mã quầy", "Tên quầy", "Địa chỉ", "Phường", "Thành phố", "Tỉnh" }, 0);
		quayTable =new JTable(quayModel);
		
		
		JTableHeader headerTable = quayTable.getTableHeader();
		headerTable.setBackground(Color.decode(ColorConsts.PrimaryColor));
		headerTable.setFont(new Font("Arial", Font.PLAIN, 14));
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));
		headerTable.setForeground(Color.WHITE);
		
		quayTable.setRowHeight(40);
		
		quayTable.setShowVerticalLines(false);
		quayTable.setFont(new Font("Arial", Font.PLAIN, 14));
		quayTable.setRowHeight(40);
		quayTable.setGridColor(Color.decode("#696969"));
		quayTable.setTableHeader(headerTable);
		quayTable.addMouseListener(this);
		
		left.add(new JScrollPane(quayTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		
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
		JLabel tbLb = new JLabel("Thành phố");
		
		tbLb.setFont(commonFont);
		thanhPhoTxt = new JTextField("");
		thanhPhoTxt.setEditable(false);
		tpBox.add(tbLb);
		tpBox.add(thanhPhoTxt);
		
		
		Box maQuayBox = Box.createHorizontalBox();
		JLabel maQuayLb = new JLabel("Mã quầy");
		
		maQuayLb.setFont(commonFont);
		maQuayLb.setPreferredSize(tbLb.getPreferredSize());
		maQuayTxt = new JTextField("");
		maQuayTxt.setEditable(false);
		maQuayBox.add(maQuayLb);
		maQuayBox.add(maQuayTxt);
		
		
		
		Box tenQuayBox = Box.createHorizontalBox();
		JLabel tenQuayLb = new JLabel("Mã quầy");
		
		tenQuayLb.setFont(commonFont);
		tenQuayLb.setPreferredSize(tbLb.getPreferredSize());
		tenQuayTxt = new JTextField("");
		tenQuayTxt.setEditable(false);
		tenQuayBox.add(maQuayLb);
		tenQuayBox.add(maQuayTxt);
		
		
		Box diaChiBox = Box.createHorizontalBox();
		JLabel diaChiLb = new JLabel("Địa chỉ");
		
		diaChiLb.setFont(commonFont);
		diaChiLb.setPreferredSize(tbLb.getPreferredSize());
		diaChiTxt = new JTextField("");
		diaChiTxt.setEditable(false);
		diaChiBox.add(diaChiLb);
		diaChiBox.add(diaChiTxt);
		
		
		Box phuongBox = Box.createHorizontalBox();
		JLabel phuongLb = new JLabel("Phường");
		
		phuongLb.setFont(commonFont);
		phuongLb.setPreferredSize(tbLb.getPreferredSize());
		phuongTxt = new JTextField("");
		phuongTxt.setEditable(false);
		phuongBox.add(phuongLb);
		phuongBox.add(phuongTxt);
		
		
		
		Box tinhBox = Box.createHorizontalBox();
		JLabel tinhLb = new JLabel("Tỉnh");
		
		tinhLb.setFont(commonFont);
		tinhLb.setPreferredSize(tbLb.getPreferredSize());
		tinhTxt = new JTextField("Khánh Hòa");
		tinhTxt.setEditable(false);
		tinhBox.add(tinhLb);
		tinhBox.add(tinhTxt);
		
		
		
		JLabel doanhThuTheoThangLb = new JLabel("Doanh thu theo tháng");
		doanhThuTheoThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		doanhThuTheoThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		
		incomeIPeriodChart = ChartFactory.createBarChart(
				"Doanh Thu chi nhánh trong 1 tháng", 
				"Ngày trong tuần",
				"Doanh thu", 
				getIncomeInPeriodDateset(), 
				PlotOrientation.VERTICAL,
				false, 
				false,
				false);
		
		incomeIPeriodChart.setBorderVisible(false);
		incomeIPeriodChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		incomeIPeriodChart.setBackgroundPaint(Color.white);
		
		incomeIPeriodChartPanel =new ChartPanel(incomeIPeriodChart);
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
		topSaleProductList = new JList<Object>(topSaleProductModel);
		topSaleProductList.setCellRenderer(new TopSaleProductView());
		topSaleProductPanel.add(topSanPhamChayThangLb, BorderLayout.NORTH);
		topSaleProductPanel.add(topSaleProductList, BorderLayout.CENTER);
		//topSaleProductPanel.setVisible(false);
		
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
		right.add(doanhThuTheoThangLb); 
		right.add(incomeIPeriodChartPanel);
		right.add(topSanPhamChayThangLb); 
		right.add(topSaleProductPanel); 
		
		
		
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(left, BorderLayout.CENTER);
		panel.add(new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		JPanel jp_test = new JPanel();
		return new Header()
				.addTitle("Đơn hàng")
				.addInsidePanel(jp_test)
				.createView();
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
	

	private void getAllQuay() {
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
		
		
		maQuayTxt.setText(quayModel.getValueAt(row, 0).toString());
		tenQuayTxt.setText(quayModel.getValueAt(row, 1).toString());
		diaChiTxt.setText(quayModel.getValueAt(row, 2).toString());
		phuongTxt.setText(quayModel.getValueAt(row, 3).toString());
		thanhPhoTxt.setText(quayModel.getValueAt(row, 4).toString());
		tinhTxt.setText(quayModel.getValueAt(row, 5).toString());
		
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
