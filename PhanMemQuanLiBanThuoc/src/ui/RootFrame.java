package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import components.ColorConsts;
import components.INavigateListener;
import components.NavigationBar;
import db.ConnectDB;

public class RootFrame extends JFrame implements WindowStateListener {

	private BasePage dashboardPage;
	private BasePage orderPage;
	private BasePage customerPage;
	private BasePage premisesPage;
	private BasePage productPage;
	private BasePage employeeManagerPage;
	private BasePage orderListPage;
	
	public RootFrame() {
		super("Phần mềm quản lí thuốc nhóm 2");

		
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("khong ket noi");
		}
		
	
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 1000));
		Image img = Toolkit.getDefaultToolkit().createImage("img\\img_logoSmall.png");
		setIconImage(img);

		
		
		orderListPage = new OrderListPage();
		dashboardPage = new DashboardPage();
		orderPage = new OrderPage();
		customerPage = new CustomerPage();
		premisesPage = new PremisePage();
		productPage = new ProductPage();
		employeeManagerPage = new EmployeeManagerPage();
		
		add(getNavigationBar(), BorderLayout.WEST);
		add(dashboardPage, BorderLayout.CENTER);

		getContentPane().setBackground(Color.decode(ColorConsts.BackgroundColor));

		addWindowStateListener(this);
	}
	//change
	public BasePage getOrderListPage() {
	    return orderListPage;
	}
	public BasePage getOrderPage() {
	    return orderPage;
	}

	private JPanel getNavigationBar() {
		return new NavigationBar()
				.addNaviButton("Trang chủ", "icon\\ic_home.png")
				.addNaviButton("Đơn hàng", "icon\\ic_bill.png")
				.addNaviButton("Sản phẩm", "icon\\ic_product.png")
				.addNaviButton("Khách hàng", "icon\\ic_customer.png")
				.addNaviButton("Quầy", "")
				.addNaviButton("Quản lí nhân viên", "")
				.addNavigateListener(new INavigateListener() {
					@Override
					public void onNavigated(String txt) {
						remove(orderListPage);//change
						remove(dashboardPage);
						remove(orderPage);
						remove(customerPage);
						remove(premisesPage);
						remove(productPage);
						remove(employeeManagerPage);
						
						
						if (txt.equals("Trang chủ")) {
							add(dashboardPage, BorderLayout.CENTER);
						}

						if (txt.equals("Đơn hàng")) {
							add(orderPage, BorderLayout.CENTER);
						}
						
						if (txt.equals("Khách hàng")) {
							add(customerPage, BorderLayout.CENTER);
						}
						
						if (txt.equals("Quầy")) {
							add(premisesPage, BorderLayout.CENTER);
						}

						if (txt.equals("Sản phẩm")) {
							add(productPage, BorderLayout.CENTER);
						}

						if (txt.equals("Quản lí nhân viên")) {
							add(employeeManagerPage, BorderLayout.CENTER);
						}

						SwingUtilities.updateComponentTreeUI(RootFrame.this);
					}
				}).createView();
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		if (e.getNewState() != Frame.MAXIMIZED_BOTH) {
			setSize(1000, 1000);
			setLocationRelativeTo(null);
		}

	}
}