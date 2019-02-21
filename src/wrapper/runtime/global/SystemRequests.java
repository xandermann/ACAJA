package wrapper.runtime.global;

import java.io.*;
import java.util.HashMap;
import files.*;
import wrapper.runtime.details.FFmpegRuntime;
import wrapper.runtime.details.Request;
import wrapper.streams.ProcessManager;
import wrapper.streams.StreamsFilter;

/**
 * Cette classe a pour but de realiser les requetes FFMPEG
 * necessaires au fonctionnement interne de notre logiciel ( d'ou le 
 * de la classe : SystemRequests. ). 
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class SystemRequests{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LE RENSEIGNEMENT DES METADONNEES D'UNE VIDEO OU D'UN SON. ]
	 * 
	 * Les valeurs des metadonnees du fichier sont recuperes par l'intermediaire d'une simple requete FFmpeg. 
	 * 
	 * Un fichier video posssede des metadonnees video ET audio alors qu'un fichier audio 
	 * ne possede que des metadonnees audio. 
	 * 
	 * Metadonnees video : le codec video, le bitrate video, la resolution, les nombre d'images par 
	 * secondes (FPS). 
	 * 
	 * Metadonnees audio : le codec audio, le bitrate audio, le volume en sortie, taux d'echantillonnage,
	 * nombre de canaux audio en sortie. 
	 * 
	 * @param file							Le fichier dont on souhaite connaitre les parametres. 
	*/
	public static void askMetadata(SettingsFile file){
		if(file.containsAudio()){	
			/**
			 * REQUETE A SOUMETRE A FFMPEG.
			 */
			ProcessManager processManager = (new Request(file.getFullName())).result();
			/**
			 * EXTRACTION DES DONNEES.
			 */
			String informations = StreamsFilter.findInformationsOfMediaFile(processManager);
			/**
			 * INITIALISATION DES PARAMETRES DE LA VIDEO OU DU SON.
			 */
			HashMap<SettingType, String> fileSettings = file.getSettings();		
			//Parametres a extraire uniquement pour les fichiers video. 
			if(file.isVideo()) {
				fileSettings.put(SettingType.VIDEO_CODEC, StreamsFilter.findVideoSetting(informations, 0));
				fileSettings.put(SettingType.RESOLUTION, StreamsFilter.findVideoSetting(informations, 2));	
				fileSettings.put(SettingType.VIDEO_BITRATE, StreamsFilter.findVideoSetting(informations, 3));
				fileSettings.put(SettingType.FRAMERATE, StreamsFilter.findVideoSetting(informations, 4));
			}	
		    fileSettings.put(SettingType.AUDIO_CODEC, StreamsFilter.findAudioSetting(informations, 0));
			fileSettings.put(SettingType.SAMPLING_RATE, StreamsFilter.findAudioSetting(informations, 1));
			String nbChannels = StreamsFilter.findAudioSetting(informations, 2).contains("mono") ? "1" : "2";
			fileSettings.put(SettingType.NUMBER_AUDIO_CHANNELS, nbChannels);
			fileSettings.put(SettingType.AUDIO_BITRATE, StreamsFilter.findAudioSetting(informations, 4));
			file.setDuration(StreamsFilter.findDuration(informations));
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
