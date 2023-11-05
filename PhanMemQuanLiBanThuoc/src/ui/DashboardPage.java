package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import components.Header;
import components.TopSaleProductView;

public class DashboardPage extends BasePage {

	private DatePicker datePickerFrom;
	private DatePicker datePickerTo;

	private JLabel incomeLabel;
	private JLabel orderCountLabel;
	private JLabel productCountLabel;
	
	private JFreeChart incomeInWeekChart;
	private JFreeChart categoryTopSaleChart;
	private JFreeChart premisSaleChart;
	private JFreeChart paymentMethodChart;
	
	private JList topSaleProductList;
	private DefaultListModel<Object> topSaleProductModel;
	
	public DashboardPage() {
		super();
	}

	@Override
	public JPanel onCreateNestedContainerView() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.decode(ColorConsts.ForegroundColor));

		JPanel boxGroup = new JPanel(new GridLayout(1, 4, 20, 0));
		boxGroup.setBorder(new EmptyBorder(15, 15, 15, 15));
		boxGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		Box box2 = Box.createVerticalBox();
		JLabel box2Label = new JLabel("Doanh thu");
		box2Label.setFont(new Font("Arials", Font.PLAIN, 20));
		incomeLabel = new JLabel("1,520,000đ");
		incomeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		box2.setOpaque(true);
		box2.setBorder(new EmptyBorder(15, 15, 15, 15));
		box2.setBackground(Color.BLACK);
		box2.add(box2Label);
		box2.setBackground(new Color(22, 120, 254, 80));
		box2.add(incomeLabel);

		Box box1 = Box.createVerticalBox();
		JLabel box1Label = new JLabel("Số hóa đơn");
		box1Label.setFont(new Font("Arials", Font.PLAIN, 20));
		orderCountLabel = new JLabel("25");
		orderCountLabel.setFont(new Font("Arials", Font.BOLD, 30));
		box1.setBorder(new EmptyBorder(15, 15, 15, 15));
		box1.setOpaque(true);
		box1.setBackground(new Color(22, 120, 254, 80));
		box1.add(box1Label);
		box1.add(orderCountLabel);

		Box box3 = Box.createVerticalBox();
		JLabel box3Label = new JLabel("Số thuốc");
		box3Label.setFont(new Font("Arials", Font.PLAIN, 20));
		productCountLabel = new JLabel("25");
		productCountLabel.setFont(new Font("Arials", Font.BOLD, 30));
		box3.setOpaque(true);
		box3.setBackground(new Color(22, 120, 254, 80));
		box3.setBorder(new EmptyBorder(15, 15, 15, 15));
		box3.add(box3Label);
		box3.add(productCountLabel);

		boxGroup.add(box2);
		boxGroup.add(box1);
		boxGroup.add(box3);

		
		JPanel chartGridGroup = new JPanel(new GridLayout(0, 3, 10, 10));
		chartGridGroup.setBorder(new EmptyBorder(15, 0, 15, 0));
		
		
		incomeInWeekChart = ChartFactory.createBarChart(
				"Doanh Thu 7 Ngày Gần Nhất", 
				"Ngày trong tuần",
				"Doanh thu", 
				getIncomeInWeekDateset(), 
				PlotOrientation.VERTICAL,
				false, 
				false,
				false);
		
		incomeInWeekChart.setBorderVisible(false);
		incomeInWeekChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		incomeInWeekChart.setBackgroundPaint(Color.white);

		CategoryPlot cplot = (CategoryPlot)incomeInWeekChart.getPlot();
	    BarRenderer r = (BarRenderer)incomeInWeekChart.getCategoryPlot().getRenderer();
	    r.setSeriesPaint(0, new Color(22, 120, 254));
		
		ChartPanel incomeInWeekChartPanel =new ChartPanel(incomeInWeekChart);
		incomeInWeekChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		//
		
		categoryTopSaleChart = ChartFactory.createPieChart(
				"Top 5 danh mục thuốc bán chạy nhất", getTopSaleInCategory(), true, true, true);
		categoryTopSaleChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		categoryTopSaleChart.setBackgroundPaint(Color.white);
		
		ChartPanel categoryChartPanel = new ChartPanel(categoryTopSaleChart);
		categoryChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		
		
		// 
		
		JPanel topSaleProductPanel = new JPanel(new BorderLayout());
		topSaleProductPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		JLabel topSaleProductLabel = new JLabel("Top 10 sản phẩm bán chạy nhất");
		topSaleProductLabel.setFont(new Font("Arials", Font.BOLD, 23));
		topSaleProductLabel.setAlignmentX(CENTER_ALIGNMENT);
		topSaleProductLabel.setBackground(Color.decode(ColorConsts.ForegroundColor));
		topSaleProductLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
		
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
		topSaleProductPanel.add(topSaleProductLabel, BorderLayout.NORTH);
		topSaleProductPanel.add(topSaleProductList, BorderLayout.CENTER);
		
		//
		
		
		premisSaleChart = ChartFactory.createBarChart(
				"Top quầy thuốc bán chạy nhất", 
				"Quầy thuốc",
				"Doanh thu", 
				getTopSaleInPremisDateset(), 
				PlotOrientation.HORIZONTAL,
				false, 
				false,
				false);
		
		premisSaleChart.setBorderVisible(false);
		premisSaleChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		premisSaleChart.setBackgroundPaint(Color.white);

		CategoryPlot cplotPremis = (CategoryPlot)premisSaleChart.getPlot();
	    BarRenderer rPremis = (BarRenderer)premisSaleChart.getCategoryPlot().getRenderer();
	    rPremis.setSeriesPaint(0, new Color(22, 120, 254));
		
		ChartPanel premisSaleChartPanel =new ChartPanel(premisSaleChart);
		premisSaleChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		///
		
		paymentMethodChart = ChartFactory.createPieChart(
				"Phương thức thanh toán", getPaymentMethodPercentDataset(), true, true, true);
		paymentMethodChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		paymentMethodChart.setBackgroundPaint(Color.white);
		
		ChartPanel paymentMethodChartPanel = new ChartPanel(paymentMethodChart);
		paymentMethodChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		
		chartGridGroup.setBackground(Color.decode(ColorConsts.BackgroundColor));
		chartGridGroup.add(incomeInWeekChartPanel);
		chartGridGroup.add(categoryChartPanel);
		chartGridGroup.add(topSaleProductPanel);
		chartGridGroup.add(premisSaleChartPanel);
		chartGridGroup.add(paymentMethodChartPanel);
		
		
		

		
		panel.add(boxGroup, BorderLayout.NORTH);
		panel.add(chartGridGroup, BorderLayout.CENTER);
		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {

		JPanel insidePane = new JPanel(new BorderLayout());
		insidePane.setBackground(Color.decode(ColorConsts.ForegroundColor));
		insidePane.setLayout(new BoxLayout(insidePane, BoxLayout.Y_AXIS));

		JPanel datePickerGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
		datePickerGroup.setBorder(new EmptyBorder(15, 10, 10, 10));

		datePickerFrom = new DatePicker();
		datePickerFrom.setDate(LocalDate.now().minusDays(7));
		datePickerTo = new DatePicker();
		datePickerTo.setDateToToday();

		datePickerGroup.add(datePickerFrom);
		datePickerGroup.add(datePickerTo);
		datePickerGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		insidePane.add(datePickerGroup);

		return new Header().addTitle("Trang chủ").addInsidePanel(insidePane).createView();
	}
	
	
	private CategoryDataset getIncomeInWeekDateset() {
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
	
	private PieDataset getTopSaleInCategory() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		
		dataset.setValue("Thuốc kháng sinh", 60.0);
		dataset.setValue("Thuốc giảm đau, hạ sốt", 20.0);
		dataset.setValue("Thuốc kháng viêm", 10.0);
		dataset.setValue("Thuốc ho và long đờm", 5.0);
		dataset.setValue("Thuốc trị tiêu chảy", 5.0);
		
		
		return dataset;
	}

	private CategoryDataset getTopSaleInPremisDateset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(200000, "Doanh thu", "312 Tân Bình");
		dataset.addValue(300000, "Doanh thu", "1 Nguyễn Thị Minh Khai");
		dataset.addValue(1000000, "Doanh thu", "2 Nhà H IUH");
		
		dataset.addValue(700000, "Doanh thu", "405 Điện Biên Phủ");
		dataset.addValue(200000, "Doanh thu", "14 Nguyễn Văn Bảo");
		
		return dataset;
	} 
	
	private PieDataset getPaymentMethodPercentDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("Tiền mặt", 60.0);
		dataset.setValue("Chuyển khoản ngân hàng", 25.0);
		dataset.setValue("Chuyển khoản ví Momo", 15.0);
		
		return dataset;
	} 
}