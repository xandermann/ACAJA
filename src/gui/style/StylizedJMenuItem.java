package gui.style;

import java.awt.Color;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuItem extends javax.swing.JMenuItem implements StylizedComponent {

	/**
	 * Constructeur du JMenuItem
	 * 
	 * @param title Ajout d'un titre obligatoire pour le JMenuItem. Le constructeur
	 *              reste très simple car il ne fait qu'appeler la fonction super()
	 *              de Java.
	 */
	public StylizedJMenuItem(String title) {
		super(title);
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setForeground(Color.BLACK);
		// this.setBorderPainted(true);
	}

}
