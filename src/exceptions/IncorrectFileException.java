package exceptions;
public final class IncorrectFileException extends Exception {
	
	public final static String FORBIDDEN_FILE = "Extension du fichier non conforme !";
	public final static String BAD_TYPE_FILE = "Seuls les fichiers audio et video sont acceptes !";
	
	public IncorrectFileException(String exception_message) {
		super(exception_message);
	}
}
