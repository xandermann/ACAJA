package test.gui;

import java.awt.Dimension;
import javax.swing.*;
import gui.style.*;

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

		// Creation JMenu
		StylizedJMenuBar barre = new StylizedJMenuBar();

		StylizedJMenu profilesMenu = new StylizedJMenu("Profils");
		profilesMenu.add(new StylizedJMenuItem("Creer un profil - Menu 1"));
		profilesMenu.add(new StylizedJMenuItem("Charger un profil - Menu 2"));
		profilesMenu.add(new StylizedJMenuItem("Supprimer un profil - Menu 3"));

		barre.add(profilesMenu);
		frame.setJMenuBar(barre);

		frame.setPreferredSize(new Dimension(800, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		
		frame.dispose();
	}

}
