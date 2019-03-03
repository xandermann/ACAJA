package gui.processing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonPan extends JPanel {
	
	private JToggleButton flouButton;
	private JToggleButton rectangle;
	
	public ButtonPan() {
		this.setLayout(new GridLayout(3, 2, 1, 1));
		JToggleButton rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
		JToggleButton flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.BLURRED));
		JButton pivoteD1 = new JButton(new ImageIcon(resources.ResourceConstants.LEFT_ARROW));
		JButton pivoteG1 = new JButton(new ImageIcon(resources.ResourceConstants.RIGHT_ARROW));

		rectangle.setPreferredSize(new Dimension(35, 35));
		flouButton.setPreferredSize(new Dimension(35, 35));
		pivoteD1.setPreferredSize(new Dimension(35, 35));
		pivoteG1.setPreferredSize(new Dimension(35, 35));

		this.add(rectangle);
		this.add(flouButton);
		this.add(pivoteD1);
		this.add(pivoteG1);
		this.repaint();
	}
	
}
