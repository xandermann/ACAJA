package wrapper.language;

import wrapper.runtime.global.SystemRequests;

/**
 * [ CLASSE POUR ACCEDER AUX CODECS SUPPORTES PAR FFMPEG. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class CodecConstants {
	public final static String[] ALL_SUPPORTED_VIDEO_CODECS = SystemRequests.askVideoCodecs();
	public final static String[] ALL_SUPPORTED_AUDIO_CODECS = SystemRequests.askAudioCodecs();
}
