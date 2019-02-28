package exceptions;

public final class UnfindableResourceException extends Exception {
	//UAITR = UNFINDABLE_AND_IMPOSSIBLE_TO_RECREATE
	
	private final static String UAITR_PREFIX = "Le repertoire ";
	private final static String UAITR_SUFFIX = " est introuvable et est impossible a recreer !";
	
	public final static String UAITR_IMPORTS = UAITR_PREFIX + "parent des imports" + UAITR_SUFFIX;
	public final static String UAITR_CONVERSION_IMPORTS = UAITR_PREFIX + "des imports pour la conversion" + UAITR_SUFFIX;
	public final static String UAITR_PROCESSING_IMPORTS = UAITR_PREFIX + "des imports pour le traitement" + UAITR_SUFFIX;
	
	public final static String UAITR_ANSWERS = UAITR_PREFIX + "parent des reponses" + UAITR_SUFFIX;
	public final static String UAITR_STDERR_ANSWERS = UAITR_PREFIX + "des reponses stderr" + UAITR_SUFFIX;
	public final static String UAITR_STDOUT_ANSWERS = UAITR_PREFIX + "des reponses stdout" + UAITR_SUFFIX;
	
	public final static String UAITR_TEMPORARY_FILES = UAITR_PREFIX + "des fichiers temporaires" + UAITR_SUFFIX;
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param exception_message		Le message d'erreur.
	 */
	public UnfindableResourceException(String exception_message) {
		super(exception_message);
	}
}
