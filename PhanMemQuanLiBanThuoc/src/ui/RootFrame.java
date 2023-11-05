package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
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

public class RootFrame extends JFrame implements WindowStateListener {

	private BasePage dashboardPage;
	private BasePage orderPage;
	private BasePage productPage;

	public RootFrame() {
		super("Phần mềm quản lí thuốc nhóm 2");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 1000));

		dashboardPage = new DashboardPage();
		orderPage = new OrderPage();
		productPage = new ProductPage();

		add(getNavigationBar(), BorderLayout.WEST);
		add(dashboardPage, BorderLayout.CENTER);

		getContentPane().setBackground(Color.decode(ColorConsts.BackgroundColor));

		addWindowStateListener(this);
	}

	private JPanel getNavigationBar() {
		return new NavigationBar().addNaviButton("Trang chủ", "").addNaviButton("Đơn hàng", "")
				.addNaviButton("Sản phẩm", "").addNaviButton("Khách hàng", "").addNaviButton("Cài đặt", "")
				.addNavigateListener(new INavigateListener() {
					@Override
					public void onNavigated(String txt) {
						remove(dashboardPage);
						remove(orderPage);

						if (txt.equals("Trang chủ")) {
							add(dashboardPage, BorderLayout.CENTER);
						}

						if (txt.equals("Đơn hàng")) {
							add(orderPage, BorderLayout.CENTER);
						}
						
						if (txt.equals("Sản phẩm")) {
							add(productPage, BorderLayout.CENTER);
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