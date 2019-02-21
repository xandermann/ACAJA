package messages;

public final class MessageConstants {
	private static final String ERROR_PREFIX = "ERREUR ";
	
	private static final String INTERNAL_ERROR_PREFIX = ERROR_PREFIX + "EN INTERNE !";
	
	public static final String ERROR_UNFINDABLE_STREAMS =
			INTERNAL_ERROR_PREFIX
			+ "\nLes flux de sorties de FFMPEG sont introuvables !";
	
	public static final String ERROR_ACTIVE_WAIT_FOR =
			INTERNAL_ERROR_PREFIX
			+ "\nL'attente active de la fin du processus fils,"
			+ "\nexecutant FFMPEG en interne,"
			+ "\na ete interrompu et a donc echoue !";
	
	public static final String ERROR_CONVERSION_IS_ALREADY_ON_GOING =
			ERROR_PREFIX + "! Une conversion d'un ou plusieurs fichiers est deja en cours !"
			+ "\nVeuillez attendre la fin de cette operation pour en demarrer une nouvelle !";
}
