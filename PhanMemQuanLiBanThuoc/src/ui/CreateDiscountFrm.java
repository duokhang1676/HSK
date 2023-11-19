package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import dao.MaGiamGiaDao;
import dao.ThuocDao;
import entity.MaGiamGia;
import entity.Thuoc;

public class CreateDiscountFrm extends JFrame implements ActionListener{
	private JTextField txt_maGiamGia;
	
	private DatePicker txt_ngayKetThuc;
	private JTextField txt_phanTramGiamGia;
	private JTextArea txt_moTa;
	private JButton btn_them;
	private JButton btn_xoaTrang;

	private DatePicker txt_ngayBatDau;

	private MaGiamGiaDao magiamgia_dao;

	private DefaultListModel suggestionListModel;

	private JList suggestionList;

	private JScrollPane listTimKiem;

	private JTextField txt_maThuoc;

	private ThuocDao thuoc_dao;

	private ArrayList<Thuoc> dsThuoc;
	private ArrayList<Thuoc> dsThuocTemp = new ArrayList<Thuoc>();


	@SuppressWarnings("unchecked")
	public CreateDiscountFrm() {
		// TODO Auto-generated constructor stub
		this.setTitle("Tạo mã giảm giá");
		this.setSize(600, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		thuoc_dao = new ThuocDao();
		magiamgia_dao = new MaGiamGiaDao();
		dsThuoc = thuoc_dao.getAllData();
		Font titleFont = new Font("Arial", Font.BOLD, 30);
		JLabel title = new JLabel("Thêm mã giảm giá mới");
		title.setFont(titleFont);
		/**
		 * Panel Information
		 */
		JPanel jp_info = new JPanel();
		jp_info.setLayout(new BorderLayout());
		
		JLabel jl_maGiamGia = new JLabel("Mã giảm giá: ");
		JLabel jl_ngayBatDau = new JLabel("Ngày bắt đầu: ");
		JLabel jl_ngayKetThuc = new JLabel("Ngày kết thúc: ");
		JLabel jl_moTa = new JLabel("Mô tả: ");
		JLabel jl_phanTramGiamGia = new JLabel("Phần trăm giảm giá: ");
		JLabel jl_maThuoc = new JLabel("Tên thuốc: ");
		
		jl_maGiamGia.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayBatDau.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_ngayKetThuc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_moTa.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		jl_maThuoc.setPreferredSize(jl_phanTramGiamGia.getPreferredSize());
		
		txt_maGiamGia = new JTextField();
		txt_maGiamGia.setEditable(false);
		
		txt_maThuoc = new JTextField();
		
		txt_ngayBatDau = new DatePicker();
//		txt_ngayBatDau = new JTextField();
		
		txt_ngayKetThuc = new DatePicker();
//		txt_ngayKetThuc = new JTextField();
		
		txt_phanTramGiamGia = new JTextField();
		
		txt_moTa = new JTextArea(3,2);
		txt_moTa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);
        listTimKiem = new JScrollPane(suggestionList);
		
		listTimKiem.setVisible(false);
		
		txt_maThuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_maThuoc.getText().trim().toLowerCase()))
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
				}
				if (txt_maThuoc.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_maThuoc.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
		});
		txt_maThuoc.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_maThuoc.getText().trim().toLowerCase())) {
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
						dsThuocTemp.add(dsThuoc.get(i));
					}

				}
				if (txt_maThuoc.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_maThuoc.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				listTimKiem.setVisible(true);
				suggestionListModel.removeAllElements();
				dsThuocTemp.clear();
				for (int i = 0; i < dsThuoc.size(); i++) {
					if (dsThuoc.get(i).getTenThuoc().toLowerCase().contains(txt_maThuoc.getText().trim().toLowerCase())) {
						suggestionListModel.addElement(dsThuoc.get(i).getTenThuoc());
						dsThuocTemp.add(dsThuoc.get(i));
					}
				}
				if (txt_maThuoc.getText().trim().equals("")) {
					suggestionListModel.removeAllElements();
					listTimKiem.setVisible(false);
				}
				if (txt_maThuoc.getText().length() > 0 && suggestionListModel.getSize() == 0) {
					suggestionListModel.addElement("Không tìm thấy sản phẩm :(");
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		suggestionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					txt_maThuoc.setText(suggestionList.getSelectedValue().toString());
					listTimKiem.setVisible(false);
				}
			}
		});
		
		
		
		Box b, b1, b2, b3, b4, b5;
		
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		
		b1.add(jl_maThuoc);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txt_maThuoc);
		
		b2.add(jl_ngayBatDau);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txt_ngayBatDau);

		b3.add(jl_ngayKetThuc);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txt_ngayKetThuc);
		
		b4.add(jl_phanTramGiamGia);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txt_phanTramGiamGia);
		
		b5.add(jl_moTa);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txt_moTa);
		
		b.add(b1);
		b.add(Box.createVerticalStrut(15));
		b.add(b2);
		b.add(Box.createVerticalStrut(15));
		b.add(b3);
		b.add(Box.createVerticalStrut(15));
		b.add(b4);
		b.add(Box.createVerticalStrut(15));
		b.add(b5);
		b.add(Box.createVerticalStrut(15));
		
		JLayeredPane jp_infor = new JLayeredPane();
	
		
		jp_infor.add(b, JLayeredPane.DEFAULT_LAYER);
		b.setBounds(0, 0, 550, 250);
		jp_infor.add(listTimKiem,JLayeredPane.PALETTE_LAYER);
		listTimKiem.setBounds(125, 30, 425, 100);
		
//		jp_info.add(b, BorderLayout.CENTER);
		
		int width = 130, height = 40;
		JPanel jp_button = new JPanel();
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		btn_them = new JButton("Thêm");
		btn_them.setIcon(new ImageIcon("icon\\ic_add.png"));
		btn_them.setPreferredSize(new Dimension(width, height));
		btn_them.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		btn_xoaTrang = new JButton("Xóa thông tin");
		btn_xoaTrang.setIcon(new ImageIcon("icon\\ic_clearInfo.png"));
		btn_xoaTrang.setPreferredSize(new Dimension(width, height));
		btn_xoaTrang.setBackground(Color.decode(ColorConsts.BackgroundColor));
		
		jp_button.add(btn_them);
		jp_button.add(btn_xoaTrang);
		jp_button.setBackground(Color.decode(ColorConsts.PrimaryColor));
		
		/**
		 * add event
		 */
		btn_them.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(jp_infor, BorderLayout.CENTER);
		this.add(jp_button, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btn_them)) {
			if (validData()) {
				addRow();
				dispose();
			}
		}else if (src.equals(btn_xoaTrang)) {
			clearData();
		}
	}

	private boolean validData() {
		// TODO Auto-generated method stub
		String ptGiamGia = txt_phanTramGiamGia.getText();
		String thuoc = txt_maThuoc.getText();
		// check thuoc
		if (thuoc.length() <= 0) {
			showMessage("Tên thuốc không được để trống!!!");
			return false;
		}
		// check phan tram giam gia
		if (ptGiamGia.length() > 0) {
			try {
				double ptgg = Double.parseDouble(ptGiamGia);
				if (!(ptgg > 0)) {
					showMessage("Phần trăm giảm giá phải lớn hơn 0!!!");
					txt_phanTramGiamGia.requestFocus();
					txt_phanTramGiamGia.selectAll();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				showMessage("Phần trăm giảm giá phải nhập số!!!");
				txt_phanTramGiamGia.requestFocus();
				txt_phanTramGiamGia.selectAll();
				return false;
			}
		} else {
			showMessage("Phần trăm giảm giá không được để trống!!!");
			return false;
		}
		
		return true;
	}

	private void addRow() {
		// TODO Auto-generated method stub
		LocalDate ngayBD = txt_ngayBatDau.getDate();
		LocalDate ngayKT = txt_ngayKetThuc.getDate();
		double ptGiamGia = Double.parseDouble(txt_phanTramGiamGia.getText());
		
		Thuoc thuoc = thuoc_dao.timThuocTheoTen(txt_maThuoc.getText().toString());
		String moTa = txt_moTa.getText();

		MaGiamGia mgg = new MaGiamGia( ngayBD, ngayKT, ptGiamGia, moTa, thuoc);
		try {
			magiamgia_dao.themMaGiamGia(mgg);
			showMessage("Thêm thành công");
		} catch (Exception e) {
			// TODO: handle exception
			showMessage("Thêm không thành công");
		}
	}

	private void showMessage(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, string);
	}

	private void clearData() {
		// TODO Auto-generated method stub
		txt_maGiamGia.setText("");
		txt_ngayBatDau.setText("");
		txt_ngayKetThuc.setText("");
		txt_phanTramGiamGia.setText("");
		txt_moTa.setText("");
	}
}
