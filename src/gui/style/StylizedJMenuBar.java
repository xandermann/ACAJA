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
public class StylizedJMenuBar extends javax.swing.JMenuBar implements StylizedComponent, MouseListener {

	private boolean isEntered = false;
	private boolean isPressed = false;

	public StylizedJMenuBar() {
		super();
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setBorder(new LineBorder(StyleConfigurator.BACKGROUND_COLOR_SECONDARY));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (!this.isPressed) {
			g2d.setColor(StyleConfigurator.BACKGROUND_COLOR);
		} else {
			g2d.setColor(StyleConfigurator.BACKGROUND_COLOR_SECONDARY);
		}

		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.isPressed = false;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.isEntered = true;
		System.out.println("Salut");
		this.repaint();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.isEntered = false;
		this.repaint();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.isPressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isPressed = false;

	}

}
