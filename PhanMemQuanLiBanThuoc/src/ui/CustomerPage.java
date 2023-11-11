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

import javax.print.event.PrintJobListener;
import javax.swing.Box;
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
import components.TopSaleProductView;
import dao.QuayDao;

public class CustomerPage extends BasePage  implements MouseListener{
	
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtHo;
	private JTextField txtSdt;
	private JButton btn_tim;
	private JButton btn_them;
	private JButton btn_xoa;
	private JButton btn_xoaTrang;
	private JButton btn_sua;
	private JButton btn_luu;
	
	private DefaultTableModel cusModel;
	private JTable cusTable;
	
	
	private JList topCustomerList;
	private DefaultListModel<Object> topCustomerModel;

	private JFreeChart incomeIPeriodChart;
	private ChartPanel incomeIPeriodChartPanel;
	public CustomerPage() {
		super();
	}
	
	@Override
	protected JPanel onCreateNestedContainerView() {
		Font commonFont = new Font("Arials", Font.PLAIN, 14);
		Font commonButtonFont = new Font("Arials", Font.BOLD, 16);

		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.decode(ColorConsts.ForegroundColor));

		cusModel = new DefaultTableModel(
				new String[] {"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Quầy Mua"}, 0);
		cusTable = new JTable(cusModel);

		JTableHeader headerTable = cusTable.getTableHeader();
		headerTable.setBackground(Color.decode(ColorConsts.BackgroundColor));
		headerTable.setFont(new Font("Arial", Font.BOLD, 14));
		headerTable.setPreferredSize(new Dimension(headerTable.getPreferredSize().width, 40));

		cusTable.setRowHeight(40);
		cusTable.setShowVerticalLines(false);
		cusTable.setFont(new Font("Arial", Font.PLAIN, 14));
		cusTable.setRowHeight(40);
		cusTable.setIntercellSpacing(new Dimension(0, 0));
		cusTable.setGridColor(Color.decode("#696969"));
		cusTable.setTableHeader(headerTable);
		cusTable.addMouseListener(this);

		btn_them = new JButton("Thêm");
		btn_them.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_them.setFont(commonButtonFont);
		btn_them.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreatePremisesFrm().setVisible(true);
			}
		});
		btn_xoa = new JButton("Xóa");
		btn_xoa.setPreferredSize(new Dimension(0, 50));
		btn_xoa.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_xoa.setFont(commonButtonFont);
		btn_xoa.setForeground(Color.decode(ColorConsts.ForegroundColor));
//		btn_xoa.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int row = cusTable.getSelectedRow();
//				int maQuay = Integer.parseInt(cusModel.getValueAt(row, 0).toString());
//				String tenQuay = cusModel.getValueAt(row, 1).toString();
//				
//				if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Khách Hàng " + tenKhachHang, "Confirm",
//						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//
//					if (KhachHangDao.deleteQuay(maKH)) {
//						cusModel.removeRow(row);
//					}
//				}
//			}
//		});
		
		btn_xoaTrang = new JButton("Xóa trắng");
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_xoaTrang.setFont(commonButtonFont);
		btn_xoaTrang.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_xoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_sua = new JButton("Sửa");
		btn_sua.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btn_sua.setFont(commonButtonFont);
		btn_sua.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btn_sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel footer = new JPanel(new GridLayout(1, 4));
		footer.add(btn_them);
		footer.add(btn_xoa);
		footer.add(btn_xoaTrang);
		footer.add(btn_sua);

		left.add(new JScrollPane(cusTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		left.add(footer, BorderLayout.SOUTH);

		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setMinimumSize(new Dimension(500, right.getPreferredSize().height));
		/**
		 * Tiêu đề
		 */
		JLabel tieuDeLabel = new JLabel("Khách Hàng");
		tieuDeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		tieuDeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

//		Box tpBox = Box.createHorizontalBox();
//		JLabel tbLb = new JLabel("Thành phố");
////
////		tbLb.setFont(commonFont);
////		thanhPhoTxt = new JTextField("");
////		thanhPhoTxt.setEditable(false);
////		tpBox.add(tbLb);
////		tpBox.add(thanhPhoTxt);

		Box maKhBox = Box.createHorizontalBox();
		JLabel maKHLb = new JLabel("Mã Khách Hàng");

		maKHLb.setFont(commonFont);
		txtMa = new JTextField("");
		txtMa.setEditable(false);
		maKhBox.add(maKHLb);
		maKhBox.add(txtMa);
		
		
		Box tenKHBox = Box.createHorizontalBox();
		JLabel tenKHLb = new JLabel("Tên Khách Hàng");

		tenKHLb.setFont(commonFont);
		tenKHLb.setPreferredSize(maKHLb.getPreferredSize());
		txtTen = new JTextField("");
		txtTen.setEditable(false);
		tenKHBox.add(tenKHLb);
		tenKHBox.add(txtTen);
		
		Box hoKHBox = Box.createHorizontalBox();
		JLabel hoKHLb = new JLabel("Họ Khách Hàng");

		hoKHLb.setFont(commonFont);
		hoKHLb.setPreferredSize(maKHLb.getPreferredSize());
		txtHo = new JTextField("");
		txtHo.setEditable(false);
		hoKHBox.add(hoKHLb);
		hoKHBox.add(txtHo);

		Box sdtBox = Box.createHorizontalBox();
		JLabel sdtLb = new JLabel("Số Điện Thoại");
		sdtLb.setFont(commonFont);
		sdtLb.setPreferredSize(maKHLb.getPreferredSize());
		txtSdt = new JTextField("");
		txtSdt.setEditable(false);
		sdtBox.add(sdtLb);
		sdtBox.add(txtSdt);
		
		JLabel doanhThuTheoThangLb = new JLabel("Tổng đơn hàng đã mua theo tháng");
		doanhThuTheoThangLb.setFont(new Font("Arials", Font.BOLD, 20));
		doanhThuTheoThangLb.setBorder(new EmptyBorder(20, 0, 20, 0));

		right.add(tieuDeLabel);
		right.add(maKhBox);
		right.add(Box.createVerticalStrut(20));
		right.add(tenKHBox);
		right.add(Box.createVerticalStrut(20));
		right.add(hoKHBox);
		right.add(Box.createVerticalStrut(20));
		right.add(sdtBox);
		right.add(Box.createVerticalStrut(20));
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(left, BorderLayout.CENTER);
		panel.add(new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
				BorderLayout.EAST);

		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		// TODO Auto-generated method stub
		
		return new Header()
				.addTitle("Khách hàng")
				.createView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
