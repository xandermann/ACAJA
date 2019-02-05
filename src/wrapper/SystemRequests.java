package wrapper;

import java.io.*;

import java.util.HashMap;

import files.*;

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
	 * [ CONSTANTES DE CLASSES A USAGE INTERNE. ]
	 * 
	 * Voici des constantes qui nous seront bien pratiques. 
	 * Pour la majorite, ce sont des contantes representant des types de resolutions 
	 * ( par exemple : resolution d'une frame de l'apercu de lecture de video du logiciel ).
	 * 
	 * Ces constantes ne seront destines a etre utilises que pour le fonctionnement
	 * interne de la fenetre de traitement.  
	 * 
	 * Elles sont destinees a un usage uniquement interne ( d'ou l presence des private ). 
	 */

	/**
	 * [ CHEMIN VERS LE REPERTOIRE DES FICHIERS TEMPORAIRES. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * Ceci est le chemin vers le repertoire des fichiers temporaires. 
	 * Ces fameux ficheirs temporaires seront en fait les miniatures de video, 
	 * et les frames de d'apercu de lecture de video, etc.
	 */
	private final static String PATH_TEMPORARY_FILES = System.getProperty("user.dir")+"\\temporary_files\\";

	/**
	 * [ RESOLUTION DES MINIATURES DANS LES BIBLIOTHEQUES. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * Ceci est le tableau representant la resolution des miniatutres des videos, 
	 * des sons, et des images qui seront importes par l'utilisateur dans le logiciel. 
	 */
	private final static int[] LIBRARY_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * [ RESOLUTION DES MINIATURES DES VIDEO DANS LA CHRONOLOGIE VIDEO. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * Ceci est le tableau representant la resolution des miniatutres des videos,
	 * dans la chronologie video. 
	 */
	private final static int[] VIDEO_TIMELINE_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * [ RESOLUTION DES MINIATURES DES SONS DANS LES CHRONOLOGIES DES SONS. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * Ceci est le tableau representant la resolution des miniatutres des sons,
	 * dans les chronologies des sons. 
	 */
	private final static int[] SOUND_TIMELINE_THUMBAIL_RESOLUTION = new int[] {};

	/**
	 * [ RESOLUTION DES FRAMES DANS L'APERCU DE LECTURE DE VIDEO. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * Ceci est le tableau representant la resolution frames dans l'apercu
	 * de lecture de video de la fenetre de traitement. 
	 */
	private final static int[] VIDEO_RESOLUTION = new int[] {};

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode pour simplement extraire une image 
	 * d'une video sans redimensionnement. 
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file		La video dont on doit extraire 
	 * 					l'image. 
	 * 
	 * @param time		Le temps auquel on doit extraire l'image.
	 * 
	 * @return file		L'image extraite.
	 */
	private static File extractImage(File file, long time) {
		return file;
	}

	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode de classe pour redimensionner une image 
	 * extraite. 
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file			L'image extraite a redimensionner. 
	 * 
	 * @param resolution	La constante de classe 
	 * 						representrant les dimensions
	 * 						attendues pour la miniature. 
	 * 
	 * @return file			L'image extraite redimmensionnee. 
	 */
	private static File resizeImage(File file, int[] resolution) {
		return file;
	}
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode de classe pour determiner une resolution proche 
	 * d'une resolution referencee ( constante de classes )
	 * qui puisse etre adaptee a une image, sans la deformer. 
	 * 
	 * TODO  iteration 1 pas concernee. 
	 * 
	 * @param file			L'image pour laquelle on cherche une resolution plus adaptee. 
	 * 
	 * @param resolution	La resolution referencee demandee a adapter pour l'image. 
	 * 
	 * @return int[]		La nouvelle resolution trouvee plus adaptee a l'image. 
	 */
	private static int[] findGoodResolution(File file, int[] resolution) {
		return resolution;
	}
	
	/**
	 * [ METHODE INTERNE DE CLASSE. ]
	 * 
	 * Methode pour verifier qu'une resolution donnee
	 * est une resolution refrencee ( constante de 
	 * classe ). 
	 * 
	 * TODO  iteration 1 pas concernee. 
	 * 
	 * @param resolution		La resolution a verifier. 
	 * 
	 * @return boolean			Vaut true si la resolution fournie
	 * 							en parametre est referencee. 
	 */
	private static boolean resolutionExisting(int[] resolution) {
		return false;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode pour creer une frame de l'apercu de lecture de la video. 
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file		La video dont on doit extraire la frame,
	 * 					et par la suite la redimensionner. 
	 * 
	 * @param time		Le temps auquel on doit extraire la frame
	 * 					de la vide od'origine.
	 * 
	 * @return file		La frame creee. 
	 */
	public static File createVideoFrame(File file, long time) {
		return file;
	}

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * Methode creer une miniature pour une video. 
	 * Cette miniature peut etre aussi destiner 
	 * a un usage dans les bibliotheques que 
	 * dans les chronologies. 
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file			La video pour laquelle on doit 
	 * 						extraire une miniature.
	 * 
	 * @param resolution	La constante de classe 
	 * 						representrant les dimensions
	 * 						attendues pour la miniature. 
	 * 
	 * @return file			La miniature cree. 		
	 */
	public static File createThumbail(File file, int[] resolution) {
		return file;
	}
	
	
	
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
			HashMap<SettingType, Object> fileSettings = file.getSettings();
		
			
			//Parametres a extraire uniquement pour les fichiers video. 
			String codec = file.getSourceFile().getName().split("[.]")[file.getSourceFile().getName().split("[.]").length-1];
			if(file.isVideo()) {
				fileSettings.put(SettingType.VIDEO_CODEC, codec);
				
				String[] resolutionStr = StreamsFilter.findVideoSetting(informations, 2).split("x");
				Integer[] resolutionInt = new Integer[2]; 
				resolutionInt[0] = Integer.parseInt(resolutionStr[0]);
				resolutionInt[1] = Integer.parseInt(resolutionStr[1]);			
				fileSettings.put(SettingType.VIDEO_RESOLUTION, resolutionInt);
				
				fileSettings.put(SettingType.VIDEO_BITRATE, Integer.parseInt(StreamsFilter.findVideoSetting(informations, 3)));
				fileSettings.put(SettingType.FPS, Double.parseDouble(StreamsFilter.findVideoSetting(informations, 4)));
				fileSettings.put(SettingType.AUDIO_CODEC, StreamsFilter.findAudioSetting(informations, 0));
			}else
				fileSettings.put(SettingType.AUDIO_CODEC, codec);
			
			fileSettings.put(SettingType.SAMPLING_RATE,  Integer.parseInt(StreamsFilter.findAudioSetting(informations, 1)));
			if(StreamsFilter.findAudioSetting(informations, 2).contains("mono"))
				fileSettings.put(SettingType.NUMBER_AUDIO_CHANNELS, 1);
			else
				fileSettings.put(SettingType.NUMBER_AUDIO_CHANNELS, 2);
			
			fileSettings.put(SettingType.AUDIO_BITRATE, Integer.parseInt(StreamsFilter.findAudioSetting(informations, 4)));
		}
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
