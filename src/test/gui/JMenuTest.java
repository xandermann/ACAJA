package test.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import gui.style.StylizedJMenu;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;

/**
 * Pour ne pas à avoir à relancer l'application à chaque fois, nous créons ici
 * une "application miniature" pour tester si les composants créées s'affichent
 * correctement ou pas.
 * 
 * @author alex
 *
 */
public class JMenuTest {

	/**
	 * Creation d'une frame qui test le style des composants graphiques de la
	 * fenetre.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creation JMenuBar
		StylizedJMenuBar barre = new StylizedJMenuBar();

		// Creation JMenu
		StylizedJMenu profilesMenu = new StylizedJMenu("Profils");

		StylizedJMenuItem item1 = new StylizedJMenuItem("Creer un profil - Item 1");
		StylizedJMenuItem item2 = new StylizedJMenuItem("Charger un profil - Item 2");
		StylizedJMenuItem item3 = new StylizedJMenuItem("Supprimer un profil - Item 3");

		profilesMenu.add(item1);
		profilesMenu.add(item2);
		profilesMenu.add(item3);

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

		// Exemple

		item1.setBackground(Color.BLUE);

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

		// Ajoute les menus
		barre.add(profilesMenu);
		frame.setJMenuBar(barre);

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
	}

}
