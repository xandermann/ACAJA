package exceptions;
/**
 * [ EXCEPTION PERSONNALISEE. ]
 * 
 * Cette exception personnalisee correpond aux cas 
 * ou au cours d'un import, aucun import n'est realise.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class ImportationException extends Exception {
	/**
	 * [ CONSTANTES DE CLASSE. ]
	 * 
	 * Messages d'erreur possibles. 
	 */
	public final static String ABSENT_FILE = "Aucun fichier selectionne !";
	public final static String ABSENT_DIRECTORY = "Aucun dossier selectionne !";
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param exception_message		Le message d'erreur.
	 */
	public ImportationException(String exception_message) {
		super(exception_message);
	}
}
