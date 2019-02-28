package files.enumerations;

/**
 * [ ENUMERATION DES TYPES DE PARAMETRES. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public enum SettingType implements OperationType {
	/**
	 * Parametre du type de qualite. 
	 */
	QUALITY,
	/**
	 * Parametre du type de codec video.
	 */
	VIDEO_CODEC,

	/**
	 * Parametre du type de bitrate video.
	 */
	VIDEO_BITRATE,

	/**
	 * Parametre du type de FPS de la video.
	 */
	FRAMERATE,

	/**
	 * Parametre du type de resolution video.
	 */
	RESOLUTION,
	
	/**
	 * Parametre du type de codec audio.
	 */
	AUDIO_CODEC,
	
	/**
	 * Parametre du type de taux d'echantillonage.
	 */
	SAMPLING_RATE,
	
	/**
	 * Parametre du type de nombre de canaux audio.
	 */
	NUMBER_AUDIO_CHANNELS,

	/**
	 * Parametre du type de bitrate audio.
	 */
	AUDIO_BITRATE
}
