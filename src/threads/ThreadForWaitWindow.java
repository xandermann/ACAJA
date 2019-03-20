package threads;

import java.io.File;

import javax.swing.JOptionPane;

import gui.WindowTools;
import gui.alerts.Alert;
import gui.alerts.AlertWindow;
import resources.ResourceConstants;
import wrapper.streams.managers.consumers.WatchedConsumer;
import wrapper.streams.managers.filters.FilterForFeedback;

/**
 * [ CLASSE THREAD POUR GERER LES FENETRES D'ATTENTE ET DE FIN D'ATTENTE. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ThreadForWaitWindow extends Thread{
	/**
	 * [ ATTRIBUTS D'INSTANCE - LES FENETRES D'ATTENTE ET DE FIN D'ATTENTE. ]
	 */
	private AlertWindow waitWindow;
	private File concernedFile;
	
	
	/**
	 * [ CONSTRUCTEUR INTERNE - ENCAPSULE ]
	 * 
	 * @param waitWindow	La fenetre d'attente. 
	 */
	private ThreadForWaitWindow(AlertWindow waitWindow, File concernedFile) {
		if((this.waitWindow = waitWindow) == null)
			throw new NullPointerException("La fenetre d'attente recue en parametre est null !");
		if((this.concernedFile = concernedFile) == null)
			throw new NullPointerException("Le fichier concerne recu en parametre est null !");
	}
	
	
	/**
	 * [ DEROULEMENT DU THREAD. ]
	 */
    public void run() {
    	/**
    	 * ATTENTE.
    	 */
    	WindowTools.executeWindow(waitWindow);
		while(WatchedConsumer.hand.took());
		waitWindow.dispose();
		/**
		 * FEEDBACK
		 */
		File[] filesErr = ResourceConstants.STDERR_ANSWERS.listFiles();
		long time = 1000;
		if(FilterForFeedback.successed(filesErr[filesErr.length-1], concernedFile)) {
			Alert.longAlert(Alert.SUCCESS, "L'operation a ete realisee avec succes !");
			time += Alert.LONG;
		}else{
			Alert.shortAlert(Alert.FAILURE, "L'operation en cours a echouee !");
			time += Alert.SHORT;
		}
		/**
		 * RENDRE LA MAIN.
		 */
		try {
			Thread.sleep(time);
		} catch (InterruptedException ie) {
			JOptionPane.showMessageDialog(null, ie.getMessage());
		}
		RuntimeSpaceManager.hand.render();
    }
    
    
    /**
     * [ METHODE POUR LANCER LE THREAD. ]
     * 
     * @param model		Le modele. 
     */
    public static void waitInNewThread(AlertWindow waitWindow, File concernedFile) {
    	new ThreadForWaitWindow(waitWindow, concernedFile).start();
    }
}
