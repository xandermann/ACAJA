package tests.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.LoadingView;
import gui.style.StyleTheme;
import gui.style.StylizedJButton;
import gui.style.StylizedJMenu;
import gui.style.StylizedJMenuBar;
import gui.style.StylizedJMenuItem;
import gui.style.StylizedJPanel;

/**
 * Pour ne pas à avoir à relancer l'application à chaque fois, nous créons ici
 * une "application miniature" pour tester si les composants créées s'affichent
 * correctement ou pas.
 * 
 * @author alex
 *
 */
public class MainStylizedComponentsTest {

	/**
	 * Méthode qui permet de générer un menu pour le template
	 * 
	 * @return Menu généré
	 */
	private static StylizedJMenuBar createJMenuBar() {
		// Creation JMenuBar
		StylizedJMenuBar barre = new StylizedJMenuBar();

		// Creation JMenu
		StylizedJMenu profilesMenu = new StylizedJMenu("Profils");
		profilesMenu.add(new StylizedJMenuItem("Creer un profil - Item 1"));
		profilesMenu.add(new StylizedJMenuItem("Charger un profil - Item 2"));
		profilesMenu.add(new StylizedJMenuItem("Supprimer un profil - Item 3"));

		StylizedJMenu conversionMenu = new StylizedJMenu("Conversion");
		conversionMenu.add(new StylizedJMenuItem("Ajouter un fichier"));
		conversionMenu.add(new StylizedJMenuItem("Ajouter une image"));
		conversionMenu.add(new StylizedJMenuItem("Charger la conversion"));

		// Ajoute les menus
		barre.add(profilesMenu);
		barre.add(conversionMenu);

		return barre;
	}

	/**
	 * Creation d'une frame qui test le style des composants graphiques de la
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

		// JMenu
		frame.setJMenuBar(MainStylizedComponentsTest.createJMenuBar());

		// Panel
		StylizedJPanel panel = new StylizedJPanel();

		panel.add(new StylizedJButton("Mon super bouton !"));
		panel.add(new StylizedJButton("Attention danger !", StyleTheme.DANGER));
		panel.add(new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.setColor(Color.RED);
				g.fillRect(0, 0, 40, 40);
			}
		});

		frame.add(panel);

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

		// close
	}

}
