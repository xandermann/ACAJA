package gui.style;

import javax.swing.JFileChooser;

public class StylizedJFileChooser extends JFileChooser implements StylizedComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3438609538445355923L;

	public StylizedJFileChooser() {
		super();
		
		this.stylize();
	}
	
	public StylizedJFileChooser(String title) {
		super(title);
		
		this.stylize();
	}

	@Override
	public void stylize() {
		// TODO
	}

}
