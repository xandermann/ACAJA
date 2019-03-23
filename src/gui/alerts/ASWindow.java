package gui.alerts;
import gui.general.Context;
import gui.general.GeneralWindow;
/**
 * [ CLASSE FENETRE DE LA VUE DE CHOIX DES PARAMETRES DES ALERTES. ]
 * 
 * 	AS = AlertSettings.
 * 
 * Cette classe permet par le biais du constructeur de generer
 * une fenetre contenant la vue de choix des parametres des alertes.
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ASWindow extends GeneralWindow{
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public ASWindow(){
		super("Choix des parametres des notifications.", new ASView());
		/**
		 * THIS DETIENT LE FOCUS ET SERA UN COMPONENT PARENT.
		 */
		Context.$C(0, this);
	}
}
