package gui.answers;

import gui.general.*;

/**
 * [ CLASSE FENETRE DE LA VUE DES REPONSES. ]
 * 
 * Cette classe permet par le biais du constructeur de generer une fenetre
 * contenant la vue des reponses.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class AnswersWindow extends GeneralWindow {
	/**
	 * [ CONSTRUCTEUR VIDE. ]
	 */
	public AnswersWindow() {
		super("Historique des reponses de FFmpeg.", new AnswersView());
		/**
		 * THIS DETIENT LE FOCUS ET SERA UN COMPONENT PARENT.
		 */
		Context.$C(0, this);
	}
}
