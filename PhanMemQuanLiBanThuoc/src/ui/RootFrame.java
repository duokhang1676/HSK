package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import components.ColorConsts;
import components.INavigateListener;
import components.NavigationBar;

public class RootFrame extends JFrame {
	
	private BasePage dashboardPage;
	private BasePage orderPage;
	
	
	public RootFrame() {
		super("Phần mềm quản lí thuốc nhóm 2");
		
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		 
		dashboardPage = new DashboardPage();
		orderPage = new OrderPage();
		
		add(getNavigationBar() , BorderLayout.WEST);
		add(dashboardPage, BorderLayout.CENTER);
		
		getContentPane().setBackground(Color.decode(ColorConsts.BackgroundColor));
	}
	
	
	private JPanel getNavigationBar() {
		return new NavigationBar()
				.addNaviButton("Trang chủ")
				.addNaviButton("Đơn hàng")
				.addNaviButton("Sản phẩm")
				.addNaviButton("Khách hàng")
				.addNaviButton("Cài đặt")
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
						
						revalidate();
					    repaint();
					    pack();
						setSize(1000, 1000);
					}
				})
				.createView();
	}
	
}