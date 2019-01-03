package ffmpeg_tools;
import java.io.*;
import java.util.HashMap;

import files.SelectableFile;

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
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file
	 * @param time
	 * @return
	 */
	public static File createVideoFrame(File file, long time) {
		return file;
	}

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file
	 * @param tab
	 * @return
	 */
	public static File createThumbail(File file, int[] resolution) {
		return file;
	}

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file
	 * @param time
	 * @return
	 */
	public static File extractImage(File file, long time) {
		return file;
	}

	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO iteration 1 pas concernee. 
	 * 
	 * @param file
	 * @param resolution
	 * @return
	 */
	private static File resizeImage(File file, int[] resolution) {
		return file;
	}
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * TODO  Concerne la fenetre de traitement et donc ne concerne pas l'iteration 1. 
	 * 
	 * @param file
	 * @param resolution
	 * @return
	 */
	private static int[] findGoodResolution(File file, int[] resolution) {
		return resolution;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LE RENSEIGNEMENT DE LA DUREE D'UNE VIDEO OU D'UN SON. ]
	 * 
	 * TODO  Concerne la fenetre de traitement et donc ne concerne pas l'iteration 1. 
	 * 
	 * @param file
	 * @return
	 */
	public static long getDuration(File file) {
		return 0;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE POUR LE RENSEIGNEMENT DES CARACTERISTIQUES D'UNE VIDEO OU D'UN SON. ]
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
	 * @param file					Le fichier dont on souhaite connaitre les parametres. 
	 * @param fileType  			L'indice correspondant au type du fichier audio ou video. 
	 * 
	 * @return 						Les parametres du fichier. 
	 * @throws IOException 
	 * @throws InterruptedException 
	*/
	public static HashMap<String, Object> getSettings(SelectableFile file) throws IOException, InterruptedException {
		if(SelectableFile.isGoodFile(file)){	
			HashMap<String, Object> fileSettings = new HashMap<String, Object>();
			
			/**
			 * Requete pour acceder aux parametres d'un fichier. 
			 */
			Process p = FFmpegRuntime.execute(" -i"+file.getSourceFile().getName());
			
			/**
			 * "Il est temps de vous racontez une petite histoire" :
			 * Les pogrammeurs de FFMPEG savent que les temps de traitement des requetes
			 * sur FFMPEG peuvent prendre un temps assez long. Et il faut savoir que 
			 * le traitement d'une requete renvoie son resultat par le biais des flux de sortie 
			 * appeles STDOUT. Les pogrammeurs de FFMPEG ont decide que les messages 
			 * (de reussite comme d'echec d'une requete passeraient par les flux 
			 * d'erreurs STDERR. Mais pourquoi ce choix n'est-ce-pas ? Pour la bonne 
			 * raison qu'envoyer tous les messages par STDERR permet d'eviter que 
			 * les traitements des requetes qui passent par STDOUT ne soit freiner par les 
			 * messages. 
			 * 
			 * C'est ainsi qu'on se retrouve en 2019 a devoir extraire les messages ne correpondant 
			 * pas a des cas d'erreur a partir STDERR et pas a partir de STDOUT ( comme ca l'est  
			 * habituellement" ). 
			 * 
			 * Aisni on ne s'interesse ici qu'au flux d'erreurs qui du coup n'en est pas un avec FFMPEG,
			 * mais plutot un flux des messages tous confondus. 
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream())); 
			String informations = br.readLine();
			br.close();
			
			if(SelectableFile.isVideo(file)) {
				//fileSettings.add()
			}else{	
				//utiliser indexOf() et substring()
			}
			
			return fileSettings;
		}
		return null;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	

	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	//POUR TESTER FAITES PAS GAFFE MDR 
	public static void main(String[] args) throws IOException, InterruptedException {
		String s1 ="-i C:\\Users\\Jean-christophe\\Documents\\PROFESSIONNEL\\2A\\projetTutore\\test\\1.avi";
		Process p = execute(s1);

		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream())); 
		PrintWriter pw = new PrintWriter(new FileWriter("lol.txt"));

				try {
					String s3 = null;
					while ((s3 = br.readLine()) != null) { 
						pw.println(s3); 
						System.out.println("ERROR"+s3);
					}
					pw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
