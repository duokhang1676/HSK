package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

import com.github.lgooddatepicker.components.DatePicker;

import components.ColorConsts;
import dao.NhanVienDao;
import dao.QuayDao;
import entity.NhanVien;
import entity.Quay;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextField;

public class CreateEmployeeFrm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btn_them;
	private JButton btn_xoaTrang;
	private JPanel header;
	private JPanel center;
	private JTextField txtTen;
	private JTextField txtSdt;
	private JTextField txtMk;
	private DatePicker datePicker;

	private NhanVienDao nhanVienDao = new NhanVienDao();
	private QuayDao quayDao = new QuayDao();
	
	private JComboBox cbCa;
	private JComboBox cbCv;
	private DefaultComboBoxModel cbModelQuay;
	private ArrayList<Quay> ds;
	private JComboBox comboBox;

	public CreateEmployeeFrm() {
		setTitle("Thêm nhân viên");
		setSize(900,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		builUI();	
	}
	public void builUI() {
		int width = 250, height = 40;
		JPanel jp_button = new JPanel();
		getContentPane().add(jp_button,BorderLayout.SOUTH);
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
		
		header = new JPanel();
		add(header, BorderLayout.NORTH);
		
		Font font1 = new Font("Arial", Font.BOLD, 26);
		Font font2 = new Font("Arial", Font.BOLD, 18);
		Font font3 = new Font("Arial", Font.PLAIN, 16);
		JLabel title, lblTen, lblNgay, lblCa, lblSdt, lblMk, lblMaquay, lblChucvu, lblEmpty;
		
		
		title = new JLabel("THÊM NHÂN VIÊN");
		title.setFont(font1);
		title.setForeground(Color.decode(ColorConsts.PrimaryColor));
		header.add(title);
		
		center = new JPanel();
		center.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
		add(center);
		center.add(lblTen = new JLabel("Tên nhân viên"));
		lblTen.setFont(font2);
		lblTen.setPreferredSize(new Dimension(150,25));
		center.add(txtTen = new JTextField(50));
		txtTen.setPreferredSize(new Dimension(0,25));
		txtTen.setFont(font3);
		
		center.add(lblNgay = new JLabel("Ngày vào làm"));
		lblNgay.setFont(font2);
		lblNgay.setPreferredSize(lblTen.getPreferredSize());
		datePicker = new DatePicker();
		datePicker.setDateToToday();
		datePicker.setPreferredSize(new Dimension(209,25));
		datePicker.setFont(font3);
		
		JPanel pnl2 = new JPanel();
		pnl2.setPreferredSize(new Dimension(txtTen.getPreferredSize().width,30));
		center.add(pnl2);
		pnl2.add(datePicker);
		
		pnl2.add(lblEmpty = new JLabel());
		
		lblEmpty.setPreferredSize(new Dimension(95,0));
		
		pnl2.add(lblCa = new JLabel("Ca làm việc"));
		lblCa.setFont(font2);
		lblCa.setPreferredSize(lblTen.getPreferredSize());
		String listCa[] = "Sáng;Trưa;Chiều;Tối".split(";");
		cbCa = new JComboBox(listCa);
		cbCa.setPreferredSize(new Dimension(180,25));
		cbCa.setFont(font3);
		pnl2.add(cbCa);
		
		center.add(lblSdt = new JLabel("Số điện thoại"));
		lblSdt.setFont(font2);
		lblSdt.setPreferredSize(lblTen.getPreferredSize());
		center.add(txtSdt = new JTextField(50));
		txtSdt.setFont(font3);
		
		center.add(lblMk = new JLabel("Mật khẩu"));
		lblMk.setFont(font2);
		lblMk.setPreferredSize(lblTen.getPreferredSize());
		center.add(txtMk = new JTextField(50));
		txtMk.setFont(font3);
		
		center.add(lblMaquay = new JLabel("Tên quầy"));
		lblMaquay.setFont(font2);
		lblMaquay.setPreferredSize(lblTen.getPreferredSize());
		
		JPanel pnl3 = new JPanel();
		pnl3.setPreferredSize(new Dimension(txtTen.getPreferredSize().width,30));
		center.add(pnl3);
		
		comboBox = new JComboBox<>();
		cbModelQuay = new DefaultComboBoxModel<>();
        comboBox.setModel(cbModelQuay);
        addQuayToComboBox();
        comboBox.setPreferredSize(datePicker.getPreferredSize());
        comboBox.setFont(font3);
		pnl3.add(comboBox);
		
		JLabel lblEmpty2;
		pnl3.add(lblEmpty2 = new JLabel());
		lblEmpty2.setPreferredSize(new Dimension(95,0));
		
		pnl3.add(lblChucvu = new JLabel("Chức vụ"));
		lblChucvu.setFont(font2);
		lblChucvu.setPreferredSize(lblTen.getPreferredSize());
		String listChucvu[] = "Quản lý;Nhân viên".split(";");
		cbCv = new JComboBox(listChucvu);
		cbCv.setPreferredSize(new Dimension(180,25));
		cbCv.setFont(font3);
		pnl3.add(cbCv);
		
		add(center);
		btn_them.addActionListener(this);
		btn_xoaTrang.addActionListener(this);
	}

	public void addQuayToComboBox() {
		ds = quayDao.getAllData();
		for (Quay q : ds) {
			cbModelQuay.addElement(q.getTenQuay());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_xoaTrang)) {
			txtMk.setText("");
			txtSdt.setText("");
			txtTen.setText("");
			txtTen.requestFocus();
		}if(o.equals(btn_them)) {
			String tenNhanVien = txtTen.getText().trim();
			LocalDate ngayVaoLam = datePicker.getDate();
			String caLamViec = cbCa.getSelectedItem().toString();
			String soDienThoai = txtSdt.getText().trim();
			String matKhau = txtMk.getText().trim();
			int maQuay = ds.get(comboBox.getSelectedIndex()).getMaQuay();
			String chucVu = cbCv.getSelectedItem().toString();
			
			NhanVien nv = new NhanVien(tenNhanVien, ngayVaoLam, caLamViec, soDienThoai, matKhau, new Quay(maQuay), chucVu);
			nhanVienDao.addNhanVien(nv);
			setVisible(false);
		}
	}
}
