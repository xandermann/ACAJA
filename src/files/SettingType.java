package files;

public enum SettingType implements OperationType {
	/**
	 * Constante qui definit le codec video dans la HashMap
	 */
	VIDEO_CODEC,

	/**
	 * Constante qui definit le bitrate video dans la HashMap
	 */
	VIDEO_BITRATE,

	/**
	 * Constante qui definit les FPS de la video dans la HashMap
	 */
	FPS,

	/**
	 * Constante qui definit le codec audio dans la HashMap
	 */
	AUDIO_CODEC,
	
	/**
	 * Constante qui definit le sampling rate dans la HashMap
	 */
	SAMPLING_RATE,
	
	/**
	 * Constante qui definit le nombre de pistes audios dans la HashMap
	 */
	NUMBER_AUDIO_CHANNELS,

	/**
	 * Constante qui definit le bitrate audio dans la HashMap
	 */
	AUDIO_BITRATE,

	/**
	 * Constante qui definit la résolution de la video dans la HashMap
	 */
	VIDEO_RESOLUTION
}
