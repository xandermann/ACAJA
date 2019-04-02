package gui.style;

import java.awt.Color;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJButton extends javax.swing.JButton implements StylizedComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2144569208796665234L;
	/**
	 * Couleur du bouton
	 */
	private Color color;
	
	/**
	 * Constructeur du JButton
	 * 
	 * @param title Ajout d'un texte obligatoire pour le JButton. Le constructeur
	 *              reste très simple car il ne fait qu'appeler la fonction super()
	 *              de Java.
	 */
	public StylizedJButton(String text) {
		super(text);
		
		this.color = StyleTheme.BACKGROUND_COLOR_SECONDARY;
		
		this.stylize();
	}
	
	public StylizedJButton(String text, Color color) {
		super(text);
		
		this.color = color;

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(this.color);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
	}

}
