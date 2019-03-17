package threads;
import resources.Hand;

/**
 * [ CLASSE POUR LA GESTION DE L'ESPACE D'EXECUTION POUR LES THREADS. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class RuntimeSpaceManager {
	/**
	 * [ MAIN SUR LA RESSOURCE : L'ESPACE D'EXECUTION. ]
	 * 
	 * Ce gestionnaire possede une main, qu'on peut prendre et rendre
	 * depuis l'interieur comme depuis l'exterieur de la classe.
	 * Une main est un genre de verrou, pris en compte par tous 
	 * les processus du fil d'execution du logiciel.
	 */
	public static Hand hand = new Hand();

	/**
	 * [ GESTIONNAIRE DE L'ESPACE D'EXECUTION. ]
	 * 
	 * @return	Vaut true si aucunne operation de conversion n'est deja en cours. 
	 */
	public static boolean manage() {
		//JOptionPane.showMessageDialog(null, MessageConstants.ERROR_OPERATION_IS_ALREADY_ON_GOING);
		return !hand.took();
	}
}
