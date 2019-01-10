package files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public class SettingsFile extends SelectableFile {
	
	/**
	 * Clé de la HashMap Codec video
	 */
	private static final int VIDEO_CODEC = 1;

	/**
	 * Clé de la HashMap Bitrate video
	 */
	private static final int VIDEO_BITRATE = 2;

	/**
	 * Clé de la HashMap FPS
	 */
	private static final int FPS = 3;

	/**
	 * Clé de la HashMap Codec Audio
	 */
	private static final int AUDIO_CODEC = 4;

	/**
	 * Clé de la HashMap Taux d'échantillonnage
	 */
	private static final int SAMPLING_RATE = 5;

	/**
	 * Clé de la HashMap Nombre de canaux audios
	 */
	private static final int NUMBER_AUDIO_CHANNEL = 6;

	/**
	 * Clé de la HashMap Bitrate Audio
	 */
	private static final int AUDIO_BITRATE = 7;

	/**
	 * [ RESOLUTION DES FRAMES DANS L'APERCU DE LECTURE DE VIDEO. ]
	 * 
	 * TODO iteration 1 pas concernee.
	 * 
	 * Ceci est le tableau representant la resolution frames dans l'apercu de
	 * lecture de video de la fenetre de traitement.
	 */
	private final static Integer VIDEO_RESOLUTION = 8;
	
	/**
	 * [ RESOLUTION DES FRAMES DANS L'APERCU DE LECTURE DE VIDEO. ]
	 * 
	 * TODO iteration 1 pas concernee.
	 * 
	 * Ceci est le tableau representant la resolution frames dans l'apercu de
	 * lecture de video de la fenetre de traitement.
	 */
	private final static Integer FORMAT_VIDEO = 9;
	
	/*============================================================*/

	/**
	 * [ CONSTANTES DE CLASSES A USAGE INTERNE. ]
	 * 
	 * Voici des constantes qui nous seront bien pratiques. Pour la majorite, ce
	 * sont des contantes representant des types de resolutions ( par exemple :
	 * resolution d'une frame de l'apercu de lecture de video du logiciel ).
	 * 
	 * Ces constantes ne seront destines a etre utilises que pour le fonctionnement
	 * interne de la fenetre de traitement.
	 * 
	 * Elles sont destinees a un usage uniquement interne ( d'ou l presence des
	 * private ).
	 */

	/**
	 * [ CHEMIN VERS LE REPERTOIRE DES FICHIERS TEMPORAIRES. ]
	 * 
	 * TODO iteration 1 pas concernee.
	 * 
	 * Ceci est le chemin vers le repertoire des fichiers temporaires. Ces fameux
	 * ficheirs temporaires seront en fait les miniatures de video, et les frames de
	 * d'apercu de lecture de video, etc.
	 */
	private final static String PATH_TEMPORARY_FILES = System.getProperty("user.dir") + "\\temporary_files\\";

	/**
	 * [ RESOLUTION DES MINIATURES DANS LES BIBLIOTHEQUES. ]
	 * 
	 * TODO iteration 1 pas concernee.
	 * 
	 * Ceci est le tableau representant la resolution des miniatutres des videos,
	 * des sons, et des images qui seront importes par l'utilisateur dans le
	 * logiciel.
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
	 * Ceci est le tableau representant la resolution des miniatutres des sons, dans
	 * les chronologies des sons.
	 */
	private final static int[] SOUND_TIMELINE_THUMBAIL_RESOLUTION = new int[] {};

	/*============================================================*/
	
	/**
	 * Constante ajoutée: clé de la hashmap
	 */
	public final static String KEY_FORMAT = "FORMAT";
	

	/**
	 * Les anciens parametres du fichier
	 */
	private HashMap<Integer, Object> oldSettings;

	/**
	 * Les parametres courants du fichier
	 */
	private HashMap<Integer, Object> settings;

	/**
	 * Constructeur
	 * 
	 * @param file Le fichier source.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public SettingsFile(File file) {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(file);

		/**
		 * SI TYPE DE FICHIER PAS ACCCPTE EXCPETION.
		 */
		if (!isGoodFile())
			throw new IllegalArgumentException("Seuls les fichiers audio et video sont toleres.");

		/**
		 * INITIALISATION DES PARAMETRES DE LA VIDEO.
		 */
		// Intiliasation des tables.
		oldSettings = new HashMap<Integer, Object>();
		settings = new HashMap<Integer, Object>();

		// Initialisation des autres parametres.
		// SystemRequests.getSettings(this);

	}

	/**
	 * Methode pour modifier les parametres de la video.
	 * 
	 * @param setting  Le parametre a modifier.
	 * @param newValue La nouvelle valeur du parametre.
	 */
	public void modifySettings(Integer setting, String newValue) {
		this.oldSettings.put(setting, this.settings.get(setting));
		this.settings.put(setting, newValue);
	}

	/**
	 * [ METHODE POUR SAVOIR SI DES PARAMETRES ONT ETE MODIFIES. ]
	 * 
	 * Methode pour tester si les parametres du fichier ont ete modifies.
	 * 
	 * @return booleen True si le fichier a ete modifie.
	 */
	public boolean isModified() {
		return !oldSettings.equals(new HashMap<String, Object>());
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Recupere les parametres du fichiers.
	 * 
	 * @return HashMap<String, String> Les parametres du fichier.
	 */
	public HashMap<Integer, Object> getSettings() {
		return settings;
	}

}
