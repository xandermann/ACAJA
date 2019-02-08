package style;

import java.awt.Color;

public class JMenuItem extends javax.swing.JMenuItem {

	public JMenuItem(String title) {
		super(title);

		this.setBackground(Style.BACKGROUND_PRIMARY);
		this.setForeground(Color.BLACK);
		// this.setBorderPainted(true);
	}

}
