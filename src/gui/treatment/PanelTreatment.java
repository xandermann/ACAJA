package gui.treatment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTreatment extends JPanel {

	public PanelTreatment() {
		this.setBackground(Color.GRAY);

		PanelLibrary pb = new PanelLibrary();
		PanelButton pm = new PanelButton();
		PanelPicture pi = new PanelPicture();
		this.add(pb);
		this.add(pm);
		this.add(pi);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
