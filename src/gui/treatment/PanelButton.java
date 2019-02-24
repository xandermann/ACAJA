package gui.treatment;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PanelButton extends JPanel {

	public PanelButton() {
		this.setLayout(new GridLayout(3, 2, 1, 1));
		try {
			JToggleButton rectangle = new JToggleButton(new ImageIcon(resources.ResourceConstants.rectbutton));
			JToggleButton flouButton = new JToggleButton(new ImageIcon(resources.ResourceConstants.flou));
			JToggleButton pivoteG = new JToggleButton();
			JToggleButton rectangle1 = new JToggleButton();
			JButton pivoteD1 = new JButton(new ImageIcon(resources.ResourceConstants.arrowR));
			JButton pivoteG1 = new JButton(new ImageIcon(resources.ResourceConstants.arrowL));

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
