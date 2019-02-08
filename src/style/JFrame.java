package style;

public class JFrame extends javax.swing.JFrame {

	public JFrame() {
		this.stylize();
	}
	
	public JFrame(String title) {
		super(title);
		
		this.stylize();
	}
	
	private void stylize() {
		this.setBackground(Style.BACKGROUND_PRIMARY);
	}

}
