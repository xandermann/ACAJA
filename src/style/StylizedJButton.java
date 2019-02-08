package style;

import java.awt.Color;

public class StylizedJButton extends javax.swing.JButton implements StylizedComponent {

	public StylizedJButton(String text) {
		super(text);

		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR_SECONDARY);
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
	}

}
