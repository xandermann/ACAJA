package gui.style;


import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.border.Border;

public class StylizedJMenu extends JMenu implements StylizedComponent {

	public StylizedJMenu() {
		super();

		this.stylize();
	}

	public StylizedJMenu(String title) {
		super(title);

		this.stylize();
	}

	@Override
	public void stylize() {
		// TODO
	}

}
