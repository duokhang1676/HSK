package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import components.ColorConsts;
import components.Header;
import components.TaiKhoanDangNhap;
import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.KhoDao;
import dao.MaGiamGiaDao;
import dao.NhanVienDao;
import dao.ThuocDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.Kho;
import entity.MaGiamGia;
import entity.NhanVien;
import entity.Quay;
import entity.Thuoc;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CreateBillFrm extends JFrame {
	private JPanel contentPane;
	private DefaultTableModel orderTableModel;
	private JTable tb;
	private JTextField txtSDT;
	private JTextField txtTim;
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
	private JList list;

	
	private KhoDao khoDao;
	private ThuocDao thuocDao;
	private NhanVienDao nhanVienDao;
	private KhachHangDao khachHangDao;
	private HoaDonDao hoaDonDao;
	private ChiTietHoaDonDao chiTietHoaDonDao;
	private MaGiamGiaDao maGiamGiaDao;

	private List<String> tenThuoc = new ArrayList<String>();
	private JLabel gioLapHD;
	private JLabel ngayLapHD;

	private LocalDateTime thoiGianHienHanh;
	private JScrollPane khPane;
	private JTextField txtSDTNhap;

	private ArrayList<Thuoc> dsThuoc;
	private ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
	private ArrayList<Integer> dsSoLuong = new ArrayList<Integer>();
	private ArrayList<Thuoc> dsThuocTemp = new ArrayList<Thuoc>();
	private ArrayList<KhachHang> dsKhachHangTemp = new ArrayList<KhachHang>();
	private ArrayList<Integer> dsMaThuoc = new ArrayList<Integer>();
	private JButton btnTang;
	private JButton btnGiam;
	private JButton btnXoa;
	private ArrayList<KhachHang> dsKhachHang;
	private JButton btnXoaTatCa;
	private JRadioButton tienMat;
	private JRadioButton nganHang;
	private JRadioButton viDienTu;
	private boolean temp;
	private JLabel lblTK;
	private JLabel lblQuay;
	
	private boolean isUserInteraction = true;
	private JTextField txtSoLuong;
	private JTextArea textArea;
	
	public CreateBillFrm() {
		super();
		this.setTitle("Tạo đơn hàng");
		this.setSize(1250, 750);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		build();
		
		khoDao = new KhoDao();
		thuocDao = new ThuocDao();
		khachHangDao = new KhachHangDao();
		hoaDonDao = new HoaDonDao();
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		nhanVienDao = new NhanVienDao();
		maGiamGiaDao = new MaGiamGiaDao();
		docDuLieuTuDB();
		getKhachHang();
		capNhatNgayGio();
	}

	private void capNhatNgayGio() {
		thoiGianHienHanh = LocalDateTime.now();
		String gio = String.valueOf(thoiGianHienHanh.getHour());
		String phut = String.valueOf(thoiGianHienHanh.getMinute());
		gioLapHD.setText(gio + ":" + phut);
		LocalDate now = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ngayLapHD.setText(dtf.format(now));
	}

	public void build() {
		Font font = new Font("Arial", Font.BOLD, 16);

		contentPane = new JPanel();
		add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		int w = 800, h = 650;
		JPanel left = new JPanel();
		left.setLayout(null);
		left.setBounds(10, 0, w, h + 50);
		contentPane.add(left);

		JLabel lblTimThuoc = new JLabel("Tìm kiếm thuốc");
		lblTimThuoc.setBounds(0, 10, 200, 19);
		left.add(lblTimThuoc);
		lblTimThuoc.setFont(font);

		txtTim = new JTextField();
		txtTim.setBounds(135, 10, 480, 26);
		txtTim.setColumns(10);
		left.add(txtTim);

		JButton btnTaoDonHang = new JButton("(+) Tạo đơn hàng");
		btnTaoDonHang.setBounds(630, 10, 170, 25);
		left.add(btnTaoDonHang);
		btnTaoDonHang.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnTaoDonHang.setForeground(Color.decode(ColorConsts.ForegroundColor));

		btnTaoDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		       new CreateBillFrm().setVisible(true);
			}
		});

		String header[] = "STT;Tên hàng;Đơn vị tính;Số lượng;Giá bán;Giảm giá (%);Thành tiền".split(";");
		orderTableModel = new DefaultTableModel(header, 0);
		tb = new JTable(orderTableModel);
		setCellEditable();
		JScrollPane croll = new JScrollPane(tb);
		croll.setBounds(0, 0, w, h - 50);
		
		//Xóa dòng trên table bằng phím delete
		tb.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
        tb.getActionMap().put("deleteRow", (Action) new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	int i = tb.getSelectedRow();
				if (i != -1) {
					orderTableModel.removeRow(i);
					capNhatSTT();
					capNhapSoLuong();
					dsMaThuoc.remove(i);
				} else {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							"Chọn sản phẩm muốn xóa!");
					return;
				}
            }
        });

		TableColumnModel columnModel = tb.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(230);
		columnModel.getColumn(4).setPreferredWidth(110);
		columnModel.getColumn(5).setPreferredWidth(110);
		columnModel.getColumn(6).setPreferredWidth(110);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setPreferredSize(new Dimension(100, 100));
		JScrollPane listPane = new JScrollPane(list);
		listPane.setBounds(135, 0, 480, 300);
		listPane.setVisible(false);
		list.setFont(new Font("Arial", Font.BOLD, 16));
		txtTim.setFont(new Font("arial", Font.ITALIC, 16));
		// Đóng
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtSDT.getText().trim().isEmpty())
					txtSDT.setText("Nhập số điện thoại...");
				listPane.setVisible(false);
				khPane.setVisible(false);
				for (KhachHang kh : dsKhachHang) {
					if (txtSDT.getText().trim().equals(kh.getSoDienThoai()))
						return;
				}
				txtTenKH.setText("");
			}
		});
		// Thêm thuốc khi double click vào jlist
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2 && !e.isConsumed()) {
					int i = list.getSelectedIndex();
					if (!list.getSelectedValue().toString().equals("Không tìm thấy sản phẩm :(")) {
						if (!dsMaThuoc.contains(dsThuocTemp.get(i).getMaThuoc()))// Không thêm trùng
						{
							if(!kiemTraTonKho(dsThuocTemp.get(i).getMaThuoc(), 1)) {
								JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
										dsThuocTemp.get(i).getTenThuoc() +" đã hết hàng!");
								return;
							}
							dsSoLuong.add(1);
							double phanTramGiamGia = 0;
							MaGiamGia mgg = maGiamGiaDao.getMaGiamGiaTheoMaThuoc(dsThuocTemp.get(i).getMaThuoc());
							if(mgg!=null)
								phanTramGiamGia = mgg.getPhanTramGiamGia();
							dsMaThuoc.add(dsThuocTemp.get(i).getMaThuoc());
							orderTableModel.addRow(new Object[] { 0, dsThuocTemp.get(i).getTenThuoc(),
									dsThuocTemp.get(i).getDonViTinh(), 1,
									dsThuocTemp.get(i).getGiaBanLe(), phanTramGiamGia,//giảm giá
									0 });
							capNhatSTT();
							capNhatThanhTien();
						} // Khi thêm trùng thuốc thì số lượng tăng thêm 1
						else {
							int index = dsMaThuoc.indexOf(dsThuocTemp.get(i).getMaThuoc());
							if(!kiemTraTonKho(dsThuocTemp.get(i).getMaThuoc(), dsSoLuong.get(index)+1)) {
								JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
										dsThuocTemp.get(i).getTenThuoc()+ " không đủ số lượng!");
								return;
							}
							dsSoLuong.set(index, dsSoLuong.get(index) + 1);
							orderTableModel.setValueAt(dsSoLuong.get(index), index, 3);
							Double giaBan = Double.valueOf(orderTableModel.getValueAt(index, 4).toString());
							Double giamGia = Double.valueOf(orderTableModel.getValueAt(index, 5).toString());
							double thanhTien2 = giaBan * (dsSoLuong.get(index)) * ((100 - giamGia) / 100);
							orderTableModel.setValueAt(thanhTien2, index, 6);
//							//chổ này fix 3 tiếng :))
						}
						capNhatTongTienHang();
					}
					e.consume();
					listPane.setVisible(false);
					// handle double click event.
					txtTim.requestFocus();
					txtTim.setText("");
				}
				capNhatThanhTien();
			}
		});
		// ---------
		txtTim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listPane.setVisible(true);
				listModel.removeAllElements();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (matchesTxt(dsThuoc.get(i).getTenThuoc().toLowerCase(), txtTim.getText().trim().toLowerCase())||(dsThuoc.get(i).getMaThuoc()+"").equals(txtTim.getText()))
						listModel.addElement(dsThuoc.get(i).getTenThuoc() + "  -  " + dsThuoc.get(i).getDonViTinh());
				}
				if (txtTim.getText().trim().equals("")) {
					listModel.removeAllElements();
					listPane.setVisible(false);
				}
				if (txtTim.getText().length() > 0 && listModel.getSize() == 0) {
					listModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
		});
		txtTim.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				listPane.setVisible(true);
				listModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (matchesTxt(dsThuoc.get(i).getTenThuoc().toLowerCase(), txtTim.getText().trim().toLowerCase())||(dsThuoc.get(i).getMaThuoc()+"").equals(txtTim.getText())) {
						listModel.addElement(dsThuoc.get(i).getTenThuoc() + "  -  " + dsThuoc.get(i).getDonViTinh());
						dsThuocTemp.add(dsThuoc.get(i));
					}

				}
				if (txtTim.getText().trim().equals("")) {
					listModel.removeAllElements();
					listPane.setVisible(false);
				}
				if (txtTim.getText().length() > 0 && listModel.getSize() == 0) {
					listModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				listPane.setVisible(true);
				listModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (matchesTxt(dsThuoc.get(i).getTenThuoc().toLowerCase(), txtTim.getText().trim().toLowerCase())||(dsThuoc.get(i).getMaThuoc()+"").equals(txtTim.getText())) {
						listModel.addElement(dsThuoc.get(i).getTenThuoc() + "  -  " + dsThuoc.get(i).getDonViTinh());
						dsThuocTemp.add(dsThuoc.get(i));
					}
				}
				if (txtTim.getText().trim().equals("")) {
					listModel.removeAllElements();
					listPane.setVisible(false);
				}
				if (txtTim.getText().length() > 0 && listModel.getSize() == 0) {
					listModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		// --------------------
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 39, w, h - 50);
		layeredPane.add(croll, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(listPane, JLayeredPane.PALETTE_LAYER);
		left.add(layeredPane);
		JPanel footer = new JPanel();
		footer.setLayout(new GridLayout(1, 4));
		left.add(footer);
		footer.setBounds(0, 645, 800, 50);
		
		JLabel lblSoLuong;
		footer.add(lblSoLuong = new JLabel("Nhập số lượng"));
		lblSoLuong.setFont(font);
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		
		footer.add(txtSoLuong = new JTextField());
		((AbstractDocument) txtSoLuong.getDocument()).setDocumentFilter(new NumberOnlyFilter());
		txtSoLuong.setFont(new Font("Arial", Font.BOLD, 25));
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if(txtSoLuong.getText().trim().equals(""))return;
				int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
				if (i != -1) {
				int sl = Integer.parseInt(orderTableModel.getValueAt(i, 3).toString());
				if(!kiemTraTonKho(dsMaThuoc.get(i), soLuong)) {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							orderTableModel.getValueAt(i, 1)+ " không đủ số lượng!"); 
					return;
				}
					orderTableModel.setValueAt(soLuong, i, 3);
					txtSoLuong.setText("");
					capNhapSoLuong();
					capNhatThanhTien();
				} else {
					return;
				}
			}
		});

		footer.add(btnTang = new JButton("Tăng"));
		btnTang.setFont(font);
		btnTang.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnTang.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnTang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if (i != -1) {
				int sl = Integer.parseInt(orderTableModel.getValueAt(i, 3).toString());
				if(!kiemTraTonKho(dsMaThuoc.get(i), sl+1)) {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							orderTableModel.getValueAt(i, 1)+ " không đủ số lượng!"); 
					return;
				}
					orderTableModel.setValueAt(sl + 1, i, 3);
					capNhapSoLuong();
					capNhatThanhTien();
				} 
				else {
//					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
//							"Chọn sản phẩm muốn tăng số lượng!");
					return;
				}
			}
		});
		footer.add(btnGiam = new JButton("Giảm"));
		btnGiam.setFont(font);
		btnGiam.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnGiam.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnGiam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if (i != -1) {
					int sl = Integer.parseInt(orderTableModel.getValueAt(i, 3).toString());
					if (sl <= 1)
						return;
					orderTableModel.setValueAt(sl - 1, i, 3);
					capNhapSoLuong();
					capNhatThanhTien();
				} else {
//					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
//							"Chọn sản phẩm muốn giảm số lượng!");
					return;
				}
			}
		});
		footer.add(btnXoa = new JButton("Xoá"));
		btnXoa.setFont(font);
		btnXoa.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnXoa.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = tb.getSelectedRow();
				if (i != -1) {
					orderTableModel.removeRow(i);
					capNhatSTT();
					capNhapSoLuong();
					dsMaThuoc.remove(i);
				} else {
//					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
//							"Chọn sản phẩm muốn xóa!");
					return;
				}
			}
		});
		footer.add(btnXoaTatCa = new JButton("Xoá tất cả"));
		btnXoaTatCa.setFont(font);
		btnXoaTatCa.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnXoaTatCa.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnXoaTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderTableModel.getRowCount()==0)
					return;
				DefaultTableModel model = (DefaultTableModel) tb.getModel();
				model.setRowCount(0); 
				dsMaThuoc.clear();
				capNhapSoLuong();
			}
		});

		// --------------------------------------------

		int x2 = 820, x3 = 100, y1 = 10, y2 = 25, w2 = 500, h2 = 30;
		JPanel right = new JPanel();
		right.setBounds(x2, 0, 600, 700);
		contentPane.add(right);
		right.setLayout(null);

		JLabel lblDonHang = new JLabel("Thông tin đơn hàng");
		lblDonHang.setBounds(0, y1, 150, h2);
		right.add(lblDonHang);
		lblDonHang.setFont(font);

		lblQuay = new JLabel("");
		lblQuay.setBounds(315, y1, 80, h2);
		right.add(lblQuay);

		lblTK = new JLabel("");
		lblTK.setBounds(0, y1 += y2 + 10, 200, 13);
		right.add(lblTK);
		
		setNVandQuay();

		ngayLapHD = new JLabel("05/11/2023");
		ngayLapHD.setBounds(315, y1, 60, 13);
		right.add(ngayLapHD);

		gioLapHD = new JLabel("12:30");
		gioLapHD.setBounds(270, y1, 40, 13);
		right.add(gioLapHD);

		JLabel lblTimKH = new JLabel("Tìm khách hàng (SDT)");
		lblTimKH.setBounds(0, y1 += y2, 125, 13);
		right.add(lblTimKH);

		txtSDT = new JTextField("Nhập số điện thoại...");
		txtSDT.setBounds(0, y1 += y2, 300, 20);
		txtKhachThanhToan = new JTextField("");
		 ((AbstractDocument) txtSDT.getDocument()).setDocumentFilter(new NumberOnlyFilter());
		right.add(txtSDT);

		// -----------------
		txtSDTNhap = new JTextField();
		txtSDTNhap.setBounds(0, y1, 300, 20);
		right.add(txtSDTNhap);
		txtSDTNhap.setColumns(10);
		txtSDTNhap.setVisible(false);
		txtKhachThanhToan = new JTextField("");
		
		 ((AbstractDocument) txtSDTNhap.getDocument()).setDocumentFilter(new NumberOnlyFilter());
		// -------------

		txtTenKH = new JTextField();
		y1 += y2;
		txtTenKH.setEditable(false);

		temp = false;
		JButton btnThemKH = new JButton("Thêm");
		btnThemKH.setBounds(310, y1 - 25, 70, 21);
		right.add(btnThemKH);

		JButton btnThem = new JButton("OK");
		btnThem.setVisible(false);
		btnThemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!temp) {
					btnThemKH.setText("Tìm");
					txtTenKH.setText("");
					txtTenKH.requestFocus();
					txtTenKH.setEditable(true);
					txtSDT.setVisible(false);
					txtSDTNhap.setVisible(true);
					khPane.setVisible(false);
					if (!txtSDT.getText().equals("Nhập số điện thoại..."))
						txtSDTNhap.setText(txtSDT.getText());
					else
						txtSDTNhap.setText("");
					btnThemKH.setText("Tìm");
					btnThem.setVisible(true);
				}else {
					txtTenKH.setText("");
					btnThemKH.setText("Thêm");
					txtTenKH.setEditable(false);
					txtSDTNhap.setVisible(false);
					txtSDT.setVisible(true);
					btnThem.setVisible(false);
				}
				
				temp = !temp;
			}
		});
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTenKH.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							"Tên khách hàng không được rỗng!");
					return;
				}
				if(txtSDTNhap.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							"Số điện thoại không được rỗng!");
					return;
				}
				txtTenKH.setEditable(false);
				txtSDTNhap.setVisible(false);
				txtSDT.setVisible(true);
				txtSDT.setText(txtSDTNhap.getText());
				khPane.setVisible(false);
				String tenKhachHang = txtTenKH.getText().trim();
				String sdtKhachHang = txtSDTNhap.getText().trim();
				KhachHang khachHang = new KhachHang(tenKhachHang, sdtKhachHang, LocalDate.now());
				if (khachHangDao.themKhachHang(khachHang))
					JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
							"Thêm khách hàng thành công!");
				docDuLieuTuDB();
			}
		});

		btnThem.setBounds(310, y1, 70, 21);
		right.add(btnThem);
		btnThemKH.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btnThem.setBackground(Color.decode(ColorConsts.ForegroundColor));

		DefaultListModel<String> listModelKH = new DefaultListModel<>();
		JPanel right2 = new JPanel();
		right2.setLayout(null);
		right2.setBounds(0, 0, 500, 155);
		list2 = new JList(listModelKH);
		khPane = new JScrollPane(list2);
		JLayeredPane layeredPane2 = new JLayeredPane();
		layeredPane2.setBounds(0, 0, 600, 500);
		layeredPane2.add(khPane, JLayeredPane.DEFAULT_LAYER);
		layeredPane2.add(right2, JLayeredPane.PALETTE_LAYER);
		layeredPane2.setBounds(0, y1, 500, 291);
		khPane.setBounds(0, 0, 300, 137);
		right.add(layeredPane2);
		khPane.setVisible(false);

		layeredPane2.add(right2);

		// ------------------
		list2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					khPane.setVisible(false);
					if (listModelKH.getElementAt(0).toString().equals("Không tìm thấy khách hàng :(")) {
						txtTenKH.setText("");
						return;
					}

					int i = list2.getSelectedIndex();
					if (i == -1) {
						txtTenKH.setText("");
						return;
					}
					txtTenKH.setText(listModelKH.getElementAt(i));// Để thằng này ở dưới thì bị lỗi ???
					txtSDT.setText(dsKhachHangTemp.get(i).getSoDienThoai().toString());
					khPane.setVisible(false);
				}
			}
		});
		// ---------
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSDT.setText("");
				listModelKH.removeAllElements();
				dsKhachHangTemp.clear();
				for (int i = 0; i < dsKhachHang.size(); i++) {
					if (dsKhachHang.get(i).getSoDienThoai().contains(txtSDT.getText().trim())) {
						listModelKH.addElement(dsKhachHang.get(i).getTenKhachHang());
						dsKhachHangTemp.add(dsKhachHang.get(i));
					}
				}
				if (txtSDT.getText().trim().equals("")) {
					listModelKH.removeAllElements();
					khPane.setVisible(false);
				}
			}
		});
		txtSDT.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				khPane.setVisible(true);
				listModelKH.removeAllElements();
				dsKhachHangTemp.clear();
				for (int i = 0; i < dsKhachHang.size(); i++) {
					if (dsKhachHang.get(i).getSoDienThoai().contains(txtSDT.getText().trim())) {
						listModelKH.addElement(dsKhachHang.get(i).getTenKhachHang());
						dsKhachHangTemp.add(dsKhachHang.get(i));
					}
				}
				if (txtSDT.getText().trim().equals("")) {
					listModelKH.removeAllElements();
					khPane.setVisible(false);
				}
				if (txtSDT.getText().length() > 0 && listModelKH.getSize() == 0) {
					listModelKH.addElement("Không tìm thấy khách hàng :(");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				khPane.setVisible(true);
				listModelKH.removeAllElements();
				dsKhachHangTemp.clear();
				for (int i = 0; i < dsKhachHang.size(); i++) {
					if (dsKhachHang.get(i).getSoDienThoai().contains(txtSDT.getText().trim())) {
						listModelKH.addElement(dsKhachHang.get(i).getTenKhachHang());
						dsKhachHangTemp.add(dsKhachHang.get(i));
					}

				}
				if (txtSDT.getText().trim().equals("")) {
					listModelKH.removeAllElements();
					khPane.setVisible(false);
				}
				if (txtSDT.getText().trim().length() > 0 && listModelKH.getSize() == 0) {
					listModelKH.addElement("Không tìm thấy khách hàng :(");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		// ---------------------
		right2.add(txtTenKH);
		txtTenKH.setBounds(0, y2 - 25, 300, 20);
		JLabel lblNewLabel_3 = new JLabel("Tổng tiền hàng");
		lblNewLabel_3.setBounds(0, 18 + y2, 91, 15);
		right2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tổng giảm giá");
		lblNewLabel_4.setBounds(0, 41 + y2, 91, 15);
		right2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Khách cần trả");
		lblNewLabel_5.setBounds(0, 64 + y2, 91, 15);
		right2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Khách thanh toán");
		lblNewLabel_6.setBounds(0, 87 + y2, 107, 15);
		right2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Tiền thừa trả khách");
		lblNewLabel_7.setBounds(0, 110 + y2, 125, 15);
		right2.add(lblNewLabel_7);

		txtTongTienHang = new JTextField("0");
		txtTongTienHang.setBounds(150, 15 + y2, 230, 19);
		right2.add(txtTongTienHang);
		txtTongTienHang.setColumns(10);
		txtTongTienHang.setEditable(false);

		txtTongGiamGia = new JTextField("0");
		txtTongGiamGia.setBounds(150, 38 + y2, 230, 19);
		right2.add(txtTongGiamGia);
		txtTongGiamGia.setColumns(10);
		txtTongGiamGia.setEditable(false);

		txtKhachCanTra = new JTextField("0");
		txtKhachCanTra.setBounds(150, 61 + y2, 230, 19);
		right2.add(txtKhachCanTra);
		txtKhachCanTra.setColumns(10);
		txtKhachCanTra.setEditable(false);

		txtKhachThanhToan = new JTextField("");
		
		 ((AbstractDocument) txtKhachThanhToan.getDocument()).setDocumentFilter(new NumberOnlyFilter());
		txtKhachThanhToan.setBounds(150, 84 + y2, 230, 19);
		right2.add(txtKhachThanhToan);
		txtKhachThanhToan.setColumns(10);
		txtKhachThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				capNhatTienThuaTraKhach();
			}
		});
		
		txtKhachThanhToan.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				capNhatTienThuaTraKhach();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				capNhatTienThuaTraKhach();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				capNhatTienThuaTraKhach();
			}
		});

		txtTienThua = new JTextField("0");
		txtTienThua.setBounds(150, 107 + y2, 230, 19);
		right2.add(txtTienThua);
		txtTienThua.setColumns(10);
		txtTienThua.setEditable(false);

		JLabel lblNewLabel_12 = new JLabel("Phương thức thanh toán");
		lblNewLabel_12.setBounds(0, y1 += 150 + y2, 141, 13);
		right.add(lblNewLabel_12);

		tienMat = new JRadioButton("Tiền mặt");
		tienMat.setBounds(0, y1 += y2, 75, 21);
		right.add(tienMat);

		nganHang = new JRadioButton("Ngân hàng");
		nganHang.setBounds(130, y1, 104, 21);
		right.add(nganHang);

		viDienTu = new JRadioButton("Ví điện tử");
		viDienTu.setBounds(265, y1, 91, 21);
		right.add(viDienTu);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(tienMat);
		buttonGroup.add(nganHang);
		buttonGroup.add(viDienTu);
		tienMat.setSelected(true);
		
		
		 ItemListener itemListener = new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	                if (tienMat.isSelected()) {
	                    txtKhachThanhToan.setEditable(true);
	                    ((AbstractDocument) txtKhachThanhToan.getDocument()).setDocumentFilter(new NumberOnlyFilter());
	                }
	                if (nganHang.isSelected()) {
	                	((AbstractDocument) txtKhachThanhToan.getDocument()).setDocumentFilter(null);
	                	txtKhachThanhToan.setText(txtKhachCanTra.getText());
	                	capNhatTienThuaTraKhach();
	                	txtKhachThanhToan.setEditable(false);
	                   
	                }
	                if (viDienTu.isSelected()) {
	                	((AbstractDocument) txtKhachThanhToan.getDocument()).setDocumentFilter(null);
	                	txtKhachThanhToan.setText(txtKhachCanTra.getText());
	                	capNhatTienThuaTraKhach();
	                	txtKhachThanhToan.setEditable(false);
	                }
	            }
	        };
	        
	        tienMat.addItemListener(itemListener);
	        nganHang.addItemListener(itemListener);
	        viDienTu.addItemListener(itemListener);
		JPanel pnlTienMat = new JPanel();
		right.add(pnlTienMat);
		pnlTienMat.setBounds(0, y1 += y2 + 10, 380, 90);
		pnlTienMat.add(btn10k = new JButton("10.000"));
		pnlTienMat.add(btn20k = new JButton("20.000"));
		pnlTienMat.add(btn50k = new JButton("50.000"));
		pnlTienMat.add(btn100k = new JButton("100.000"));
		pnlTienMat.add(btn200k = new JButton("200.000"));
		pnlTienMat.add(btn500k = new JButton("500.000"));
		btn10k.setPreferredSize(new Dimension(100, 30));
		btn20k.setPreferredSize(new Dimension(100, 30));
		btn50k.setPreferredSize(new Dimension(100, 30));
		btn100k.setPreferredSize(new Dimension(100, 30));
		btn200k.setPreferredSize(new Dimension(100, 30));
		btn500k.setPreferredSize(new Dimension(100, 30));

		btn10k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn20k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn50k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn100k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn200k.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btn500k.setBackground(Color.decode(ColorConsts.ForegroundColor));

		btn10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("10000");
					capNhatTienThuaTraKhach();
				}
			}
		});
		btn20k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("20000");
					capNhatTienThuaTraKhach();
				}
			}
		});
		btn50k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("50000");
					capNhatTienThuaTraKhach();
				}
			}
		});
		btn100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("100000");
					capNhatTienThuaTraKhach();
				}
			}
		});
		btn200k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("200000");
					capNhatTienThuaTraKhach();
				}
			}
		});
		btn500k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tienMat.isSelected()) {
					txtKhachThanhToan.setText("500000");
					capNhatTienThuaTraKhach();
				}
			}
		});

		textArea = new JTextArea();
		textArea.setBounds(10, 322, 283, 69);

		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setBounds(0, y1 += y2 + 70, 45, 13);
		right.add(lblGhiChu);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, (y1 += y2), 380, 90);
		right.add(scrollPane);
		
		JButton btnLuuTam = new JButton("LƯU TẠM");
		btnLuuTam.setBounds(0, y1 + 115, 380, 50);
		right.add(btnLuuTam);
		btnLuuTam.setFont(font);
		btnLuuTam.setBackground(Color.decode(ColorConsts.ForegroundColor));
		btnLuuTam.setForeground(Color.decode(ColorConsts.PrimaryColor));
		btnLuuTam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thanhToan(false);
			}
		});

		JButton btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.setBounds(0, y1 + 170, 380, 50);
		right.add(btnThanhToan);
		btnThanhToan.setFont(font);
		btnThanhToan.setBackground(Color.decode(ColorConsts.PrimaryColor));
		btnThanhToan.setForeground(Color.decode(ColorConsts.ForegroundColor));
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thanhToan(true);
			}
		});
		//Thanh toán bằng phím F4
		// Tạo một Action và gán chức năng khi nhấn phím F4
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	thanhToan(true);
            }
        };
        // Gắn hành động với phím tắt F4
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "performAction");
        getRootPane().getActionMap().put("performAction", action);
        
      //Tắt cửa sổ bằng phím Esc
      		// Tạo một Action và gán chức năng khi nhấn phím esc
              Action action1 = new AbstractAction() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                	  dispose();
                  }
              };
              // Gắn hành động với phím tắt Esc
              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "performAction");
              getRootPane().getActionMap().put("performAction", action1);
		

		this.add(contentPane);
	}

	public void docDuLieuTuDB() {
		dsThuoc = thuocDao.getAllData();
//		for(int i = 0;i<dsThuoc.size();i++) {
//			tenThuoc.add(dsThuoc.get(i).getTenThuoc());
//		}
	}

	public void capNhapSoLuong() {
		dsSoLuong.clear();
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			dsSoLuong.add(Integer.parseInt(orderTableModel.getValueAt(i, 3).toString()));
		}
		capNhatTongTienHang();
	}

	public void capNhatThanhTien() {
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			int sl = Integer.parseInt(orderTableModel.getValueAt(i, 3).toString());
			double donGia = Double.parseDouble(orderTableModel.getValueAt(i, 4).toString());
			double giamGia = Double.parseDouble(orderTableModel.getValueAt(i, 5).toString());

			orderTableModel.setValueAt(donGia * sl * ((100 - giamGia) / 100), i, 6);
		}
	}

	public void setCellEditable() {
		for (int i = 0; i < tb.getColumnCount(); i++) {
			tb.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
				@Override
				public boolean isCellEditable(EventObject e) {
					// Trả về false để ngăn chặn chỉnh sửa trực tiếp
					return false;
				}
			});
		}
	}

	public void getQuay() {

	}

	public void getKhachHang() {
		dsKhachHang = khachHangDao.getAllKhachHang();
	}

	public void capNhatTongTienHang() {
		double tongTienHang = 0;
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			tongTienHang += (Double.parseDouble(orderTableModel.getValueAt(i, 4).toString())
					* Integer.parseInt(orderTableModel.getValueAt(i, 3).toString()));
		}
		txtTongTienHang.setText(tongTienHang + "");
		capNhatTongGiamGia();
	}

	public void capNhatTongGiamGia() {
		double tongGiamGia = Double.parseDouble(txtTongTienHang.getText()) - tinhTongThanhTien();
		txtTongGiamGia.setText(tongGiamGia + "");
		double khachCanTra = Double.parseDouble(txtTongTienHang.getText())
				- Double.parseDouble(txtTongGiamGia.getText());
		txtKhachCanTra.setText(khachCanTra + "");
		if(!tienMat.isSelected())
			txtKhachThanhToan.setText(txtKhachCanTra.getText());
		capNhatTienThuaTraKhach();
	}
	
	public double tinhTongThanhTien() {
		double tongThanhTien = 0;
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			tongThanhTien += Double.parseDouble(orderTableModel.getValueAt(i, 6).toString());
		}
		return tongThanhTien;
	}

	public void capNhatTienThuaTraKhach() {
		if(txtKhachThanhToan.getText().isEmpty()){
			txtTienThua.setText("0");
			return;
		}
		double khachCanTra = Double.parseDouble(txtKhachCanTra.getText());
		double khachThanhToan = Double.parseDouble(txtKhachThanhToan.getText());
		
		if(khachThanhToan<khachCanTra) {
			txtTienThua.setText("0");
			return;
		}
			
		if (txtKhachCanTra.getText().isEmpty())
			return;
		double tienThuaTraKhach = Double.parseDouble(txtKhachThanhToan.getText())
				- Double.parseDouble(txtKhachCanTra.getText());
		if (tienThuaTraKhach < 0)
			return;
		txtTienThua.setText(tienThuaTraKhach + "");
	}

	public void createChiTietHoaDon(HoaDon hoaDon) {
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			try {
				int soLuong = Integer.parseInt(orderTableModel.getValueAt(i, 3).toString());
				String donViTinh = orderTableModel.getValueAt(i, 2).toString();
				double donGia = Double.parseDouble(orderTableModel.getValueAt(i, 4).toString());
				Thuoc sanPham = new Thuoc(dsMaThuoc.get(i));
				double thue = 10;
				double thanhTien = Double.parseDouble(orderTableModel.getValueAt(i, 6).toString());
				double giamGia = Double.parseDouble(orderTableModel.getValueAt(i, 5).toString());

				// int maHoaDon = hoaDonDao.getMaHoaDonLast();

				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(0, soLuong, donViTinh, donGia, sanPham, thue, thanhTien,
						giamGia, hoaDon, 1);
				chiTietHoaDonDao.themChiTietHoaDonKhongGG(chiTietHoaDon);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	public void capNhatSTT() {
		for (int i = 0; i < orderTableModel.getRowCount(); i++) {
			orderTableModel.setValueAt(i + 1, i, 0);
		}
	}
	public boolean matchesTxt(String a, String b) {
		for(int i = 0;i<b.length();i++) {
			if(b.charAt(i)!=a.charAt(i))
				return false;
		}
		return true;
	}
	public void thanhToan(boolean tt) {
		if(orderTableModel.getRowCount()==0) {
			JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
					"Chưa có sản phẩm!");
			return;
		}
		
		if(tt) {
			if(txtKhachThanhToan.getText().isEmpty()) {
				JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
						"Tiền thanh toán không hợp lệ!");
				return;
			}	
			double khachCanTra = Double.parseDouble(txtKhachCanTra.getText());
			double khachThanhToan = Double.parseDouble(txtKhachThanhToan.getText());
			if(khachThanhToan<khachCanTra) {
				JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
						"Tiền thanh toán không hợp lệ!");
				return;
			}
		}
		
		LocalDate ngayLapHoaDon = LocalDate.now();
		String trangThai;
		if(tt) trangThai = "Đã TT";
		else trangThai = "Chưa TT";
		String phuongThucThanhToan = " ";
		if(tt) {
			if (tienMat.isSelected())
				phuongThucThanhToan = "Tiền mặt";
			else if (nganHang.isSelected())
				phuongThucThanhToan = "Ngân hàng";
			else if (viDienTu.isSelected())
				phuongThucThanhToan = "Ví điện tử";
		}
		double tienNhan = 0;
		double tienDu = 0;
		if(tt) {
			tienNhan = Double.parseDouble(txtKhachThanhToan.getText());
			tienDu = Double.parseDouble(txtTienThua.getText());
		}
		
		KhachHang khachHang = new KhachHang();
		if(txtTenKH.getText().isEmpty()) {
			khachHang = new KhachHang(1);
		}else {
			int maKhachHang = 1;
			String sdtKH = txtSDT.getText().trim();
			for (KhachHang kh : dsKhachHang) {
				if (sdtKH.equals(kh.getSoDienThoai()))
					maKhachHang = kh.getMaKhachHang();
			}
			khachHang = new KhachHang(maKhachHang);
		}
		int maNhanVien = TaiKhoanDangNhap.getNV().getMaNhanVien();
		int maQuay = TaiKhoanDangNhap.getNV().getQuay().getMaQuay();
		double tongTienGiam = Double.parseDouble(txtTongGiamGia.getText());
		double tongTien = Double.parseDouble(txtKhachCanTra.getText());
		String ghiChu = textArea.getText();

		HoaDon hoaDon = new HoaDon(0, ngayLapHoaDon, trangThai, phuongThucThanhToan, tienNhan, tienDu,
				khachHang, new NhanVien(maNhanVien), new Quay(maQuay), tongTienGiam, tongTien, ghiChu);

		hoaDon = hoaDonDao.themHoaDon(hoaDon);
		if (hoaDon != null) {
			capNhatKho();
			createChiTietHoaDon(hoaDon);
			if(tt){
				//---------------------------- Tạo hóa đơn
				String cthd = "";
				for(int i = 0;i<tb.getRowCount();i++) {
					cthd+= ("\t"+(i+1)+". "+tb.getValueAt(i, 1)+"("
							+tb.getValueAt(i, 2)+")\n"
							+"\t\t"+tb.getValueAt(i, 3)
							+"\t\t"+tb.getValueAt(i, 4)
							+"\t\t"+tb.getValueAt(i, 6)+"\n");
				}
				String content = 
						"\t\t\t NHÀ THUỐC HKTD\n"
						+ "\t\t  www.nhathuochktd.com\n"
						+ "\t\t\tQuầy: "+lblQuay.getText()+"\n"
						+ "\t-------------------------------\n"
						+ "\tPHIẾU THANH TOÁN (Bao gồm VAT)\n"
						+ "\tSố HD: ?"+""+"/ Ngày: "+ngayLapHD.getText()+"\n"
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
				OrderPage.saveFile(content, "data/HD"+hoaDon.getMaHD()+".txt");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//----------------------------
			}
			JOptionPane.showMessageDialog((RootFrame) SwingUtilities.getWindowAncestor(CreateBillFrm.this),
					"Đã tạo hóa đơn!");
			
		}
		dispose();
	}
	
	
	private static class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean test(String text) {
            return text.isEmpty() || text.matches("\\d+");
        }
    }
	private void setNVandQuay() {
		lblQuay.setText(TaiKhoanDangNhap.getNV().getQuay().getTenQuay());
		lblTK.setText(TaiKhoanDangNhap.getNV().getTenNhanVien());
	}
	private boolean kiemTraTonKho(int maThuoc, int soLuong) {
		Kho kho = khoDao.getTonKhoTheoThuoc(maThuoc);
		if(kho.getSoLuong()<soLuong)
			return false;
		return true;
	}
	private void capNhatKho() {
		int i = 0;
		for (Integer maThuoc : dsMaThuoc) {
			Kho kho = khoDao.getTonKhoTheoThuoc(maThuoc);
			int soLuong = kho.getSoLuong()-dsSoLuong.get(i);
			khoDao.updateSoLuongTonKho(maThuoc, soLuong);
			i++;
		}
	}

}
