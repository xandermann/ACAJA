package gui;

import javax.swing.JFrame;
import tools.WindowTools;
import wrapper.runtime.global.UserRequests;

/**
 * [ CLASSE THREAD POUR GERER LA FENTRE D'ATTENTE. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public class ThreadForWaitWindow extends Thread{
	/**
	 * [ ATTRIBUT D'INSTANCE - LA FENETRE D'ATTENTE. ]
	 */
	private JFrame waitWindow;
	
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param waitWindow	La fenetre d'attente. 
	 */
	private ThreadForWaitWindow(JFrame waitWindow) {
		if((this.waitWindow = waitWindow) == null)
			throw new NullPointerException("Le fenetre d'attente recue en parametre est null !");
	}
	
	
	/**
	 * [ DEROULEMENT DU THREAD - ATTENTE ET FERMETURE DE LA FENETRE. ]
	 */
    public void run() {
    	WindowTools.executeWindow(waitWindow);
		while(UserRequests.workIsOnGoing());
		waitWindow.dispose();
    }
    
    
    /**
     * [ METHODE POUR LANCER LE THREAD DE LA FENETRE D'ATTENTE. ]
     * 
     * @param model		Le modele. 
     */
    public static void waitInNewThread(JFrame waitWindow) {
    	(new ThreadForWaitWindow(waitWindow)).start();
    }
}
