package wrapper.runtime.global;

import java.io.File;
import java.util.HashMap;
import javax.swing.JOptionPane;
import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.SettingType;
import files.files.ProcessingFile;
import files.files.SelectableFile;
import files.files.SettingsFile;
import resources.NamesSpaceManager;
import wrapper.runtime.details.*;
import wrapper.streams.managers.filters.*;

/**
 * [ CLASSE POUR LES GESTION DES REQUETES DU SYSTEME. ]
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
	
	
	public static String askResolution(ProcessingFile file) throws IncorrectFileException, UnfindableResourceException{
		if(file==null) throw new NullPointerException("File null !");
		if(!file.isVideo()) throw new IncorrectFileException(IncorrectFileException.REQUIRED_TYPE_VIDEO);
		return MetadataFilter.findVideoMetadata(MetadataFilter.findAllMetadata((new Request(file.getSourceFileFullName())).result()), 2);	
	}
	
	
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
	 * @param file									Le fichier dont on souhaite connaitre les parametres. 
	 * 
	 * @throws IncorrectFileException 				Exception sur les ressources introuvables.  
	 * 
	 * @throws UnfindableResourceException			Exception sur les ressources introuvables.
	*/
	public static void askMetadata(SettingsFile file) throws IncorrectFileException, UnfindableResourceException{
		if(file==null) throw new NullPointerException("File null !");
		if(!file.containsAudio()) throw new IncorrectFileException(IncorrectFileException.REQUIRED_TYPE_VIDEO_OR_SOUND);
		/**
		 * EXTRACTION DES DONNEES.
		 */
		String metadata = MetadataFilter.findAllMetadata((new Request(file.getSourceFileFullName())).result());
		/**
		 * INITIALISATION DES SETTINGS DU FICHEIR A PARTIR DES METADONNEES.
		 */
		HashMap<SettingType, String> fileMetadata = file.getSettings();		
		//Parametres a extraire uniquement pour les fichiers video. 
		if(file.isVideo()) {
			fileMetadata.put(SettingType.VIDEO_CODEC, MetadataFilter.findVideoMetadata(metadata, 0));
			fileMetadata.put(SettingType.RESOLUTION, MetadataFilter.findVideoMetadata(metadata, 2));	
			fileMetadata.put(SettingType.VIDEO_BITRATE, MetadataFilter.findVideoMetadata(metadata, 3));
			fileMetadata.put(SettingType.FRAMERATE, MetadataFilter.findVideoMetadata(metadata, 4));
		}	
	    fileMetadata.put(SettingType.AUDIO_CODEC, MetadataFilter.findAudioMetadata(metadata, 0));
		fileMetadata.put(SettingType.SAMPLING_RATE, MetadataFilter.findAudioMetadata(metadata, 1));
		String nbChannels = MetadataFilter.findAudioMetadata(metadata, 2).contains("mono") ? "1" : "2";
		fileMetadata.put(SettingType.NUMBER_AUDIO_CHANNELS, nbChannels);
		fileMetadata.put(SettingType.AUDIO_BITRATE, MetadataFilter.findAudioMetadata(metadata, 4));
		file.setDuration(MetadataFilter.findDuration(metadata));
	}
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ CONNAITRE LES CODECS VIDEO SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return	Le tableau des codecs video supportes. 
	 */
	public static String[] askVideoCodecs() {
		try {
			return DataCodecsFilter.findVideoCodecs(new Request().codecs().result());
		} catch (UnfindableResourceException ure) {
			JOptionPane.showMessageDialog(null, ure.getMessage());
			return null;
		}
	}
	
	
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONNAITRE LES CODECS AUDIO SUPPORTES PAR FFMPEG. ]
	 * 
	 * @return	Le tableau des codecs audio supportes. 
	 */
	public static String[] askAudioCodecs() {
		try {
			return DataCodecsFilter.findAudioCodecs(new Request().codecs().result());
		} catch (UnfindableResourceException ure) {
			JOptionPane.showMessageDialog(null, ure.getMessage());
			return null;
		}
	}
	
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ OBTENIR UNE FRAME D'UNE VIDEO A INSTANT PRECIS. ]
	 * 
	 * @param file		La video.
	 * 
	 * @param time		L'instant precis ou extraire la frame. 
	 * 
	 * @return	La frame. 
	 * 
	 * @throws IncorrectFileException			Exception sur les fichiers incorrects. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables.  
	 */
	public static File askFrame(SelectableFile file, String time) throws IncorrectFileException, UnfindableResourceException {
		if(file==null) throw new NullPointerException("File null !");
		if(!file.isVideo()) throw new IncorrectFileException(IncorrectFileException.REQUIRED_TYPE_VIDEO);
		if(!file.isVideo()) throw new IllegalArgumentException("SelectableFile null !");
		if(time==null) throw new NullPointerException("Time null !");
		
		String input = file.getSourceFileFullName();
		String output = NamesSpaceManager._temporary();
		(new Request(input, output)).frame(time).make();
		
		return (new File(output));
	}
	
	/**
	 * [ OBTENIR UNE FRAME D'UNE VIDEO A UN INSTANT PRECIS ET LA REDIMMENSIONNER. ]
	 * 
	 * @param file		La video.
	 * 
	 * @param time		L'instant precis ou extraire la frame. 
	 * 
	 * @return	La frame redimensionnee. 
	 * 
	 * @throws IncorrectFileException			Exception sur les fichiers incorrects. 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static File askFrame(SelectableFile file, String time, int width, int height) throws IncorrectFileException, UnfindableResourceException {
		if(file==null) throw new NullPointerException("File null !");
		if(!file.isVideo()) throw new IncorrectFileException(IncorrectFileException.REQUIRED_TYPE_VIDEO);
		if(time==null) throw new NullPointerException("Time null !");
		if(width<=0) throw new IllegalArgumentException("Width negative ou nulle !");
		if(height<=0) throw new IllegalArgumentException("Height negative ou nulle !");
		
		String input = file.getSourceFileFullName();
		String output = NamesSpaceManager._temporary();
		(new Request(input, output)).frame(time).resize(width+"", height+"").make();
		
		return (new File(output));
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
