package gui.alerts;
import gui.general.GeneralWindow;
/**
 * [ CLASSE FENETRE DE LA VUE DE CHOIX DES PARAMETRES DES ALERTES. ]
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue de choix des parametres des alertes.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class AlertSettingsWindow extends GeneralWindow{
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public AlertSettingsWindow(){
		super("Choix des parametres des notifications.", new AlertSettingsView());
	}
}
