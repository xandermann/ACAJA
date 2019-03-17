package threads;

import javax.swing.JOptionPane;

import exceptions.UnfindableResourceException;
import files.files.SettingsFile;
import wrapper.runtime.global.UserRequests;

/**
 * [ CLASSE THREAD POUR LANCER LA SAUVEGARDE VIA FFMPEG. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ThreadForSave extends Thread{
	/**
	 * [ ATTRIBUT D'INSTANCE. ]
	 */
	private SettingsFile file;
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param file		Le fichier a traiter. 
	 */
	private ThreadForSave(SettingsFile file) {
		if((this.file = file) == null)
			throw new NullPointerException("Le file recu en parametre est null !");
	}
	
	
	/**
	 * [ DEROULEMENT DU THREAD - SAUVEGARDE. ]
	 */
    public void run() {
    	try {
    		UserRequests.execute(file);
		} catch (UnfindableResourceException ure) {
			JOptionPane.showMessageDialog(null, ure.getMessage());
		}
    }
    
    
    /**
     * [ METHODE POUR LANCER LE THREAD DE LA SAUVEGARDE VIA FFMPEG. ]
     * 
     * @param file		Le fichier a traiter.  
     */
    public static void saveInNewThread(SettingsFile file) {
    	(new ThreadForSave(file)).start();
    }
}
