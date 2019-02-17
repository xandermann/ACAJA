package wrapper.wrapper;

import java.io.*;

import java.util.HashMap;

import files.*;
import wrapper.streams.StreamsFilter;

/**
 * [ CLASSE CONCRETE POUR L'INTERFACAGE JAVA-FFMPEG ---- DEGRE 2. ]
 * 
 * Cette classe a pour but de realiser les requetes FFMPEG
 * necessaires au fonctionnement interne de notre logiciel ( d'ou le 
 * de la classe : SystemRequests. ). 
 * 
 * Pour synthetiser, cette classe implemente :
 * 
 *  - des fonctionnalites pour le fonctionnement interne de la 
 *  fenetre de conversion :
 *  	- renseigner les caracteristiques d'une video ou d'un son
 * 	     ( bitrate, format, FPS, resolution, etc ).
 * 
 *  - des fonctionnalites pour le fonctionnement interne de la 
 *  fenetre de traitement :
 *  	- extraire une image d'une video a temps donne ; 
 *  	- renseigner la duree d'une video ;
 *  	- redimmensionner une image ;
 *  	- creer une miniature d'une video 
 *  	  ( pour les miniatures dans la chronologie, ou les miniatures
 *  		dans les bibliotheques ) ;
 *  	- creer une frame pour l'apercu de lecture de video. 
 *
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class SystemRequests extends FFmpegRuntime{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LE RENSEIGNEMENT DE LA DUREE D'UNE VIDEO OU D'UN SON. ]
	 * 
	 * Methode pour connaitre la duree en secondes d'une video ou d'un son. 
	 * 
	 * TODO  iteration 1 pas concernee. 
	 * 
	 * @param file		La video ou le son dont on souhaite connaitre la duree. 
	 * 
	 * @return long 	La duree en secondes du ou de la video. 
	 */
	public static long getDuration(File file) {
		return 0;
	}
	

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LE RENSEIGNEMENT DES PARAMETRES D'UNE VIDEO OU D'UN SON. ]
	 * 
	 * Cette methode retourne les parametres ( = caracteristiques ) du fichier. 
	 * 
	 * Les valeurs des parametres du fichier sont recuperes par l'intrermediaire de requetes FFmpeg. 
	 * 
	 * Un fichier video posssede des parametres video ET audio alors qu'un fichier audio 
	 * ne possede que des parametres audio. 
	 * 
	 * Parametres video : le codec video, le bitrate video, la resolution, les nombre d'images par 
	 * secondes (FPS). 
	 * 
	 * Paramteres audio : le codec audio, le bitrate audio, le volume en sortie, taux d'echantillonnage,
	 * nombre de canaux audio en sortie. 
	 * 
	 * @param file							Le fichier dont on souhaite connaitre les parametres. 
	*/
	public static void getSettings(SettingsFile file){
		if(file.containsAudio()){	
			/**
			 * REQUETE A SOUMETRE A FFMPEG.
			 * 
			 * LA requete ffmpeg -i inputFile permet de connaitre les caracteristiques
			 * d'un fichier fourni en fliux d'entree ( = inputFile ). 
			 */
			ProcessManager processManager = FFmpegRuntime.execute(file.getSourceFile().getPath());
			
			/**
			 * EXTRACTION DES DONNEES.
			 */
			String informations = StreamsFilter.findInformationsOfMediaFile(processManager);
			
			
			/**
			 * INITIALISATION DES PARAMETRES DE LA VIDEO OU DU SON.
			 */
			HashMap<SettingType, String> fileSettings = file.getSettings();
		
			
			//Parametres a extraire uniquement pour les fichiers video. 
			String codec = file.getSourceFile().getName().split("[.]")[file.getSourceFile().getName().split("[.]").length-1];
			if(file.isVideo()) {
				fileSettings.put(SettingType.VIDEO_CODEC, codec);
						
				fileSettings.put(SettingType.VIDEO_RESOLUTION, StreamsFilter.findVideoSetting(informations, 2));
				
				fileSettings.put(SettingType.VIDEO_BITRATE, StreamsFilter.findVideoSetting(informations, 3));
				fileSettings.put(SettingType.FPS, StreamsFilter.findVideoSetting(informations, 4));
				fileSettings.put(SettingType.AUDIO_CODEC, StreamsFilter.findAudioSetting(informations, 0));
			}else
				fileSettings.put(SettingType.AUDIO_CODEC, codec);
			
			fileSettings.put(SettingType.SAMPLING_RATE,  StreamsFilter.findAudioSetting(informations, 1));
			
			if(StreamsFilter.findAudioSetting(informations, 2).contains("mono"))
				fileSettings.put(SettingType.NUMBER_AUDIO_CHANNELS, "1");
			else
				fileSettings.put(SettingType.NUMBER_AUDIO_CHANNELS, "2");
			
			fileSettings.put(SettingType.AUDIO_BITRATE, StreamsFilter.findAudioSetting(informations, 4));
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
