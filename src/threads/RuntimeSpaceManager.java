package threads;

import javax.swing.JOptionPane;
import messages.MessageConstants;
import wrapper.streams.managers.consumers.WatchedConsumer;

/**
 * [ CLASSE POUR LA GESTION DE L'ESPACE D'EXECUTION POUR LES THREADS. 
 *	
 */
public final class RuntimeSpaceManager {
	/**
	 * [ GESTIONNAIRE DE L'ESPACE D'EXECUTION. ]
	 * 
	 * @return	Vaut true si aucunne operation de conversion n'est deja en cours. 
	 */
	public static boolean manage() {
		if(WatchedConsumer.workIsOnGoing()) {
			JOptionPane.showMessageDialog(null, MessageConstants.ERROR_OPERATION_IS_ALREADY_ON_GOING);
			return false;
		}
		return true;
	}
}
