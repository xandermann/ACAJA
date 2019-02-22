package gui.style;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJFrame extends javax.swing.JFrame implements StylizedComponent {

	/**
	 * Constructeur du JFrame
	 * 
	 * @param Le constructeur du JFrame. Il reste très simple car il ne fait
	 *           qu'appeler la fonction super() de Java.
	 */
	public StylizedJFrame() {
		this.stylize();
	}

	/**
	 * Constructeur du JMenuItem
	 * 
	 * @param title Ajout d'un titre obligatoire pour le JFrame. Le constructeur
	 *              reste très simple car il ne fait qu'appeler la fonction super()
	 *              de Java.
	 */
	public StylizedJFrame(String title) {
		super(title);

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleTheme.BACKGROUND_COLOR);
	}

}
