package files.enumerations;

/**
 * [ ENUMERATION DES TYPES DE PARAMETRES. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public enum SettingType implements OperationType {
	/**
	 * Parametre de type codec video.
	 */
	VIDEO_CODEC,

	/**
	 * Parametre de type bitrate video.
	 */
	VIDEO_BITRATE,

	/**
	 * Parametre de type FPS de la video.
	 */
	FRAMERATE,

	/**
	 * Parametre de type resolution video.
	 */
	RESOLUTION,
	
	/**
	 * Parametre de type codec audio.
	 */
	AUDIO_CODEC,
	
	/**
	 * Parametre de type taux d'echantillonage.
	 */
	SAMPLING_RATE,
	
	/**
	 * Parametre de type nombre de canaux audio.
	 */
	NUMBER_AUDIO_CHANNELS,

	/**
	 * Parametre de type bitrate audio.
	 */
	AUDIO_BITRATE
}
