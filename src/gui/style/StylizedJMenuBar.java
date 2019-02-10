package gui.style;

import javax.swing.border.LineBorder;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuBar extends javax.swing.JMenuBar implements StylizedComponent {

	public StylizedJMenuBar() {
		super();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setBorder(new LineBorder(StyleConfigurator.BACKGROUND_COLOR_SECONDARY));
	}

}
