package wrapper.runtime.global;

import java.util.HashMap;

import exceptions.UnfindableResourceException;
import files.enumerations.ProcessingType;
import files.enumerations.SettingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import files.files.SettingsFile;
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
	 * @param file								Le fichier contentant les requetes et sur lequel les realiser. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	private static void executeSettingsFile(SettingsFile file) throws UnfindableResourceException {
		if(file == null) throw new NullPointerException("SettingsFile null!");
		
		Request request = new Request(file.getSourceFileFullName(), file.getDestinationFileFullName());

		HashMap<SettingType, String> ffmpegRequests = file.getRequests();
		
		for(SettingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			switch(requestKey) {
				case QUALITY :
					request.quality(newValue);
					break;
				
				case VIDEO_CODEC :
					request.videoCodec(newValue);
					break;
				
				case VIDEO_BITRATE : 
					request.videoBitrate(newValue);
					break;
				
				case FRAMERATE :
					request.framerate(newValue);
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
		
		request.make();
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE DE CLASSE POUR L'EXECUTION DES REQUETES D'UN TRAITEMENT. ]
	 * 
	 * @param file								Le fichier contentant les requetes et sur lequel les realiser. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	private static void executeProcessingFile(ProcessingFile file) throws UnfindableResourceException {
		if(file == null) throw new NullPointerException("ProcessingFile null!");
		
		Request request = new Request(file.getSourceFileFullName(), file.getDestinationFileFullName());
		
		HashMap<ProcessingType, String> ffmpegRequests = file.getPerformedProcessings();
		
		for(ProcessingType requestKey : ffmpegRequests.keySet()) {
			String newValue = ffmpegRequests.get(requestKey);
			System.out.println("Val :" + newValue);
			
			switch(requestKey) {
				case QUALITY :
					request.quality(newValue);
					break;
				
				case CROPED :
					String[] tab = newValue.split("[|]");
					request.crop(tab[0],tab[1],tab[2],tab[3]);
					break;
				
				case CUT :
					//request.cut(newValue);
					break;
				
				case BLURRED :
					String[] tab2 = newValue.split("[|]");
					request.blur(tab2[0],tab2[1],tab2[2],tab2[3]);
					break;
				
				case ADDED :
					String[] tab3 = newValue.split("[|]");
					String[] tabres = new String[tab3.length-1];
					for(int i = 1;i<tab3.length;i++)
						tabres[i-1] = tab3[i];
					request.concat(tabres);
					break;
				
				case REMOVED_SOUND :
					request.removeSound();
				break;
				
				case ADDED_SOUND :
					request.addSound(newValue);
				break;
				
				case ROTATED :
					if(newValue.equals("left"))	request.rotateLeft();
					if(newValue.equals("right")) request.rotateRight();
					if(newValue.equals("180right")) request.rotate180Right();			
					if(newValue.equals("180left")) request.rotate180Left();
					if(newValue.equals("180")) request.rotate180();
				break;
				
				case ADDED_IMAGE :
					String[] tab4 = newValue.split("[|]");
					request.addImage(tab4[0], tab4[1], tab4[2]);
				break;
			}
			
		}
		
		request.make();
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour appliquer des modifications sur un ficheir media. 
	 * 
	 * @param file 								Le fichier sur lequel il faut realiser les modifications. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void execute(SelectableFile file) throws UnfindableResourceException {
		if(file == null) throw new NullPointerException("SelectableFile null!");
		if(file instanceof SettingsFile)
			executeSettingsFile((SettingsFile) file);
		else
			executeProcessingFile((ProcessingFile) file);
	}
	
	
	//==================================================================================================================================================
}
