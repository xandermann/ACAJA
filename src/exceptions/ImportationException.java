package exceptions;
public final class ImportationException extends Exception {
	public final static String ABSENT_FILE = "Aucun fichier selectionne !";
	public final static String ABSENT_DIRECTORY = "Aucun dossier selectionne !";
	
	public ImportationException(String exception_message) {
		super(exception_message);
	}
}
