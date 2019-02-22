package gui;

public class LoadingOpeningWindow {

	/**
	 * Pourcentage total de la barre
	 */
	private int percentage;

	/**
	 * Recuperer la largeur de la fenetre
	 */
	private final int WIDTH = OpeningWindow.WIDTH;

	/**
	 * Met a jour le pourcentage de la fenêtre
	 * 
	 * @param percentage Pourcentage voulu
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getCalcultatedWidth() {
		// 0 % - 0 px

		// 100 % - this.WIDTH px
		// this.percentage % - z px

		// Donc
		return (this.percentage * this.WIDTH) / 100;
	}

}
