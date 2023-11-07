package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.ColorConsts;
import components.Header;

public class EmployeeManagerPage extends BasePage {

	private JTextField maNvTextField;
	private JTextField hoTextField;
	private JTextField tenTextField;
	private JTextField tienLuongTextField;
	private JTextField tuoiTextField;
	private JTextField nhapMaSoTextField;
	private JButton timBtn;
	private JButton themBtn;

	private JButton xoaTrangBtn;
	private JRadioButton phaiRadioBtn;
	private JButton xoaBtn;
	private JButton luuBtn;

	private DefaultTableModel nhanVienModel;
	private JTable nhanVienTable;

	@Override
	protected JPanel onCreateNestedContainerView() {
		JPanel body = new JPanel(new BorderLayout());
		body.setBackground(Color.decode(ColorConsts.ForegroundColor));
		Box container = Box.createVerticalBox();
		container.setBackground(Color.decode(ColorConsts.ForegroundColor));
		container.setBorder(new EmptyBorder(15, 15, 15, 15));
		// ma nhan vien group
		Box maNVGroup = Box.createHorizontalBox();
		JLabel maNvLabel = new JLabel("Ma nhan vien:  ");
		maNVGroup.add(maNvLabel);
		maNVGroup.add(maNvTextField = new JTextField());
		container.add(maNVGroup);
		container.add(Box.createVerticalStrut(10));

		// ho - ten nv group
		JPanel hoTenNhanVienGroup = new JPanel(new GridLayout(1, 2));

		Box hoGroup = Box.createHorizontalBox();
		JLabel hoLabel = new JLabel("Ho:");
		hoLabel.setPreferredSize(maNvLabel.getPreferredSize());
		hoGroup.add(hoLabel);
		hoGroup.add(hoTextField = new JTextField());

		Box tenGroup = Box.createHorizontalBox();
		JLabel tenLabel = new JLabel("Ten nhan vien:");
		tenLabel.setPreferredSize(maNvLabel.getPreferredSize());
		tenGroup.add(tenLabel);
		tenGroup.add(tenTextField = new JTextField());
		tenGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		hoTenNhanVienGroup.add(hoGroup);
		hoTenNhanVienGroup.add(tenGroup);
		hoTenNhanVienGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));
		container.add(hoTenNhanVienGroup);
		container.add(Box.createVerticalStrut(10));

		// tuoi - phai group
		Box tuoiPhaiGroup = Box.createHorizontalBox();

		Box tuoiGroup = Box.createHorizontalBox();
		JLabel tuoiLabel = new JLabel("Tuoi:");
		tuoiLabel.setPreferredSize(maNvLabel.getPreferredSize());
		tuoiGroup.add(tuoiLabel);
		tuoiGroup.add(tuoiTextField = new JTextField());
		tuoiPhaiGroup.add(tuoiGroup);

		Box phaiGroup = Box.createHorizontalBox();
		JLabel phaiLabel = new JLabel("Phai:");
		phaiLabel.setPreferredSize(maNvLabel.getPreferredSize());
		phaiGroup.add(phaiLabel);
		phaiGroup.add(phaiRadioBtn = new JRadioButton("Nu"));
		phaiRadioBtn.setBackground(Color.decode(ColorConsts.ForegroundColor));
		phaiGroup.setBackground(Color.decode(ColorConsts.ForegroundColor));

		tuoiPhaiGroup.add(phaiGroup);
		container.add(tuoiPhaiGroup);
		container.add(Box.createVerticalStrut(10));
		// Tien luong group
		Box tienLuongGroup = Box.createHorizontalBox();
		JLabel tienLuongLabel = new JLabel("Tien luong:");
		tienLuongLabel.setPreferredSize(maNvLabel.getPreferredSize());
		tienLuongGroup.add(tienLuongLabel);
		tienLuongGroup.add(tienLuongTextField = new JTextField());
		container.add(tienLuongGroup);
		container.add(Box.createVerticalStrut(10));
		body.add(container, BorderLayout.NORTH);

		nhanVienModel = new DefaultTableModel(new String[] { "Ma", "Ho", "Ten", "Phai", "Tuoi", "Tien luong" }, 0);
		nhanVienTable = new JTable(nhanVienModel);

		body.add(new JScrollPane(nhanVienTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

		return body;
	}

	@Override
	protected JPanel onCreateHeader() {

		return new Header().addTitle("Quản lí nhân viên").createView();
	}

}
