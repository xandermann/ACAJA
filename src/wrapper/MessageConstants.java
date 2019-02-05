package wrapper;

public final class MessageConstants {
	private static final String FAILURE_SUFFIX = "ERREUR EN INTERNE!";
	
	public static final String FALIURE_UNFINDABLE_STREAMS =
			FAILURE_SUFFIX
			+ "\nLes flux de sorties de FFMPEG sont introuvables !";
	
	public static final String FALIURE_ACTIVE_WAIT_FOR =
			FAILURE_SUFFIX
			+ "\nL'attente active de la fin du processus fils,"
			+ "\nexecutant FFMPEG en interne,"
			+ "\na ete interrompu et a donc echoue !";
}
