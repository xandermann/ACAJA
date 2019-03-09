package threads;

import javax.swing.JOptionPane;

import exceptions.UnfindableResourceException;
import gui.Model;

/**
 * [ CLASSE THREAD POUR LANCER LA SAUVEGARDE VIA FFMPEG. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ThreadForSave extends Thread{
	/**
	 * [ ATTRIBUT D'INSTANCE - MODELE. ]
	 */
	private Model model;
	
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param model		Le modele. 
	 */
	private ThreadForSave(Model model) {
		if((this.model = model) == null)
			throw new NullPointerException("Le modele recu en parametre est null !");
	}
	
	
	/**
	 * [ DEROULEMENT DU THREAD - SAUVEGARDE. ]
	 */
    public void run() {
    	try {
			model.save();
		} catch (UnfindableResourceException ure) {
			JOptionPane.showMessageDialog(null, ure.getMessage());
		}
    }
    
    
    /**
     * [ METHODE POUR LANCER LE THREAD DE LA SAUVEGARDE VIA FFMPEG. ]
     * 
     * @param model		Le modele. 
     */
    public static void saveInNewThread(Model model) {
    	(new ThreadForSave(model)).start();
    }
}
