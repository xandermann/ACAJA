package gui.style;


import javax.swing.JMenu;

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
