package wrapper.runtime.global;

import java.util.*;
import files.*;
import wrapper.runtime.details.FFmpegRuntime;
import wrapper.runtime.details.Request;
import wrapper.streams.WatchedConsumer;

/**
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests{	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * @param file
	 */
	private static void executeSettingsFile(SettingsFile file) {
		Request request = new Request(file.getFullName(), file.getFullName());
		
		HashMap<SettingType, String> ffmpegRequests = file.getRequests();
		
		for(SettingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			switch(requestKey) {
				case VIDEO_CODEC : 
					request.videoCodec(newValue);
					break;
				case VIDEO_BITRATE : 
					request.videoBitrate(newValue);
					break;
				case FPS :
					request.fps(newValue);
					break;
				case RESOLUTION :
					request.resolution(newValue);
					break;
				case AUDIO_CODEC : 
					request.audioCodec(newValue);
					break;
				case AUDIO_BITRATE : 
					request.audioBitrate(newValue);
					break;
				case SAMPLING_RATE : 
					request.samplingRate(newValue);
					break;
				case NUMBER_AUDIO_CHANNELS : 
					request.numberAudioChannels(newValue);
					break;
			}
		}
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * @param file
	 */
	private static void executeProcessingFile(ProcessingFile file) {
		Request request = new Request(file.getFullName(), file.getFullName());
		
		HashMap<ProcessingType, String> ffmpegRequests = file.getPerformedProcessings();
		
		for(ProcessingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			switch(requestKey) {
			}
		}
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 		La fichier sur lequel il faut realiser les modifications. 
	 */
	public static void execute(SelectableFile file) {
		if(file == null) throw new NullPointerException("SelectableFile null!");
		if(file instanceof SettingsFile) 
			executeSettingsFile((SettingsFile) file);
		else
			executeProcessingFile((ProcessingFile) file);
	}
	
	//==================================================================================================================================================
}
