package test.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;

import style.JMenuBar;
import style.JMenuItem;

public class JMenuTest {

	/**
	 * Creation d'une frame qui test le style des composants graphiques de la
	 * fenetre
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creation JMenu
		JMenuBar barre = new JMenuBar();

		JMenu profilesMenu = new JMenu("Profils");
		profilesMenu.add(new JMenuItem("Creer un profil"));
		profilesMenu.add(new JMenuItem("Creer un profil"));
		profilesMenu.add(new JMenuItem("Creer un profil"));

		barre.add(profilesMenu);
		frame.setJMenuBar(barre);

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
