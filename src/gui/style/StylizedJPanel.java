package gui.style;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJPanel extends JPanel implements StylizedComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2554611420735505267L;

	/**
	 * Constructeur du JPanel. Ce constructeur est minimal et ne fait que appeler la
	 * fonction super() puis stylise le composant.
	 */
	public StylizedJPanel() {
		super();
		this.stylize();
	}

	/**
	 * Constructeur du JPanel
	 * 
	 * @param layout Si nous avons besoin du LayoutManager, nous pouvons l'inclure
	 *               ici. Ce constructeur ne fait que l'ajouter via la fonction
	 *               super()
	 */
	public StylizedJPanel(LayoutManager layout) {
		super(layout);
		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleTheme.BACKGROUND_COLOR);
	}

}
