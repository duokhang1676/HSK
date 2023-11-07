package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public abstract class BasePage extends JPanel {
	
	protected abstract JPanel onCreateNestedContainerView();
	
	protected abstract JPanel onCreateHeader();
	
	public BasePage() {
		setLayout(new BorderLayout());
	
		JPanel nestedContainer = onCreateNestedContainerView();
		JPanel header = onCreateHeader();
		
		add(header != null ? header : new JPanel(), BorderLayout.NORTH);
		add(nestedContainer != null ? nestedContainer : new JPanel(), BorderLayout.CENTER);
		
		
	}
	
}
