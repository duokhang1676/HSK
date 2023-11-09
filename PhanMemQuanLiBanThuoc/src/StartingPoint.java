import javax.swing.SwingUtilities;

import db.ConnectDB;
import ui.DashboardPage;
import ui.RootFrame;
import ui.LoginPage;

public class StartingPoint {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				RootFrame frm = new RootFrame();
				LoginPage login = new LoginPage();
				login.setVisible(true);
			}
		});
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("khong ket noi");
		}
		
	}
}
