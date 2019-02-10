package gui.style;

import java.awt.Color;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJButton extends javax.swing.JButton implements StylizedComponent {

	public StylizedJButton(String text) {
		super(text);

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR_SECONDARY);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
	}

}
