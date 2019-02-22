package gui.style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe qui permet de styliser le composant java swing correspondant pour
 * designer l'application.
 */
public class StylizedJMenuItem extends javax.swing.JMenuItem implements StylizedComponent, MouseListener {

	/**
	 * Constructeur du JMenuItem
	 * 
	 * @param title Ajout d'un titre obligatoire pour le JMenuItem. Le constructeur
	 *              reste très simple car il ne fait qu'appeler la fonction super()
	 *              de Java.
	 */
	public StylizedJMenuItem(String title) {
		super(title);
	}

	@Override
	public void stylize() {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		this.setForeground(Color.BLACK);
		// this.setBorderPainted(true);
	}
	
	/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString(this.getText(), 200, 200);
		g2d.setColor(StyleConfigurator.BACKGROUND_COLOR);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	*/

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setBackground(StyleConfigurator.BACKGROUND_COLOR);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
