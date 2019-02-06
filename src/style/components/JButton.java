package style.components;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JButton extends javax.swing.JButton {

	public JButton(String text) {

		super(text);
		
		this.setBackground(new Color(59, 89, 182));
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);

	}

}
