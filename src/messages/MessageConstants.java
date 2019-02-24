package messages;

public final class MessageConstants {
	private static final String ERROR_PREFIX = "ERREUR ";

	private static final String INTERNAL_ERROR_PREFIX = ERROR_PREFIX + "EN INTERNE !";

	public static final String ERROR_UNFINDABLE_STREAMS = INTERNAL_ERROR_PREFIX
			+ "\nLes flux de sorties de FFMPEG sont introuvables !";

	public static final String ERROR_ACTIVE_WAIT_FOR = INTERNAL_ERROR_PREFIX
			+ "\nL'attente active de la fin du processus fils," + "\nexecutant FFMPEG en interne,"
			+ "\na ete interrompu et a donc echoue !";

	public static final String ERROR_OPERATION_IS_ALREADY_ON_GOING = ERROR_PREFIX
			+ "! Une operation est deja en cours sur un ou plusieurs fichiers !"
			+ "\nVeuillez attendre la fin de cette operation pour en demarrer une nouvelle !";
}
