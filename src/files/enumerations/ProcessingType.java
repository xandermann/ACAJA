package files.enumerations;

/**
 * [ ENUMERATION DES TYPES DE TRAITEMENTS. ]
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public enum ProcessingType implements OperationType {
	/**
	 * Parametre du type de qualite.
	 */
	QUALITY,
	/**
	 * Parametre du type rogne.
	 */
	CROPED,
	/**
	 * Parametre du type coupe.
	 */
	CUT,
	/**
	 * Parametre du type floute.
	 */
	BLURRED,
	/**
	 * Parametre du type concatene.
	 */
	ADDED,
	/**
	 * Parametre du type suppression du son.
	 */
	REMOVE_SOUND, 
	/**
	 * Parametre du type ajout de son.
	 */
	ADD_SOUND
}
