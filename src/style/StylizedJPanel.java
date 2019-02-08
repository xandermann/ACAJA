package style;

import java.awt.LayoutManager;

public class StylizedJPanel extends javax.swing.JPanel implements StylizedComponent {

	public StylizedJPanel() {
		super();

		this.stylize();
	}

	public StylizedJPanel(LayoutManager layout) {
		super(layout);

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
	}

}
