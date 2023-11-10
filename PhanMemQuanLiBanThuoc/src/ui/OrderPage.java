package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jfree.ui.tabbedui.VerticalLayout;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import components.Header;
import components.OrderDetailView;
import components.TopSaleProductView;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Thuoc;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;

public class OrderPage extends BasePage implements MouseListener{


	private DefaultTableModel orderTableModel;
	private JTable orderTable;

	private JLabel tieuDeLabel;
	
	
	private JTextField txtTenKhachHang;
	private JTextField txtSdtKhachHang;
	
	private JTextField txtTim;
	private JTextField txtTongTienHang;
	private JTextField txtTongGiamGia;
	private JTextField txtKhachCanTra;
	private JTextField txtKhachThanhToan;
	private JTextField txtTienThua;
	private JTextField txtDH;
	private JTextField txtTenKH;
	
	private JTextArea ghiChuTextArea;
	
	private JList list2;
	private JList listThuoc;
	
	private JTable chiTietHoaDonTable;
	private DefaultTableModel chiTietHoaDonModel;
	
	private JButton taoDonHang;
	private JButton btnXuatHoaDon;
	
	private JRadioButton tienMatRdBtn;
	private JRadioButton nganHangRdBtn;
	private JRadioButton viDienTuRdBtn;
	private JTextField txtPttt;
	private DatePicker datePickerFrom;
	private DatePicker datePickerTo;
	private JRadioButton btnDTT;
	private JRadioButton btnCTT;
	private JButton btnLoc;
	
	
	public OrderPage() {
		super();

		orderTableModel.addRow(new String[] {
			"1", "1", "1","1","1","1","1"
		});
	}

	@Override
	public JPanel onCreateNestedContainerView() {
		
		Font commonFont = new Font("Arial", Font.PLAIN, 14);

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setForeground(Color.decode(ColorConsts.ForegroundColor));
		contentPane.setBorder(new EmptyBorder(5, 10, 10, 10));

		JPanel datePickerGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
		datePickerGroup.setBorder(new EmptyBorder(0, 5, 0, 0));

		datePickerFrom = new DatePicker();
		//datePickerFrom.setDate(LocalDate.now().minusDays(7));
		datePickerTo = new DatePicker();
		//datePickerTo.setDateToToday();

		datePickerGroup.add(datePickerFrom);
		datePickerGroup.add(datePickerTo);
		//datePickerGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		String listCombo[] = "Mã hóa đơn;Quầy;Khách hàng;Trạng thái".split(";");
		JComboBox<String> comboBox= new JComboBox<String>(listCombo);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString().equals("Trạng thái")) {
					btnDTT.setVisible(true);
					btnCTT.setVisible(true);
					txtTim.setVisible(false);
				}else {
					btnDTT.setVisible(false);
					btnCTT.setVisible(false);
					txtTim.setVisible(true);
				}
			}
		});
		
		txtTim = new JTextField(18);
		txtTim.setPreferredSize(new Dimension(0,25));
		
		btnDTT = new JRadioButton("Đã thanh toán");
		btnCTT = new JRadioButton("Chưa thanh toán");
		btnDTT.setVisible(false);
		btnCTT.setVisible(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnDTT);
		group.add(btnCTT);

		btnLoc = new JButton("Lọc");
		btnLoc.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnLoc.setForeground(Color.decode(ColorConsts.ForegroundColor));
		

		Box leftHeaderBox = Box.createHorizontalBox();
		leftHeaderBox.add(Box.createVerticalStrut(10));
		leftHeaderBox.setForeground(Color.decode(ColorConsts.ForegroundColor));
		leftHeaderBox.add(datePickerGroup);
		JPanel leftHeaderRight = new JPanel();
		leftHeaderRight.add(comboBox);
		leftHeaderRight.add(txtTim);
		leftHeaderRight.add(btnDTT);
		leftHeaderRight.add(btnCTT);
		leftHeaderRight.add(btnLoc);
		leftHeaderBox.add(leftHeaderRight);
//		leftHeaderBox.add(comboBox);
//		leftHeaderBox.add(txtTim);
//		leftHeaderBox.add(btnDTT);
//		leftHeaderBox.add(btnCTT);
//		leftHeaderBox.add(btnLoc);
		leftHeaderBox.add(Box.createVerticalStrut(10));
//		leftHeaderBox.setPreferredSize(new Dimension(200,25));

		String[] headerCols = "Mã hóa đơn;Quầy;Khách hàng;Tổng thanh toán;Thời gian;Trạng thái".split(";");
		orderTableModel = new DefaultTableModel(headerCols, 0);
		orderTable = new JTable(orderTableModel);
		orderTable.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		
		
		taoDonHang = new JButton("Tạo đơn hàng");
		taoDonHang.setBackground(Color.decode(ColorConsts.ForegroundColor));
		taoDonHang.setForeground(Color.decode(ColorConsts.PrimaryColor));
		taoDonHang.setPreferredSize(new Dimension(0,50));
		taoDonHang.setFont(new Font("Arial",Font.BOLD,16));
		taoDonHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateBillFrm().setVisible(true);;
//		        RootFrame rootFrame = (RootFrame) SwingUtilities.getWindowAncestor(OrderPage.this);
//		        BasePage orderPage = rootFrame.getOrderPage();
//		        Container container = getParent(); 
//		        container.remove(OrderPage.this); 
//		        rootFrame.getContentPane().add(orderPage, BorderLayout.CENTER);
//		        rootFrame.revalidate();
//		        rootFrame.repaint();
			}
		});
		JPanel leftButtonGroup = new JPanel(new BorderLayout());
		leftButtonGroup.add(taoDonHang);
		
		
		
		JPanel left = new JPanel(new BorderLayout());
//		left.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		left.add(leftHeaderBox, BorderLayout.NORTH);
		JScrollPane orderCroll;
		left.add(orderCroll = new JScrollPane(orderTable), BorderLayout.CENTER);
		left.add(leftButtonGroup, BorderLayout.SOUTH);
		left.setForeground(Color.decode(ColorConsts.ForegroundColor));
		/**
		 * Don hang text group
		 */
		JLabel lblDonHang = new JLabel("Mã đơn hàng");
		lblDonHang.setFont(commonFont);

		txtDH = new JTextField();
		lblDonHang.setPreferredSize(new Dimension(lblDonHang.getPreferredSize().width + 20, lblDonHang.getPreferredSize().height));
		txtDH.setEditable(false);
		
		JLabel lblQuay = new JLabel("     Quầy số 1");
		lblQuay.setFont(commonFont);
		Box donHangGroup = Box.createHorizontalBox();
		donHangGroup.add(lblDonHang);
		donHangGroup.add(txtDH);
		donHangGroup.add(lblQuay);

		/*
		 * Don hang ngay gio
		 */
		JLabel lblTK = new JLabel("admin");
		lblTK.setFont(commonFont);
		
		
		JLabel lblNgayTaoDonHang = new JLabel("05/11/2023");
		lblNgayTaoDonHang.setFont(commonFont);
		lblNgayTaoDonHang.setAlignmentX(CENTER_ALIGNMENT);
		lblNgayTaoDonHang.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblgioTaoDonHang = new JLabel("12:30");
		lblgioTaoDonHang.setFont(commonFont);

		JPanel ngayGioDonHangGroup = new JPanel(new BorderLayout());
		ngayGioDonHangGroup.add(lblTK, BorderLayout.WEST);
		ngayGioDonHangGroup.add(lblNgayTaoDonHang, BorderLayout.CENTER);
		ngayGioDonHangGroup.add(lblgioTaoDonHang, BorderLayout.EAST);

		/**
		 * Tim khach hang label title
		 */
		JLabel lblTimKH = new JLabel("Thông tin khách hàng");
		lblTimKH.setFont(new Font("Arials", Font.BOLD, 16));
		lblTimKH.setBorder(new EmptyBorder(15, 0, 15, 0));
		
		String[] colOrderDetails = "STT;Tên hàng;Đơn vị tính;Số lượng;Giá bán;Giảm giá;Thành tiền".split(";");
		chiTietHoaDonModel = new DefaultTableModel(colOrderDetails, 0);
		chiTietHoaDonTable = new JTable(chiTietHoaDonModel);
		
		TableColumnModel columnModel = chiTietHoaDonTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(7);
		columnModel.getColumn(1).setPreferredWidth(130);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(40);
		columnModel.getColumn(5).setPreferredWidth(40);
		
		chiTietHoaDonTable.addMouseListener(this);
		/**
		 * Tiền thừa box
		 */
		Box tienThuaBox = Box.createHorizontalBox();
		JLabel lblTienThua = new JLabel("Tiền thừa trả khách");
		lblTienThua.setPreferredSize(new Dimension(lblTienThua.getPreferredSize().width + 50, lblTienThua.getPreferredSize().height));
		lblTienThua.setFont(commonFont);
		txtTienThua = new JTextField("1,000,000d");
		txtTienThua.setEditable(false);
		tienThuaBox.add(lblTienThua);
		tienThuaBox.add(txtTienThua);
		
		/**
		 * Ten khách hàng
		 */

		JLabel tenKHLb = new JLabel("Tên khách hàng");
		tenKHLb.setFont(commonFont);
		tenKHLb.setPreferredSize(lblTienThua.getPreferredSize());
		tenKHLb.setBorder(new EmptyBorder(0,0, 0, 10));
		txtTenKhachHang = new JTextField("Nguyễn Quốc Huy");
		txtTenKhachHang.setEditable(false);


		Box tenKHBox = Box.createHorizontalBox();
		tenKHBox.add(tenKHLb);
		tenKHBox.add(txtTenKhachHang);

		/**
		 * sdtg khách hàng
		 */

		JLabel sdtKHLb = new JLabel("Số điện thoại");
		sdtKHLb.setFont(commonFont);
		sdtKHLb.setBorder(new EmptyBorder(0,0, 0, 10));
		sdtKHLb.setPreferredSize(lblTienThua.getPreferredSize());
		txtSdtKhachHang = new JTextField("0868684969");
		txtSdtKhachHang.setEditable(false);


		Box sdtKHBox = Box.createHorizontalBox();
		sdtKHBox.add(sdtKHLb);
		sdtKHBox.add(txtSdtKhachHang);

		
		/**
		 * Tổng tiền box
		 */
		Box tongTiengHangBox = Box.createHorizontalBox();
		JLabel lblTongTienHang = new JLabel("Tổng tiền hàng");
		lblTongTienHang.setFont(commonFont);
		lblTongTienHang.setPreferredSize(lblTienThua.getPreferredSize());
		txtTongTienHang = new JTextField("1,000,000d");
		txtTongTienHang.setEditable(false);
		tongTiengHangBox.add(lblTongTienHang);
		tongTiengHangBox.add(txtTongTienHang);
		
		
		/**
		 * Tổng giảm box
		 */
		Box tongGiamGiaBox = Box.createHorizontalBox();
		JLabel lblTongGiamGia = new JLabel("Tổng giảm giá");
		lblTongGiamGia.setFont(commonFont);
		lblTongGiamGia.setPreferredSize(lblTienThua.getPreferredSize());
		txtTongGiamGia = new JTextField("1,000,000d");
		txtTongGiamGia.setEditable(false);
		tongGiamGiaBox.add(lblTongGiamGia);
		tongGiamGiaBox.add(txtTongGiamGia);

		/**
		 * Tổng giảm box
		 */
		Box khachCanTraBox = Box.createHorizontalBox();
		JLabel lblKhachCanTra = new JLabel("Thanh toán");
		lblKhachCanTra.setFont(commonFont);
		lblKhachCanTra.setPreferredSize(lblTienThua.getPreferredSize());
		txtKhachCanTra = new JTextField("1,000,000d");
		txtKhachCanTra.setEditable(false);
		khachCanTraBox.add(lblKhachCanTra);
		khachCanTraBox.add(txtKhachCanTra);
		
		/**
		 * Khách thanh toán box
		 */
		Box khachThanhToanBox = Box.createHorizontalBox();
		JLabel lblKhachThanhToan = new JLabel("Khách thanh toán");
		lblKhachThanhToan.setFont(commonFont);
		lblKhachThanhToan.setPreferredSize(lblTienThua.getPreferredSize());
		txtKhachThanhToan = new JTextField("1,000,000d");
		txtKhachThanhToan.setEditable(false);
		khachThanhToanBox.add(lblKhachThanhToan);
		khachThanhToanBox.add(txtKhachThanhToan);

		
		/**
		 * Khách thanh toán box
		 */
		Box ptttBox = Box.createHorizontalBox();
		JLabel lblpttt = new JLabel("Phương thức thanh toán");
		JLabel lblspace = new JLabel();
		lblspace.setPreferredSize(new Dimension(270,0));
		lblpttt.setFont(commonFont);
		lblpttt.setPreferredSize(lblTienThua.getPreferredSize());
		txtPttt = new JTextField("Tiền mặt");
		txtPttt.setHorizontalAlignment(JTextField.CENTER);
		txtPttt.setEditable(false);
		ptttBox.add(lblpttt);
		ptttBox.add(txtPttt);
		ptttBox.add(lblspace);
		/**
		 * Ghi chú Box
		 */
		Box ghiChuBox = Box.createHorizontalBox();
		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setFont(commonFont);
		lblGhiChu.setPreferredSize(lblTienThua.getPreferredSize());
		ghiChuTextArea = new JTextArea();
		ghiChuTextArea.setEditable(false);
		ghiChuTextArea.setPreferredSize(new Dimension(
					ghiChuTextArea.getPreferredSize().width,
					100
				));
		ghiChuBox.add(lblGhiChu);
		ghiChuBox.add(new JScrollPane(ghiChuTextArea));

		/**
		 * Tiêu đề
		 */
		tieuDeLabel = new JLabel("Chi tiết đơn hàng");
		tieuDeLabel.setFont(new Font("Arials", Font.BOLD, 30));
		tieuDeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		
		btnXuatHoaDon = new JButton("Xuất hóa đơn");
		btnXuatHoaDon.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnXuatHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		btnXuatHoaDon.setPreferredSize(new Dimension(200,50));
		btnXuatHoaDon.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnXuatHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel right = new JPanel(new VerticalLayout());
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.setBackground(Color.decode(ColorConsts.ForegroundColor));
		right.setMinimumSize(new Dimension(500, right.getPreferredSize().height));
		right.add(donHangGroup);
		right.add(Box.createVerticalStrut(10));
		right.add(ngayGioDonHangGroup);
		right.add(Box.createVerticalStrut(0));
		right.add(lblTimKH);
		right.add(Box.createVerticalStrut(0));
		right.add(tenKHBox);
		right.add(Box.createVerticalStrut(10));
		right.add(sdtKHBox);
		right.add(Box.createVerticalStrut(20));
		
		JScrollPane ctHDCroll = new  JScrollPane(chiTietHoaDonTable);
		ctHDCroll.setPreferredSize(new Dimension(540,200));
		right.add(ctHDCroll);
		
		
		right.add(Box.createVerticalStrut(10));
		right.add(tongTiengHangBox);
		right.add(Box.createVerticalStrut(10));
		right.add(tongGiamGiaBox);
		right.add(Box.createVerticalStrut(10));
		right.add(khachCanTraBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ptttBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ghiChuBox);
		right.add(Box.createVerticalStrut(40));
		right.add(btnXuatHoaDon);

		
		contentPane.add(left, BorderLayout.CENTER);
		contentPane.add(right, BorderLayout.EAST);
		
		
		chiTietHoaDonModel.addRow(new String[] {
				"1",
				"Ma Tuy Da",
				"1",
				"500000",
				"500000"
		});
		
		return contentPane;
	}

	@Override
	protected JPanel onCreateHeader() {
		return new Header().addTitle("Danh sách đơn hàng").createView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
