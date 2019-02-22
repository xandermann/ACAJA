package gui.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuBar extends javax.swing.JMenuBar implements StylizedComponent {

	public StylizedJMenuBar() {
		super();
		this.stylize();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleTheme.BACKGROUND_COLOR);
		this.setBorder(new LineBorder(StyleTheme.BACKGROUND_COLOR_SECONDARY));
	}

}
