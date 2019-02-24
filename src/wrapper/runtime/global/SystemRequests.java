package wrapper.runtime.global;

import java.util.HashMap;
import files.*;
import wrapper.runtime.details.*;
import wrapper.streams.iterators.ProcessManager;
import wrapper.streams.managers.filters.*;

/**
 * [ CLASSE POUR LES GESTION DES REQUETES DU SYSTEM. ]
 * 
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
			String metadata = MetadataFilter.findAllMetadata(processManager);
			/**
			 * INITIALISATION DES SETTINGS DU FICHEIR A PARTIR DES METADONNEES.
			 */
			HashMap<SettingType, String> fileMetadatas = file.getSettings();		
			//Parametres a extraire uniquement pour les fichiers video. 
			if(file.isVideo()) {
				fileMetadatas.put(SettingType.VIDEO_CODEC, MetadataFilter.findVideoMetadata(metadata, 0));
				fileMetadatas.put(SettingType.RESOLUTION, MetadataFilter.findVideoMetadata(metadata, 2));	
				fileMetadatas.put(SettingType.VIDEO_BITRATE, MetadataFilter.findVideoMetadata(metadata, 3));
				fileMetadatas.put(SettingType.FRAMERATE, MetadataFilter.findVideoMetadata(metadata, 4));
			}	
		    fileMetadatas.put(SettingType.AUDIO_CODEC, MetadataFilter.findAudioMetadata(metadata, 0));
			fileMetadatas.put(SettingType.SAMPLING_RATE, MetadataFilter.findAudioMetadata(metadata, 1));
			String nbChannels = MetadataFilter.findAudioMetadata(metadata, 2).contains("mono") ? "1" : "2";
			fileMetadatas.put(SettingType.NUMBER_AUDIO_CHANNELS, nbChannels);
			fileMetadatas.put(SettingType.AUDIO_BITRATE, MetadataFilter.findAudioMetadata(metadata, 4));
			file.setDuration(MetadataFilter.findDuration(metadata));
		}
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ CONNAITRE LES CODECS VIDEO SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return	Le tableau des codecs video supportes. 
	 */
	public static String[] askVideoCodecs() {
		return DataCodecsFilter.findVideoCodecs(new Request().codecs().result());
	}
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONNAITRE LES CODECS AUDIO SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return	Le tableau des codecs audio supportes. 
	 */
	public static String[] askAudioCodecs() {
		return DataCodecsFilter.findAudioCodecs(new Request().codecs().result());
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
