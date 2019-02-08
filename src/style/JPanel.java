package style;

import java.awt.LayoutManager;

public class JPanel extends javax.swing.JPanel {

	public JPanel() {
		super();

		this.stylize();
	}

	public JPanel(LayoutManager layout) {
		super(layout);

		this.stylize();
	}

	public void stylize() {
		this.setBackground(Style.BACKGROUND_PRIMARY);
	}

}
