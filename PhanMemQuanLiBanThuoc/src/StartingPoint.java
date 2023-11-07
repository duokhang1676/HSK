import javax.swing.SwingUtilities;

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
				login.setLocationRelativeTo(null);
				login.setVisible(true);
			}
		});
	}
}
