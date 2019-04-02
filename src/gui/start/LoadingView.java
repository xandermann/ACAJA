package gui.start;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.style.StyleTheme;
import gui.style.StylizedJPanel;
import resources.ResourceConstants;

public final class LoadingView extends StylizedJPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2215930640158610324L;
	private int[] pointsLoadingBar;
	/**
	 * Pourcentage total de la barre
	 */
	private int percentage;

	private boolean withImage;

	/**
	 * Recuperer la largeur de la barre de chargement (120 = les marges a gauche et
	 * a droite)
	 */
	private final int WIDTH = 280;

	/**
	 * Constructeur sans parametre, ajoute 4 points pour la barre de chargement
	 * Configuration par defaut
	 */
	public LoadingView() {
		this.pointsLoadingBar = new int[] { 50, 330, 280, 25 };
		this.withImage = true;
	}

	/**
	 * Constructeur qui ajoute les 4 points de la barre de chargement
	 * 
	 * @param withImage Defini si l'on affiche l'image ou non (utilise pour les
	 *                  tests)
	 * @param points    Ajoute les 4 points de la barre
	 */
	public LoadingView(boolean withImage, int[] points) {
		this.pointsLoadingBar = points;
		this.withImage = withImage;
	}

	/**
	 * Met a jour le pourcentage de la fenêtre
	 * 
	 * @param percentage Pourcentage voulu
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
		this.repaint();
	}

	/**
	 * Calcule le pourcentage en pixel
	 * 
	 * @return Le pourcentage de la fenetre
	 */
	public int getCalcultatedWidth() {
		return (this.percentage * this.WIDTH) / 100;
	}

	@Override
	public void paintComponent(Graphics g) {
		// Ces 2 lignes de code corrigent un bug (barre noire a droite)
		g.setColor(StyleTheme.BACKGROUND_COLOR);
		g.fillRect(0, 0, 400, 400);

		// Ajout du logo
		try {
			g.drawImage(ImageIO.read(ResourceConstants.ACAJA_LOGO), 80, 60, null);
		} catch (IOException ioe) {
			System.exit(1);
		}

		g.setColor(StyleTheme.BACKGROUND_COLOR);
		g.fillRect(50, 330, 280, 25);

		g.setColor(StyleTheme.BACKGROUND_COLOR_SECONDARY);
		g.fillRect(50, 330, this.getCalcultatedWidth(), 25);

	}
}
