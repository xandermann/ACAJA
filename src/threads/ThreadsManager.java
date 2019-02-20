package threads;

import javax.swing.JOptionPane;

import messages.MessageConstants;
import wrapper.streams.WatchedConsumer;

/**
 * [ CLASSE POUR LA GESTION DE L'ESPACE D'EXECUTION POUR LES THREADS. 
 *	
 */
public final class ThreadsManager {
	/**
	 * [ GESTIONNAIRE DE L'ESPACE D'EXECUTION. ]
	 * 
	 * @return	Vaut true si aucunnes operations de conversion n'est deja en cours. 
	 */
	public static boolean manageRuntimeSpace() {
		if(WatchedConsumer.workIsOnGoing()) {
			JOptionPane.showMessageDialog(null, MessageConstants.ERROR_CONVERSION_IS_ALREADY_ON_GOING);
			return false;
		}
		return true;
	}
	
}
