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
	private BasePage myProfilePage;
	private BasePage warehousePage;
	private DiscountPage discountPage;
	
	public RootFrame() {
		super("HKTD Pharamacy");

		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 1000));
		Image img = Toolkit.getDefaultToolkit().createImage("img\\img_logoSmall.png");
		setIconImage(img);

		
		dashboardPage = new DashboardPage();
		orderPage = new OrderPage();
		customerPage = new CustomerPage();
		premisesPage = new PremisePage();
		productPage = new ProductPage();
		employeeManagerPage = new EmployeeManagerPage();
		warehousePage = new WarehousePage();
		myProfilePage = new MyProfilePage();
		discountPage = new DiscountPage();
		
		add(getNavigationBar(), BorderLayout.WEST);
		add(dashboardPage, BorderLayout.CENTER);

		getContentPane().setBackground(Color.decode(ColorConsts.BackgroundColor));

		addWindowStateListener(this);
	}

	private JPanel getNavigationBar() {
		return new NavigationBar()
				.addNaviButton("Trang chủ", "icon\\ic_home.png")
				.addNaviButton("Đơn hàng", "icon\\ic_bill.png")
				.addNaviButton("Sản phẩm", "icon\\ic_product.png")
				.addNaviButton("Khách hàng", "icon\\ic_customer.png")
				.addNaviButton("Quầy", "icon\\ic_stall.png")
				.addNaviButton("Quản lí nhân viên", "icon\\ic_employee.png")
				.addNaviButton("Quản lí kho", "icon\\ic_warehouse.png")
				.addNaviButton("Mã giảm giá", "icon\\ic_discount.png")
				.addNaviButton("Thông tin cá nhân", "icon\\ic_profile.png")
				.addNavigateListener(new INavigateListener() {
					@Override
					public void onNavigated(String txt) {
						remove(dashboardPage);
						remove(orderPage);
						remove(customerPage);
						remove(premisesPage);
						remove(productPage);
						remove(employeeManagerPage);
						remove(myProfilePage);
						remove(warehousePage);
						remove(discountPage);
						
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
						
						if (txt.equals("Thông tin cá nhân")) {
							add(myProfilePage, BorderLayout.CENTER);
						}
						
						if (txt.equals("Quản lí kho")) {
							add(warehousePage, BorderLayout.CENTER);
						}
						
						if (txt.equals("Mã giảm giá")) {
							add(discountPage, BorderLayout.CENTER);
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