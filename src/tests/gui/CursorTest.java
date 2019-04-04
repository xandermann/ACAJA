package tests.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Pour ne pas à avoir à relancer l'application à chaque fois, nous créons ici
 * une "application miniature" pour tester si les composants créées s'affichent
 * correctement ou pas.
 * 
 * @author alex
 *
 */
public class CursorTest {

	/**
	 * Méthode qui permet de générer un menu pour le template
	 * 
	 * /** Creation d'une frame qui test le style des composants graphiques de la
	 * fenetre.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * 
		 * 
		 * 
		 * == ZONE DE TESTS ============================================================
		 * 
		 * 
		 * 
		 * 
		 */

		frame.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));

		// setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		// frame.add();

		/*
		 * 
		 * 
		 * 
		 * == FIN ZONE DE TESTS ======================================================
		 * 
		 * 
		 * 
		 * 
		 */

		// Cree la fenetre
		frame.setPreferredSize(new Dimension(800, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Supprime la fenetre au bout de 4 secondes
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}

		frame.dispose();
		
		
		JFrame framed = new JFrame();
		framed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * 
		 * 
		 * 
		 * == ZONE DE TESTS ============================================================
		 * 
		 * 
		 * 
		 * 
		 */

		framed.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));

		// setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		// frame.add();

		/*
		 * 
		 * 
		 * 
		 * == FIN ZONE DE TESTS ======================================================
		 * 
		 * 
		 * 
		 * 
		 */

		// Cree la fenetre
		framed.setPreferredSize(new Dimension(800, 200));
		framed.pack();
		framed.setLocationRelativeTo(null);
		framed.setVisible(true);

		// Supprime la fenetre au bout de 4 secondes
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}

		framed.dispose();

		// close
	}

}
