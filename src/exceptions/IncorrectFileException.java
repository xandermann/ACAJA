package exceptions;

/**
 * [ EXCEPTION PERSONNALISEE. ]
 * 
 * Cette exception personnalisee correpond aux cas ou au cours de la creation
 * d'un objet de la famille des SelectableFile, le fichier source fourni de type
 * File ne correpond pas aux exigences de classe de l'objet.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class IncorrectFileException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1481249129366968765L;
	/**
	 * [ CONSTANTES DE CLASSE. ]
	 * 
	 * Messages d'erreur possibles.
	 */
	public final static String FORBIDDEN_FILE = "Extension du fichier non conforme !";
	public final static String BAD_TYPE_FILE = "Seuls les fichiers audio et video sont acceptes !";
	public final static String REQUIRED_TYPE_VIDEO = "Le fichier devrait etre un fichier video !";
	public final static String REQUIRED_TYPE_VIDEO_OR_SOUND = "Le fichier devrait etre un fichier video ou audio !";

	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param exception_message Le message d'erreur.
	 */
	public IncorrectFileException(String exception_message) {
		super(exception_message);
	}
}
