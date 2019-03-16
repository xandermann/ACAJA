package messages;

/**
 * [ INTERFACE POUR LES NOTIFICATIONS / FEEDBACKS. ]
 * 
 * Cette interface a pour but de repertorier les messages
 * de feedback a la maniere d'un dictionnaire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public interface NotificationConstants {
	static final String _INFO = "(i) information";
	
	static final String _FAILURE = "echec";

	static final String _SUCCESS = "reussite";

	public static final String INFO = _INFO+".";
	
	public static final String FAILURE = _INFO+" - "+_FAILURE+".";
	
	public static final String SUCCESS = _INFO+" - "+_SUCCESS+".";
}
