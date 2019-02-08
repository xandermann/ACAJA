package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import style.Style;

/**
 * Classe qui genere le panel de chargement de l'application
 *
 */
public class LoadingWindowPanel extends JPanel {

	/**
	 * Pourcentage de la barre de chargement
	 */
	private int percentCurrentProgress;

	/**
	 * Affiche les elements a l'interieur de la fenetre
	 */
	public void paintComponent(Graphics g) {

		g.setColor(Style.BACKGROUND_SECONDARY);
		g.fillRect(20, 20, 200, 20);

		g.setColor(Style.BACKGROUND_PRIMARY);
		g.fillRect(20, 20, (this.percentCurrentProgress * 200) / 100, 20);

	}

	/**
	 * Met a jour la progression de la barre de chargement contenue dans ce JPanel
	 * 
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.percentCurrentProgress = progress;
		this.repaint();
	}

}
