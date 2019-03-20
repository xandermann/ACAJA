package gui.alerts;

/**
 * [ INTERFACE POUR LES NOTIFICATIONS / FEEDBACKS. ]
 * 
 * Cette interface a pour but de repertorier les
 * cosntantes des alertes. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public interface AlertTypeConstants {	
	final static String _INFO = "(i) information";
	
	final static String _FAILURE = "echec";

	final static String _SUCCESS = "reussite";
	
	
	public final static String INFO = _INFO+".";
	
	public final static String FAILURE = _INFO+" - "+_FAILURE+".";
	
	public final static String SUCCESS = _INFO+" - "+_SUCCESS+".";
}
