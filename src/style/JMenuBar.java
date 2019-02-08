package style;

import javax.swing.border.LineBorder;

public class JMenuBar extends javax.swing.JMenuBar {

	public JMenuBar() {
		super();
		
		this.setBackground(Style.BACKGROUND_PRIMARY);
		this.setBorder(new LineBorder(Style.BACKGROUND_SECONDARY));
	}

}
