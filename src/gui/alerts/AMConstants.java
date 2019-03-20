package gui.alerts;

/**
 * [ INTERFACE POUR LES MESSAGES. ]
 * 
 * AM = AlertMessage.
 * 
 * Cette interface a pour but de repertorier les messages
 * a la maniere d'un dictionnaire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public interface AMConstants {
	
	static final String ERROR_PREFIX = "ERREUR ";

	static final String INTERNAL_ERROR_PREFIX = ERROR_PREFIX + "EN INTERNE !";

	public static final String ERROR_UNFINDABLE_STREAMS = 
			INTERNAL_ERROR_PREFIX
			+ "\nLes flux de sorties de FFMPEG sont introuvables !";

	public static final String ERROR_ACTIVE_WAIT_FOR = 
			INTERNAL_ERROR_PREFIX
			+ "\nL'attente active de la fin du processus fils," + "\nexecutant FFMPEG en interne,"
			+ "\na ete interrompu et a donc echoue !";

	public static final String ERROR_OPERATION_IS_ALREADY_ON_GOING = 
			ERROR_PREFIX
			+ "! Une operation est deja en cours sur un ou plusieurs fichiers !"
			+ "\nVeuillez attendre la fin de cette operation pour en demarrer une nouvelle !";
	
	public static final String ERROR_SAVE_IMPORTS = 
			INTERNAL_ERROR_PREFIX
			+ "Impossible de creer les dossiers de sauvegarde des imports ! "
			+ "Merci de verifier les permissions du repertoire d'installation d'acaja.";
	
	public static final String ERROR_UNFINDABLE_FILE_TO_REMOVE = 
			INTERNAL_ERROR_PREFIX
			+ "Le fichier a supprimer n'est pas present dans la bibliotheque !";
	
	public static final String ERROR_ABSENT_SELECTED_FILE = 
			ERROR_PREFIX
			+ "! Le fichier selectionne n'existe pas !";
}
