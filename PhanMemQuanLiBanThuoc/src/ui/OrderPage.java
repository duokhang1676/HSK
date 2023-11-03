package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import components.ColorConsts;
import components.Header;

public class OrderPage extends BasePage {

	private JTable orderTable;
	private DefaultTableModel orderTableModel;
	
	public OrderPage() {
		super();
	}
	
	@Override
	public JPanel onCreateNestedContainerView() {
		JPanel panel = new JPanel(new BorderLayout());
		
		String[] colHeaders = "Mã hóa đơn;Phương thức thanh toán;Trạng thái;Tiền nhận;Tiền dư;Tổng tiền giảm;Tổng tiền".split(";");
		
		orderTableModel = new DefaultTableModel(colHeaders, 0);
		orderTable = new JTable(orderTableModel);
		
		panel.add(new JScrollPane(orderTable));
		
		return panel;
	}

	@Override
	protected JPanel onCreateHeader() {
		JPanel jp_test = new JPanel();
		jp_test.add(new JLabel("Hihi haha"));
		return new Header()
				.addTitle("Đơn hàng")
				.addInsidePanel(jp_test)
				.createView();
	}
	
}
