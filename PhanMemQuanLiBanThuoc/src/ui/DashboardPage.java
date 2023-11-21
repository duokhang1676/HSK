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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import org.jfree.ui.RectangleInsets;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import components.ColorConsts;
import components.Header;
import components.TopSaleProductView;
import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import dao.QuayDao;
import dao.ThuocDao;
import model.IncomeInPeriod;
import model.PercentagePaymentMethod;
import model.TopSaleInCategory;
import model.TopSaleInPremises;
import model.TopThuocBanChay;

public class DashboardPage extends BasePage implements DateChangeListener {

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
	private DefaultListModel<TopThuocBanChay> topSaleProductModel;

	private QuayDao quayDao;
	private HoaDonDao hoaDonDao;
	private ThuocDao thuocDao;
	private ChiTietHoaDonDao chiTietHoaDonDao;

	private DefaultCategoryDataset topSaleInPremisDateset;
	private DefaultPieDataset paymentMethodDataset;
	private DefaultPieDataset topSaleInCategoryDataset;
	private DefaultCategoryDataset incomeDataSet;
	
	private JButton refreshButton;
	private JButton filterByDayButton;

	public DashboardPage() {
		super();

		quayDao = new QuayDao();
		hoaDonDao = new HoaDonDao();
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		thuocDao = new ThuocDao();
		
		fetchAllData();
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

		incomeDataSet = new DefaultCategoryDataset();
		incomeInWeekChart = ChartFactory.createBarChart("Doanh Thu theo ngày", "Ngày trong tuần", "Doanh thu",
				incomeDataSet, PlotOrientation.VERTICAL, false, false, false);

		incomeInWeekChart.setBorderVisible(false);
		incomeInWeekChart.setBackgroundPaint(Color.white);

		CategoryPlot cplot = (CategoryPlot) incomeInWeekChart.getPlot();
		BarRenderer r = (BarRenderer) incomeInWeekChart.getCategoryPlot().getRenderer();
		r.setSeriesPaint(0, new Color(22, 120, 254));

		ChartPanel incomeInWeekChartPanel = new ChartPanel(incomeInWeekChart);
		incomeInWeekChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		incomeInWeekChartPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));
		//

		topSaleInCategoryDataset = new DefaultPieDataset();

		categoryTopSaleChart = ChartFactory.createPieChart("Top 5 danh mục thuốc bán chạy nhất",
				topSaleInCategoryDataset, true, true, true);
		// categoryTopSaleChart.setPadding(new RectangleInsets(15, 15, 15, 15));
		categoryTopSaleChart.setBackgroundPaint(Color.white);

		ChartPanel categoryChartPanel = new ChartPanel(categoryTopSaleChart);
		categoryChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		categoryChartPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));

		JPanel topSaleProductPanel = new JPanel(new BorderLayout());
		topSaleProductPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));

		JLabel topSaleProductLabel = new JLabel("Top 10 sản phẩm bán chạy nhất");
		topSaleProductLabel.setFont(new Font("Arials", Font.BOLD, 23));
		topSaleProductLabel.setAlignmentX(CENTER_ALIGNMENT);
		topSaleProductLabel.setBackground(Color.decode(ColorConsts.ForegroundColor));
		topSaleProductLabel.setBorder(new EmptyBorder(15, 15, 15, 15));

		topSaleProductModel = new DefaultListModel<TopThuocBanChay>();
		topSaleProductList = new JList<TopThuocBanChay>(topSaleProductModel);
		topSaleProductList.setCellRenderer(new TopSaleProductView());
		topSaleProductPanel.add(topSaleProductLabel, BorderLayout.NORTH);
		topSaleProductPanel.add(new JScrollPane(topSaleProductList), BorderLayout.CENTER);

		//

		topSaleInPremisDateset = new DefaultCategoryDataset();
		premisSaleChart = ChartFactory.createBarChart("Top quầy thuốc bán chạy nhất", "Quầy thuốc", "Doanh thu",
				topSaleInPremisDateset, PlotOrientation.HORIZONTAL, false, false, false);

		premisSaleChart.setBorderVisible(false);
		premisSaleChart.setBackgroundPaint(Color.white);

		CategoryPlot cplotPremis = (CategoryPlot) premisSaleChart.getPlot();
		BarRenderer rPremis = (BarRenderer) premisSaleChart.getCategoryPlot().getRenderer();
		rPremis.setSeriesPaint(0, new Color(22, 120, 254));

		ChartPanel premisSaleChartPanel = new ChartPanel(premisSaleChart);
		premisSaleChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		premisSaleChartPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));

		paymentMethodDataset = new DefaultPieDataset();
		paymentMethodChart = ChartFactory.createPieChart("Phương thức thanh toán", paymentMethodDataset, true, true,
				true);

		paymentMethodChart.setBackgroundPaint(Color.white);

		ChartPanel paymentMethodChartPanel = new ChartPanel(paymentMethodChart);
		paymentMethodChartPanel.setForeground(Color.decode(ColorConsts.ForegroundColor));
		paymentMethodChartPanel.setBackground(Color.decode(ColorConsts.ForegroundColor));

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

		datePickerTo.addDateChangeListener(this);
		datePickerFrom.addDateChangeListener(this);

		refreshButton = new JButton("Làm mới");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				datePickerFrom.setDate(LocalDate.now().minusDays(7));
				datePickerTo.setDateToToday();

				fetchAllData();
			}
		});

		datePickerGroup.add(datePickerFrom);
		datePickerGroup.add(datePickerTo);
		datePickerGroup.add(refreshButton);
		datePickerGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		insidePane.add(datePickerGroup);

		return new Header().addTitle("Trang chủ").addInsidePanel(insidePane).createView();
	}

	private void getTopSaleInCategory() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();

		topSaleInCategoryDataset.clear();
		for (TopSaleInCategory item : chiTietHoaDonDao.getTop5DanhMucThuocBanChay(from, to)) {
			topSaleInCategoryDataset.setValue(item.getCategoryName(), item.getSoLuongBanRa());
		}
	}

	private void getTopSaleInPremisDateset() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();

		topSaleInPremisDateset.clear();
		for (TopSaleInPremises item : quayDao.getTopSaleInPremises(from, to)) {
			topSaleInPremisDateset.addValue(item.getDoanhThu(), "Doanh thu", item.getTenQuay());
		}
	}

	private void getPaymentMethodPercentDataset() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();

		paymentMethodDataset.clear();
		for (PercentagePaymentMethod item : hoaDonDao.getPercentageOfMethodPayment(from, to)) {
			paymentMethodDataset.setValue(item.getPhuongThucThanhToan(), item.getSoLuongDonHang());
		}
	}

	private void getIncomeInPeriod() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();
		
		incomeDataSet.clear();
		for (IncomeInPeriod item : hoaDonDao.getDoanhThuTrongKhoang(from, to)) {
			incomeDataSet.addValue(item.getValue(), "Doanh thu", item.getyLabel());
		}
	}
	
	private void getTop10ThuocBanChayNhat() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();
		
		topSaleProductModel.clear();
		for (TopThuocBanChay item : thuocDao.getTop10ThuocBanChay(from, to)) {
			topSaleProductModel.addElement(item);
		}
		
	}
	
	private void getTongDoanhThu() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();
		
		long tongDoanhThu = hoaDonDao.getTongDoanhThu(from, to);
		
		Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");
        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
        df.setCurrency(currency);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);
        
		incomeLabel.setText(numberFormat.format(tongDoanhThu));
	}
	
	@Override
	public void dateChanged(DateChangeEvent e) {
		fetchAllData();
	}

	private void fetchAllData() {
		LocalDate from = datePickerFrom.getDate();
		LocalDate to = datePickerTo.getDate();
		
		getTopSaleInPremisDateset();
		getPaymentMethodPercentDataset();
		getTopSaleInCategory();
		getIncomeInPeriod();
		getTop10ThuocBanChayNhat();
		getTongDoanhThu();
		
		long tongSoDonHang = hoaDonDao.getTongSoDonHang(from, to);
		orderCountLabel.setText(String.valueOf(tongSoDonHang));
		
		
	}
}
