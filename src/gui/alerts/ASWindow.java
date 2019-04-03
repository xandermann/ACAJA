package gui.alerts;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import gui.general.Context;
import gui.general.GeneralWindow;
/**
 * [ CLASSE FENETRE DE LA VUE DE CHOIX DES PARAMETRES DES ALERTES. ]
 * 
 * 	AS = AlertSettings.
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue de choix des parametres des alertes.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ASWindow extends GeneralWindow{
	private static final long serialVersionUID = -5267140675594351287L;

	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public ASWindow(){
		super("Choix des parametres des notifications.", new ASView());
		/**
		 * SE SOUVENIR DES MODIFICATIONS.
		 */
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				AlertSettings.toRemember();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
	}
}
