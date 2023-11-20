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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Thuoc;
import utils.FileChooser;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
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
	
	private HoaDonDao hoaDonDao;
	private ChiTietHoaDonDao chiTietHoaDonDao;
	
	private ArrayList<HoaDon> dsHoaDon;
	private JLabel lblQuay;
	private JLabel lblTK;
	private JLabel lblNgayTaoDonHang;
	private JComboBox<String> comboBox;
	
	
	public OrderPage() {
		super();
	}

	@Override
	public JPanel onCreateNestedContainerView() {
		hoaDonDao = new HoaDonDao();
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		Font commonFont = new Font("Arial", Font.PLAIN, 14);

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setForeground(Color.decode(ColorConsts.ForegroundColor));
		contentPane.setBorder(new EmptyBorder(5, 10, 10, 10));

		JPanel datePickerGroup = new JPanel(new FlowLayout(FlowLayout.LEFT));
		datePickerGroup.setBorder(new EmptyBorder(0, 5, 0, 0));

		datePickerFrom = new DatePicker();
		datePickerFrom.setDate(LocalDate.now().minusDays(7));
		datePickerTo = new DatePicker();
		datePickerTo.setDateToToday();
		

		datePickerGroup.add(datePickerFrom);
		datePickerGroup.add(datePickerTo);
		//datePickerGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		String listCombo[] = "Mã hóa đơn;Quầy;Khách hàng;Trạng thái".split(";");
		comboBox= new JComboBox<String>(listCombo);
		comboBox.setBackground(Color.decode(ColorConsts.ForegroundColor));
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
		txtTim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTim.setText("");
			}
		});
		txtTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loc();
			}
		});
		
		comboBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTim.setText("");
			}
		});
		
		btnDTT = new JRadioButton("Đã thanh toán");
		btnDTT.setSelected(true);
		btnDTT.setFont(new Font("Arial",Font.BOLD,10));
		btnCTT = new JRadioButton("Chưa thanh toán");
		btnCTT.setFont(new Font("Arial",Font.BOLD,10));
		btnDTT.setVisible(false);
		btnCTT.setVisible(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnDTT);
		group.add(btnCTT);

		btnLoc = new JButton("Lọc");
		btnLoc.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnLoc.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnLoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loc();
			}

		});
		
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
		leftHeaderBox.add(Box.createVerticalStrut(10));

		String[] headerCols = "Mã hóa đơn;Quầy;Khách hàng;Tổng thanh toán;Thời gian;Trạng thái".split(";");
		orderTableModel = new DefaultTableModel(headerCols, 0);
		orderTable = new JTable(orderTableModel);
		orderTable.setFont(new Font("Arial", Font.PLAIN, 14));
		TableColumnModel columnModel2 = orderTable.getColumnModel();
		columnModel2.getColumn(0).setPreferredWidth(30);
		columnModel2.getColumn(2).setPreferredWidth(160);
		getDanhSachDonHang();
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = orderTable.getSelectedRow();
				if(i==-1)
					return;
				for (HoaDon hoaDon : dsHoaDon) {
					if((hoaDon.getMaHD()+"").equals(orderTable.getValueAt(i, 0).toString())) {
						txtDH.setText(hoaDon.getMaHD()+"");
						lblTK.setText(hoaDon.getNhanVien().getTenNhanVien()+"");
						lblQuay.setText(hoaDon.getQuay().getTenQuay()+"");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						lblNgayTaoDonHang.setText(dtf.format(hoaDon.getNgayLapHD()));
						txtTenKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang()+"");
						txtSdtKhachHang.setText(hoaDon.getKhachHang().getSoDienThoai()+"");
						ghiChuTextArea.setText(hoaDon.getGhiChu());
						
						ArrayList<ChiTietHoaDon> dsCTHD = chiTietHoaDonDao.getAllChiTietHoaDonByMaDonHang(hoaDon.getMaHD());
						int stt = 1;
						chiTietHoaDonModel.setNumRows(0);
						for (ChiTietHoaDon chiTietHoaDon : dsCTHD) {
							chiTietHoaDonModel.addRow(new Object[] {
									stt++,chiTietHoaDon.getSanPham().getTenThuoc(),
									chiTietHoaDon.getDonViTinh(), chiTietHoaDon.getSoLuong(),
									chiTietHoaDon.getDonGia(),chiTietHoaDon.getGiamGia(),
									chiTietHoaDon.getThanhTien()
							});
						}
						
						double tongTien = hoaDon.getTongTien()-hoaDon.getTongTienGiam();
						txtTongTienHang.setText(tongTien+"");
						txtTongGiamGia.setText(hoaDon.getTongTienGiam()+"");
						txtKhachThanhToan.setText(hoaDon.getTongTien()+"");
						txtPttt.setText(hoaDon.getPhuongThucThanhToan()+"");
						ghiChuTextArea.setText(hoaDon.getGhiChu());
						if(hoaDon.getTrangThai().equals("Chưa TT"))
							btnXuatHoaDon.setText("Thanh toán");
						else btnXuatHoaDon.setText("Xuất hóa đơn");
					}
				}
			}
		});
		
		
		
		taoDonHang = new JButton("Tạo đơn hàng");
		taoDonHang.setBackground(Color.decode(ColorConsts.ForegroundColor));
		taoDonHang.setForeground(Color.decode(ColorConsts.PrimaryColor));
		taoDonHang.setPreferredSize(new Dimension(0,50));
		taoDonHang.setFont(new Font("Arial",Font.BOLD,16));
		taoDonHang.setIcon(new ImageIcon("icon\\ic_create.png"));
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
		
		
		Box donHangGroup = Box.createHorizontalBox();
		JLabel lblTTDH;
		donHangGroup.add(lblTTDH = new JLabel("Đơn hàng"));
		lblTTDH.setPreferredSize(new Dimension(160,20));
		lblTTDH.setFont(new Font("Arial", Font.BOLD, 16));
		donHangGroup.add(txtDH);
	

		/*
		 * Don hang ngay gio
		 */
		lblTK = new JLabel(".");
		lblTK.setFont(commonFont);
		
		
		lblNgayTaoDonHang = new JLabel("");
		lblNgayTaoDonHang.setFont(commonFont);
		lblNgayTaoDonHang.setAlignmentX(CENTER_ALIGNMENT);
		lblNgayTaoDonHang.setHorizontalAlignment(JLabel.CENTER);
		
		lblQuay = new JLabel("");
		lblQuay.setFont(commonFont);

		JPanel ngayGioDonHangGroup = new JPanel(new BorderLayout());
		ngayGioDonHangGroup.add(lblTK, BorderLayout.WEST);
		ngayGioDonHangGroup.add(lblNgayTaoDonHang, BorderLayout.CENTER);
		ngayGioDonHangGroup.add(lblQuay, BorderLayout.EAST);

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
		
		setCellEditable();
		
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
		txtTenKhachHang = new JTextField("");
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
		txtSdtKhachHang = new JTextField("");
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
		txtTongTienHang = new JTextField("");
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
		txtTongGiamGia = new JTextField("");
		txtTongGiamGia.setEditable(false);
		tongGiamGiaBox.add(lblTongGiamGia);
		tongGiamGiaBox.add(txtTongGiamGia);

		/**
		 * Tổng giảm box
		 */
		Box khachThanhToanBox = Box.createHorizontalBox();
		JLabel lblKhachThanhToan = new JLabel("Thanh toán");
		lblKhachThanhToan.setFont(commonFont);
		lblKhachThanhToan.setPreferredSize(lblTienThua.getPreferredSize());
		txtKhachThanhToan = new JTextField("");
		txtKhachThanhToan.setEditable(false);
		khachThanhToanBox.add(lblKhachThanhToan);
		khachThanhToanBox.add(txtKhachThanhToan);
		
		/**
		 * Khách thanh toán box
		 */
//		Box khachThanhToanBox = Box.createHorizontalBox();
//		JLabel lblKhachThanhToan = new JLabel("Khách thanh toán");
//		lblKhachThanhToan.setFont(commonFont);
//		lblKhachThanhToan.setPreferredSize(lblTienThua.getPreferredSize());
//		txtKhachThanhToan = new JTextField("abc");
//		txtKhachThanhToan.setEditable(false);
//		khachThanhToanBox.add(lblKhachThanhToan);
//		khachThanhToanBox.add(txtKhachThanhToan);

		
		/**
		 * Khách thanh toán box
		 */
		Box ptttBox = Box.createHorizontalBox();
		JLabel lblpttt = new JLabel("Phương thức thanh toán");
		JLabel lblspace = new JLabel();
		lblspace.setPreferredSize(new Dimension(270,0));
		lblpttt.setFont(commonFont);
		lblpttt.setPreferredSize(lblTienThua.getPreferredSize());
		txtPttt = new JTextField("");
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
				if(txtDH.getText().isEmpty())return;
				if(txtPttt.getText().trim().isEmpty()) {
					if(JOptionPane.showConfirmDialog((RootFrame) SwingUtilities.getWindowAncestor(OrderPage.this),"Số tiền khách hàng cần thanh toán là "+ txtTongTienHang.getText()+"VND","Thanh toán",
							JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						if(hoaDonDao.setTrangThai(Integer.parseInt(txtDH.getText().trim())))
							JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(OrderPage.this),
								"Đã thanh toán!");
				}else {
					String cthd = "";
					for(int i = 0;i<chiTietHoaDonModel.getRowCount();i++) {
						cthd+= ("\t"+(i+1)+". "+chiTietHoaDonModel.getValueAt(i, 1)+"("
								+chiTietHoaDonModel.getValueAt(i, 2)+")\n"
								+"\t\t"+chiTietHoaDonModel.getValueAt(i, 3)
								+"\t\t"+chiTietHoaDonModel.getValueAt(i, 4)
								+"\t\t"+chiTietHoaDonModel.getValueAt(i, 6)+"\n");
					}
					String content = 
							"\t\t\t NHÀ THUỐC HKTD\n"
							+ "\t\t  www.nhathuochktd.com\n"
							+ "\t\t\tQuầy: "+lblQuay.getText()+"\n"
							+ "\t-------------------------------\n"
							+ "\tPHIẾU THANH TOÁN (Bao gồm VAT)\n"
							+ "\tSố HD: "+txtDH.getText()+"/ Ngày: "+lblNgayTaoDonHang.getText()+"\n"
							+ "\tNhân viên: "+lblTK.getText()+"\n"
							+ "\t\tSL\t\tGiá bán\t\tTTiền\n"
							+ cthd
							+ "\t--------------------------------\n"
							+ "\tPhải thanh toán:\t\t"+txtTongTienHang.getText()+"\n"
							+ "\t--------------------------------\n"
							+ "\tNhà thuốc chỉ xuất hóa đơn và đổi\n"
							+ "\ttrả trong ngày vui lòng liên hệ\n"
							+ "\tnhân viên để được phụ vụ,\n"
							+ "\tXin cảm ơn Quý Khách!";
					
					try { 
						String path = FileChooser.fileButtonActionPerformed();
						if(path==null) {
							return;
						}
						if(saveFile(content, path+"_HD"+txtDH.getText()+".txt"))
							JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(OrderPage.this),
									"Đã xuất hóa đơn!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
		right.add(khachThanhToanBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ptttBox);
		right.add(Box.createVerticalStrut(20));
		right.add(ghiChuBox);
		right.add(Box.createVerticalStrut(40));
		right.add(btnXuatHoaDon);

		
		contentPane.add(left, BorderLayout.CENTER);
		contentPane.add(right, BorderLayout.EAST);
		
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
	private void dropText() {
		txtDH.setText("");
		lblTK.setText(".");
		lblQuay.setText("");
		lblNgayTaoDonHang.setText("");
		txtTenKhachHang.setText("");
		txtSdtKhachHang.setText("");
		DefaultTableModel model  = (DefaultTableModel) chiTietHoaDonTable.getModel();
		model.setRowCount(0);
		txtTongTienHang.setText("");
		txtTongGiamGia.setText("");
		txtKhachThanhToan.setText("");
		txtPttt.setText("");
		ghiChuTextArea.setText("");
	}
	public void loc() {
		dropText();
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		model.setRowCount(0);
		if(comboBox.getSelectedItem().toString().equals("Mã hóa đơn")) {
			if(txtTim.getText().trim().isEmpty()) {
				getDanhSachDonHang();
				return;
			}else {
				for (HoaDon hoaDon : dsHoaDon) {
					if(txtTim.getText().trim().equalsIgnoreCase(hoaDon.getMaHD()+""))
						orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
							hoaDon.getNgayLapHD(), hoaDon.getTrangThai()});
				}
			}
		}else
		if(comboBox.getSelectedItem().toString().equals("Quầy")) {
			if(txtTim.getText().trim().isEmpty()) {
				getDanhSachDonHang();
				return;
			}else {
				for (HoaDon hoaDon : dsHoaDon) {
					if(txtTim.getText().trim().equalsIgnoreCase(hoaDon.getQuay().getMaQuay()+"")
							||txtTim.getText().trim().equalsIgnoreCase(hoaDon.getQuay().getTenQuay()+""))
						orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
							hoaDon.getNgayLapHD(), hoaDon.getTrangThai()});
				}
			}
		}else
		if(comboBox.getSelectedItem().toString().equals("Khách hàng")) {
			if(txtTim.getText().trim().isEmpty()) {
				getDanhSachDonHang();
				return;
			}else {
				for (HoaDon hoaDon : dsHoaDon) {
					if(txtTim.getText().trim().equalsIgnoreCase(hoaDon.getKhachHang().getMaKhachHang()+"")||
							txtTim.getText().trim().equalsIgnoreCase(hoaDon.getKhachHang().getTenKhachHang()+"")||
							txtTim.getText().trim().equalsIgnoreCase(hoaDon.getKhachHang().getSoDienThoai()+""))
						orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
							hoaDon.getNgayLapHD(), hoaDon.getTrangThai()});
				}
			}
		}else {
			if(btnDTT.isSelected()) {
				for (HoaDon hoaDon : dsHoaDon) {
					if((hoaDon.getTrangThai()+"").equalsIgnoreCase("Đã TT"))
						orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
							hoaDon.getNgayLapHD(), hoaDon.getTrangThai()});
				}
			}else {
				for (HoaDon hoaDon : dsHoaDon) {
					if((hoaDon.getTrangThai()+"").equalsIgnoreCase("Chưa TT"))
						orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
							hoaDon.getNgayLapHD(), hoaDon.getTrangThai()});
				}
			}
		}
	}
	
	public void getDanhSachDonHang() {
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		model.setRowCount(0);
		String dateFrom = datePickerFrom.getDateStringOrEmptyString();
		String dateTo = datePickerTo.getDateStringOrEmptyString();
		LocalDate tuNgay;
		LocalDate denNgay;
		if(dateFrom.isEmpty()&&dateTo.isEmpty()) {//lấy tất cả
			dsHoaDon = hoaDonDao.getAllTBHoaDon();
		}else if(dateFrom.isEmpty()&&!dateTo.isEmpty()) {//lấy đến ngày
			denNgay = datePickerTo.getDate();
			dsHoaDon = hoaDonDao.getHoaDonTheoNgayDen(denNgay);
		}else if(!dateFrom.isEmpty()&&dateTo.isEmpty()) {//lấy từ ngày
			tuNgay = datePickerFrom.getDate();
			dsHoaDon = hoaDonDao.getHoaDonTheoNgayTu(tuNgay);
		}else if(!dateFrom.isEmpty()&&!dateTo.isEmpty()) {
			tuNgay = datePickerFrom.getDate();
			denNgay = datePickerTo.getDate();
			dsHoaDon = hoaDonDao.getHoaDonTheoNgay(tuNgay, denNgay);
		}
		if(dsHoaDon == null)
			return;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (HoaDon hoaDon : dsHoaDon) {
			orderTableModel.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getQuay().getTenQuay(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getTongTien(),
					dtf.format(hoaDon.getNgayLapHD()), hoaDon.getTrangThai()});
		}
	}
	public void setCellEditable() {
		for (int i = 0; i < orderTable.getColumnCount(); i++) {
			orderTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
				@Override
				public boolean isCellEditable(EventObject e) {
					// Trả về false để ngăn chặn chỉnh sửa trực tiếp
					return false;
				}
			});
		}
		for (int i = 0; i < chiTietHoaDonTable.getColumnCount(); i++) {
			chiTietHoaDonTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
				@Override
				public boolean isCellEditable(EventObject e) {
					// Trả về false để ngăn chặn chỉnh sửa trực tiếp
					return false;
				}
			});
		}
	}
	public static boolean saveFile(String content,String filePath) throws Exception{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedOutputStream(
					new FileOutputStream(filePath)),true);
			out.println(content);
		} catch(Exception e) {
			return false;
		}
		finally {
			if(out!=null)out.close();
		}
		return true;
	}
}
