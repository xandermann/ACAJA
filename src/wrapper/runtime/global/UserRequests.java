package wrapper.runtime.global;

import java.util.*;
import files.*;
import wrapper.runtime.details.Request;


/**
 * [ CLASSE POUR LES GESTION DES REQUETES DE L'UTILISATEUR. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class UserRequests{	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE POUR L'EXECUTION DES REQUETES D'UNE CONVERSION. ]
	 * 
	 * @param file			Le fichier contentant les requetes et sur lequel les realiser. 
	 */
	private static void executeSettingsFile(SettingsFile file) {
		if(file == null) throw new NullPointerException("SettingsFile null!");
		
		Request request = new Request(file.getFullName(), "ff.avi");
		
		HashMap<SettingType, String> ffmpegRequests = file.getRequests();
		
		for(SettingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			switch(requestKey) {
				case VIDEO_CODEC :
					System.out.println("passe 1");
					request.videoCodec(newValue);
					break;
				case VIDEO_BITRATE : 
					System.out.println("pasvevese 1");
					request.videoBitrate(newValue);
					break;
				case FRAMERATE :
					System.out.println("paseggese 1");
					request.framerate(newValue);
					break;
				case RESOLUTION :
					System.out.println("piguiguasse 1");
					request.resolution(newValue);
					break;
				case AUDIO_CODEC : 
					System.out.println("pahohsse 1");
					request.audioCodec(newValue);
					break;
				case AUDIO_BITRATE : 
					System.out.println("pahohsse 1");
					request.audioBitrate(newValue);
					break;
				case SAMPLING_RATE : 
					System.out.println("pefeffasse 1");
					request.samplingRate(newValue);
					break;
				case NUMBER_AUDIO_CHANNELS : 
					System.out.println("passbbbe 1");
					request.numberAudioChannels(newValue);
					break;
			}
		}
		
		request.make();
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE POUR L'EXECUTION DES REQUETES D'UN TRAITEMENT. ]
	 * 
	 * @param file			Le fichier contentant les requetes et sur lequel les realiser. 
	 */
	private static void executeProcessingFile(ProcessingFile file) {
		if(file == null) throw new NullPointerException("ProcessingFile null!");
		
		Request request = new Request(file.getFullName(), file.getFullName());
		
		HashMap<ProcessingType, String> ffmpegRequests = file.getPerformedProcessings();
		
		for(ProcessingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			switch(requestKey) {}
		}
		
		request.make();
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
