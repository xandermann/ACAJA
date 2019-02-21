package gui;

import java.io.*;

import javax.imageio.ImageIO;

import javax.swing.*;

import resources.ResourceConstants;

/**
 * [ CLASSE OUTILS POUR LES FENETRES DU LOGICIEL. ]
 * 
 * Cette classe implemente des methodes communes aux fenetres.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class WindowTools {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE POUR MONTRER LE LOGO DU LOGICIEL. ]
	 * 
	 * Cette methode permet de montrer le logo du logiciel en tant qu'icone 
	 * de la fenetre en parametre de la methode.
	 * 
	 * @param window 		La fenetre a laquelle ajouter le logo en icone.
	 */
	public static void showLogo(JFrame window) {
		try {
			window.setIconImage(ImageIO.read(ResourceConstants.ACAJA_LOGO));
		} catch (IOException ioe) {}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	/**
	 * [ METHODE DE CLASSE POUR LANCER UNE FENETRE. ]
	 * 
	 * Cette methode permet de lancer/demarrer une fenetre dans un
	 * nouveau processus ( = Thread / Runnable ). 
	 * 
	 * @param window		La fenetre a lancer.
	 */
	public static void executeWindow(JFrame window) {
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		        window.setVisible(true);
		    }
		});
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
