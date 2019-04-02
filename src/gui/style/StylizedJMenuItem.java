package gui.style;

import java.awt.Color;

import javax.swing.JMenuItem;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuItem extends JMenuItem implements StylizedComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7454089581736060222L;

	/**
	 * Constructeur du JMenuItem
	 * 
	 * @param title Ajout d'un titre obligatoire pour le JMenuItem. Le constructeur
	 *              reste très simple car il ne fait qu'appeler la fonction super()
	 *              de Java.
	 */
	public StylizedJMenuItem(String title) {
		super(title);

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleTheme.BACKGROUND_COLOR);
		this.setForeground(Color.BLACK);
		// this.setBorderPainted(true);
	}

	/*
	 * @Override protected void paintComponent(Graphics g) {
	 * super.paintComponent(g); Graphics2D g2d = (Graphics2D) g;
	 * g2d.drawString(this.getText(), 200, 200);
	 * g2d.setColor(StyleConfigurator.BACKGROUND_COLOR); g2d.fillRect(0, 0,
	 * getWidth() - 1, getHeight() - 1); }
	 */

}
