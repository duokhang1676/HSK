package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

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

import components.ColorConsts;
import components.Header;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class OrderPage extends BasePage {
	private JPanel contentPane;
	private DefaultTableModel orderTableModel;
	private JTable tb;
	private JTextField txtTimKH;
	private JTextField txtTim;
	private JList listThuoc;
	private JTextField txtTongTienHang;
	private JTextField txtTongGiamGia;
	private JTextField txtKhachCanTra;
	private JTextField txtKhachThanhToan;
	private JTextField txtTienThua;
	private JTextField txtDH;
	private JList list2;
	private JTextField txtTenKH;
	private JButton btn10k;
	private JButton btn20k;
	private JButton btn50k;
	private JButton btn100k;
	private JButton btn200k;
	private JButton btn500k;

	
	
	public OrderPage() {
		super();
	}
	
	@Override
	public JPanel onCreateNestedContainerView() {
		Font font = new Font("Arial", Font.BOLD, 16);
		
		contentPane = new JPanel();
		add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		int w = 800, h = 650;
		JPanel left = new JPanel();
		left.setLayout(null);
		left.setBounds(10, 0, w, h+50);
		contentPane.add(left);
		
		JLabel lblTimThuoc = new JLabel("Tìm kiếm thuốc");
		lblTimThuoc.setBounds(0, 10, 200, 19);
		left.add(lblTimThuoc);
		lblTimThuoc.setFont(font);
		
		txtTim = new JTextField();
		txtTim.setBounds(135, 10, 480, 26);
		txtTim.setColumns(10);
		left.add(txtTim);
		
		JButton btnHoaDonDaTao = new JButton("Đơn hàng đã tạo");
		btnHoaDonDaTao.setBounds(630, 10, 170, 25);
		left.add(btnHoaDonDaTao);
		btnHoaDonDaTao.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnHoaDonDaTao.setForeground(Color.decode(ColorConsts.ForegroundColor));
		
		btnHoaDonDaTao.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thể hiện của RootFrame
		        RootFrame rootFrame = (RootFrame) SwingUtilities.getWindowAncestor(OrderPage.this);
		        // Lấy trang OrderListPage
		        BasePage orderListPage = rootFrame.getOrderListPage();
		        Container container = getParent(); 
		        container.remove(OrderPage.this); 
		        // Thêm trang OrderListPage vào trung tâm của RootFrame
		        rootFrame.add(orderListPage, BorderLayout.CENTER);
		        // Yêu cầu RootFrame vẽ lại
		        rootFrame.revalidate();
		        rootFrame.repaint();
		    }
		});


		
		String header[] = "STT;Tên hàng;Đơn vị tính;Số lượng;Giá bán;Giảm giá;Thành tiền".split(";");
		orderTableModel = new DefaultTableModel(header,0);
		tb = new JTable(orderTableModel);
		JScrollPane croll = new JScrollPane(tb);
		croll.setBounds(0, 0, w, h);
		
		TableColumnModel columnModel = tb.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(230);
		columnModel.getColumn(4).setPreferredWidth(110);
		columnModel.getColumn(5).setPreferredWidth(110);
		columnModel.getColumn(6).setPreferredWidth(110);
		
		String[] entries = {"Entry 1","Entry 2","Entry 3","Entry 4","Entry 5","Entry 6"};
		listThuoc = new JList(entries);
		listThuoc.setVisibleRowCount(5);
		listThuoc.setPreferredSize(new Dimension(100,100));
		JScrollPane listPane = new JScrollPane(listThuoc);
		listPane.setBounds(135, 0, 480, 300);
		listPane.setVisible(false);
		this.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				    listPane.setVisible(false);
			}
		});
		listThuoc.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     listPane.setVisible(false);
				     //handle double click event.
				}
			}
		});
		txtTim.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				     listPane.setVisible(true);
				}
		});
		JLayeredPane layeredPane = new  JLayeredPane();
		layeredPane.setBounds(0,39,w,h);
		layeredPane.add(croll, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(listPane, JLayeredPane.PALETTE_LAYER);
		left.add(layeredPane);
		
		//--------------------------------------------
		
		int x2 = 820,x3 = 100,y1 = 10,y2 = 25, w2 = 500,h2 = 30;
		JPanel right = new JPanel();
		//right.setBackground(Color.decode(ColorConsts.BackgroundColor));
		right.setBounds(x2, 0, 600, 700);
		contentPane.add(right);
		right.setLayout(null);
		
		JLabel lblDonHang = new JLabel("Đơn hàng");
		lblDonHang.setBounds(0, y1, 150, h2);
		right.add(lblDonHang);
		lblDonHang.setFont(font);
		
		txtDH = new JTextField("DH123456");
		txtDH.setEditable(false);
		txtDH.setBounds(150, y1+5, 150, 20);
		right.add(txtDH);
		txtDH.setColumns(10);
		
		JLabel lblQuay = new JLabel("Quầy số 1");
		lblQuay.setBounds(315, y1, 80, h2);
		right.add(lblQuay);
		
		JLabel lblTK = new JLabel("admin");
		lblTK.setBounds(0, y1+=y2+10, 45, 13);
		right.add(lblTK);
		
		JLabel lblNewLabel_10 = new JLabel("05/11/2023");
		lblNewLabel_10.setBounds(315, y1, 60, 13);
		right.add(lblNewLabel_10);
		
		
		JLabel lblNewLabel_9 = new JLabel("12:30");
		lblNewLabel_9.setBounds(270, y1, 40, 13);
		right.add(lblNewLabel_9);
		
		JLabel lblTimKH = new JLabel("Tìm khách hàng");
		lblTimKH.setBounds(0, y1+=y2, 125, 13);
		right.add(lblTimKH);
		
		txtTimKH = new JTextField();
		txtTimKH.setBounds(0, y1+=y2, 300, 20);
		right.add(txtTimKH);
		txtTimKH.setColumns(10);
		
		txtTenKH = new JTextField();
		txtTenKH.setBounds(0, y1+=y2, 300, 20);
		right.add(txtTenKH);
		txtTenKH.setEditable(false);
	
		
		JButton btnThemKH = new JButton("+");
		btnThemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemKH.setBounds(310, y1-25, 70, 21);
		right.add(btnThemKH);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setBounds(310, y1, 70, 21);
		right.add(btnThem);
		btnThemKH.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btnThem.setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		String[] dsKH = {"Entry 1","Entry 2","Entry 3","Entry 4","Entry 5","Entry 6"};
		JPanel right2 = new JPanel();
		right2.setLayout(null);
		right2.setBounds(0, 0, 500, 133);
		list2 = new JList(dsKH);
		list2.setVisibleRowCount(5);
		JScrollPane scrollPane_1 = new JScrollPane(list2);
		JLayeredPane layeredPane2 = new  JLayeredPane();
		layeredPane2.setBounds(0, 0, 600, 500);
		layeredPane2.add(scrollPane_1, JLayeredPane.DEFAULT_LAYER);
        layeredPane2.add(right2, JLayeredPane.PALETTE_LAYER);
        layeredPane2.setBounds(0, y1+=y2, 500, 291);
		scrollPane_1.setBounds(0, 0, 300, 137);
		right.add(layeredPane2);
		scrollPane_1.setVisible(false);
	
		layeredPane2.add(right2);
		
		JLabel lblNewLabel_3 = new JLabel("Tổng tiền hàng");
		lblNewLabel_3.setBounds(0, 18, 91, 15);
		right2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tổng giảm giá");
		lblNewLabel_4.setBounds(0, 41, 91, 15);
		right2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Khách cần trả");
		lblNewLabel_5.setBounds(0, 64, 91, 15);
		right2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Khách thanh toán");
		lblNewLabel_6.setBounds(0, 87, 107, 15);
		right2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tiền thừa trả khách");
		lblNewLabel_7.setBounds(0, 110, 125, 15);
		right2.add(lblNewLabel_7);
		
		txtTongTienHang = new JTextField();
		txtTongTienHang.setBounds(150, 15, 230, 19);
		right2.add(txtTongTienHang);
		txtTongTienHang.setColumns(10);
		txtTongTienHang.setEditable(false);
		
		txtTongGiamGia = new JTextField();
		txtTongGiamGia.setBounds(150, 38, 230, 19);
		right2.add(txtTongGiamGia);
		txtTongGiamGia.setColumns(10);
		txtTongGiamGia.setEditable(false);
		
		txtKhachCanTra = new JTextField();
		txtKhachCanTra.setBounds(150, 61, 230, 19);
		right2.add(txtKhachCanTra);
		txtKhachCanTra.setColumns(10);
		txtKhachCanTra.setEditable(false);
		
		txtKhachThanhToan = new JTextField();
		txtKhachThanhToan.setBounds(150, 84, 230, 19);
		right2.add(txtKhachThanhToan);
		txtKhachThanhToan.setColumns(10);
		
		txtTienThua = new JTextField();
		txtTienThua.setBounds(150, 107, 230, 19);
		right2.add(txtTienThua);
		txtTienThua.setColumns(10);
		txtTienThua.setEditable(false);
		
		JLabel lblNewLabel_12 = new JLabel("Phương thức thanh toán");
		lblNewLabel_12.setBounds(0, y1+=150, 141, 13);
		right.add(lblNewLabel_12);
		
		JRadioButton tienMat = new JRadioButton("Tiền mặt");
		tienMat.setBounds(0, y1+=y2, 75, 21);
		right.add(tienMat);
		
		JRadioButton nganHang = new JRadioButton("Ngân hàng");
		nganHang.setBounds(130, y1, 104, 21);
		right.add(nganHang);
		
		JRadioButton viDienTu = new JRadioButton("Ví điện tử");
		viDienTu.setBounds(265, y1, 91, 21);
		right.add(viDienTu);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(tienMat);
		buttonGroup.add(nganHang);
		buttonGroup.add(viDienTu);
		
		JPanel pnlTienMat = new JPanel();
		right.add(pnlTienMat);
		pnlTienMat.setBounds(0,y1+=y2+20,380,100);
		pnlTienMat.add(btn10k = new JButton("10.000"));
		pnlTienMat.add(btn20k = new JButton("20.000"));
		pnlTienMat.add(btn50k = new JButton("50.000"));
		pnlTienMat.add(btn100k = new JButton("100.000"));
		pnlTienMat.add(btn200k = new JButton("200.000"));
		pnlTienMat.add(btn500k = new JButton("500.000"));
		btn10k.setPreferredSize(new Dimension(100,30));
		btn20k.setPreferredSize(new Dimension(100,30));
		btn50k.setPreferredSize(new Dimension(100,30));
		btn100k.setPreferredSize(new Dimension(100,30));
		btn200k.setPreferredSize(new Dimension(100,30));
		btn500k.setPreferredSize(new Dimension(100,30));
		
		btn10k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn20k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn50k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn100k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn200k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn500k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		
		btn10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("10000");
			}
		});
		btn20k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("20000");
			}
		});
		btn50k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("50000");
			}
		});
		btn100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("100000");
			}
		});
		btn200k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("200000");
			}
		});
		btn500k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKhachThanhToan.setText("500000");
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 322, 283, 69);
		
		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setBounds(0, y1+=y2+80, 45, 13);
		right.add(lblGhiChu);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, y1+=y2, 380, 100);
		right.add(scrollPane);
		
		JButton btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThanhToan.setBounds(0, y1+130, 380, 60);
		right.add(btnThanhToan);
		btnThanhToan.setFont(font);
		btnThanhToan.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnThanhToan.setForeground(Color.decode(ColorConsts.ForegroundColor));

		
		return contentPane;
	}
	

	@Override
	protected JPanel onCreateHeader() {
		return new Header()
				.addTitle("Đơn hàng")
				.createView();
	}
}
