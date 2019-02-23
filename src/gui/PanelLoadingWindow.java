package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.style.StylizedJPanel;
import resources.ResourceConstants;

public class PanelLoadingWindow extends StylizedJPanel {

	private int[] pointsLoadingBar;
	/**
	 * Pourcentage total de la barre
	 */
	private int percentage;

	private boolean withImage;
	
	/**
	 * Recuperer la largeur de la fenetre
	 */
	private final int WIDTH = OpeningWindow.WIDTH;

	/**
	 * Constructeur sans parametre, ajoute 4 points pour la barre de chargement
	 * Configuration par defaut
	 */
	public PanelLoadingWindow() {
		this.pointsLoadingBar = new int[] { 50, 330, 280, 25 };
		this.withImage = true;
	}
	
	/**
	 * Constructeur qui ajoute les 4 points de la barre de chargement
	 * @param withImage Defini si l'on affiche l'image ou non (utilise pour les tests)
	 * @param points Ajoute les 4 points de la barre
	 */
	public PanelLoadingWindow(boolean withImage, int[] points) {
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
	}

	/**
	 * Calcule le pourcentage en pixel
	 * 
	 * @return Le pourcentage de la fenetre
	 */
	public int getCalcultatedWidth() {
		// 0 % - 0 px

		// 100 % - this.WIDTH px
		// this.percentage % - z px

		// Donc
		return (this.percentage * this.WIDTH) / 100;
	}

	@Override
	public void paintComponent(Graphics g) {

		// Ajout du logo
		try {
			g.drawImage(ImageIO.read(ResourceConstants.ACAJA_LOGO), 80, 60, null);
		} catch (IOException ioe) {
			System.exit(1);
		}

		g.setColor(Color.RED);
		g.fillRect(50, 330, 280, 25);

		g.setColor(Color.BLACK);
		g.drawString("Imaginez que la barre existe", 55, 340);
		g.drawString("TODO: Barre de chargement -> Alexandre H", 55, 355);

	}

}
