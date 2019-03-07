package tests.gui.style;

import java.awt.Dimension;

import javax.swing.JFrame;

import gui.style.StylizedJFileChooser;

public class MainStylizedJFileChooser {

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

		StylizedJFileChooser chooser = new StylizedJFileChooser();
		frame.add(chooser);
		
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
		frame.setPreferredSize(new Dimension(800, 800));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Supprime la fenetre au bout de 4 secondes
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}

		frame.dispose();

		// close
	}

}
