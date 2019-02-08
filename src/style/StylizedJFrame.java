package style;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJFrame extends javax.swing.JFrame implements StylizedComponent {

	public StylizedJFrame() {
		this.stylize();
	}
	
	public StylizedJFrame(String title) {
		super(title);
		
		this.stylize();
	}
	
	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
	}

}
