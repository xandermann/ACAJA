package mvc;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principale {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Exemple projet MVC");
		JPanel panel = new JPanel();

		// Code MVC entre ces tirets:
		// =========================================================================

		// Principale => Contient les boutons, fenetres, panels, etc ...

		// Modele =====> Contient les donnees

		// Vue ========> Affiche les donnees
		// ============> Peut etre un JLabel....

		// Controller => C'est l'ActionListener, MouseListener....
		// ============> Il met a jour le model lorsqu'il est active
		// ============> Peut etre un JLabel.... mais rare

		// <=====================>

		// Initialisation classes MVC
		Modele modele = new Modele();

		VueConsole vue = new VueConsole(modele);
		VueGraphique vueGraphique = new VueGraphique(modele);

		ControlleurBoutton controller = new ControlleurBoutton(modele);

		//

		// <=====================>

		// <=====================>

		//

		// Ajout du listener au bouton
		JButton jButton = new JButton("Ajouter 1");
		jButton.addActionListener(controller);

		//

		// Ajout du bouton et label a la fenetre
		panel.add(jButton);
		panel.add(vueGraphique);
		frame.add(panel);

		// =========================================================================

		frame.setPreferredSize(new Dimension(300, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
