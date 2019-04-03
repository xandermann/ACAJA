package gui.general;
/**
 * [ CLASSE POUR LA GESTION DU FOCUS. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class Focus {
	/**
	 * [ CONSTRUIT UN NOUVEAU FOCUS. ]
	 */
	public Focus() {
		/**
		 * RENDRE LE FOCUS A L'EVENEMENT PARENT.
		 */
		if(Context.$F.isEmpty()) {
			if(Context.$W != null) 
				Context.$W.requestFocus();
		} else 
			Context.$F.peek().requestFocus();
	}
}
