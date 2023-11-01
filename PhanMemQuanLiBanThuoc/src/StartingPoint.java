import javax.swing.SwingUtilities;

import ui.DashboardPage;
import ui.RootFrame;

public class StartingPoint {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				RootFrame frm = new RootFrame();
				frm.setVisible(true);
			}
		});
	}
}
