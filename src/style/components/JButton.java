package style.components;

import java.awt.Color;

public class JButton extends javax.swing.JButton {

	public JButton(String text) {

		super(text);
		
		this.setBackground(new Color(59, 89, 182));
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);

	}

}
