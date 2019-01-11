package files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import ffmpeg_tools.SystemRequests;

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
	 * [ CONSTANTES DE CLASSE. ]
	 */
	/**
	 * Constante qui definit le codec video dans la HashMap
	 */
	public static final int VIDEO_CODEC = 1;

	/**
	 * Constante qui definit le bitrate video dans la HashMap
	 */
	public static final int VIDEO_BITRATE = 2;

	/**
	 * Constante qui definit les FPS de la video dans la HashMap
	 */
	public static final int FPS = 3;

	/**
	 * Constante qui definit le codec audio dans la HashMap
	 */
	public static final int AUDIO_CODEC = 4;

	/**
	 * Constante qui definit le sampling rate dans la HashMap
	 */
	public static final int SAMPLING_RATE = 5;

	/**
	 * Constante qui definit le nombre de pistes audios dans la HashMap
	 */
	public static final int NUMBER_AUDIO_CHANNELS = 6;

	/**
	 * Constante qui definit le bitrate audio dans la HashMap
	 */
	public static final int AUDIO_BITRATE = 7;

	/**
	 * Constante qui definit la résolution de la video dans la HashMap
	 */
	public final static int VIDEO_RESOLUTION = 8;

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * Les requetes soumises par l'utilisateur.
	 */
	private HashMap<Integer, Object> requests;

	/**
	 * Les parametres courants du fichier
	 */
	private HashMap<Integer, Object> settings;

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ CONSTRUCTEUR. ]
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
		 * Si le type de fichier n'est pas accepté, alors on renvoie une exception.
		 */
		if (!this.containsAudio())
			throw new IllegalArgumentException("Seuls les fichiers audio et video sont acceptes.");

		/**
		 * INITIALISATION DES PARAMETRES DE LA VIDEO.
		 */
		// Intiliasation des tables.
		this.requests = new HashMap<Integer, Object>();
		this.settings = new HashMap<Integer, Object>();

		// Initialisation des autres parametres.
		SystemRequests.getSettings(this);
	}

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * Methode pour modifier les parametres de la video.
	 * 
	 * @param setting  Le parametre a modifier.
	 * @param newValue La nouvelle valeur du parametre.
	 */
	public void modifySetting(Integer setting, Object request) {
		this.requests.put(setting, request);
	}

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ METHODE POUR SAVOIR SI DES PARAMETRES ONT ETE MODIFIES. ]
	 * 
	 * Methode pour tester si les parametres du fichier ont ete modifies.
	 * 
	 * @return booleen True si le fichier a ete modifie.
	 */
	public boolean isModified() {
		return !this.requests.equals(new HashMap<Integer, Object>());
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer les parametres du fichier.
	 * 
	 * @return HashMap<String, String> Les parametres du fichier.
	 */
	public HashMap<Integer, Object> getSettings() {
		return this.settings;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Cette methode pemet de recuperer les requetes.
	 * 
	 * @return HashMap<String, String> Les requetes.
	 */
	public HashMap<Integer, Object> getRequests() {
		return this.requests;
	}

}
