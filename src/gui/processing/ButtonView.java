package gui.processing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonView extends JPanel {

	public ButtonView() {
		this.setLayout(new GridLayout(3, 2, 1, 1));
		try {
			JToggleButton rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
			JToggleButton flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.BLURRED));
			JToggleButton pivoteG = new JToggleButton();
			JToggleButton rectangle1 = new JToggleButton();
			JButton pivoteD1 = new JButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));
			JButton pivoteG1 = new JButton(new ImageIcon(resources.ResourceConstants.BUTTON_RECT));

			rectangle.setPreferredSize(new Dimension(35, 35));
			flouButton.setPreferredSize(new Dimension(35, 35));
			pivoteG.setPreferredSize(new Dimension(35, 35));
			rectangle1.setPreferredSize(new Dimension(35, 35));
			pivoteD1.setPreferredSize(new Dimension(35, 35));
			pivoteG1.setPreferredSize(new Dimension(35, 35));

			this.add(rectangle);
			this.add(flouButton);
			this.add(pivoteG);
			this.add(rectangle1);
			this.add(pivoteD1);
			this.add(pivoteG1);
			this.repaint();
		} catch (Exception e) {
			System.out.println("je marche une fois sur 2");
		}

	}
}
