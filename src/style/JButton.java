package style;

import java.awt.Color;

public class JButton extends javax.swing.JButton {

	public JButton(String text) {

		super(text);
		
		this.setBackground(Style.BACKGROUND_SECONDARY);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);

	}

}
