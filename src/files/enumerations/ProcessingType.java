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
	REMOVED_SOUND, 
	/**
	 * Parametre du type ajout de son.
	 */
	ADDED_SOUND,
	/**
	 * Parametre pour rotation
	 */
	 ROTATED,
	/**
  	 * Parametre pour incruster une image.
	 */
	ADDED_IMAGE
}
