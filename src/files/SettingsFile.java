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
	//=======================================================================================================================
	//=======================================================================================================================
	

	public static final int VIDEO_CODEC = 1;

	public static final int VIDEO_BITRATE = 2;

	public static final int FPS = 3;

	public static final int AUDIO_CODEC = 4;

	public static final int SAMPLING_RATE = 5;

	public static final int NUMBER_AUDIO_CHANNELS = 6;

	public static final int AUDIO_BITRATE = 7;

	public final static int VIDEO_RESOLUTION = 8;
	

	//=======================================================================================================================
	//=======================================================================================================================
	

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

		//Initialisation des autres parametres. 
	    SystemRequests.getSettings(this);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * Methode pour modifier les parametres de la video.
	 * 
	 * @param setting  Le parametre a modifier.
	 * @param newValue La nouvelle valeur du parametre.
	 */
	public void modifySetting(Integer setting, Object newValue) {
		oldSettings.put(setting, settings.get(setting));
		settings.put(setting, newValue);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR SAVOIR SI DES PARAMETRES ONT ETE MODIFIES. ]
	 * 
	 * Methode pour tester si les parametres du fichier ont ete modifies.
	 * 
	 * @return booleen True si le fichier a ete modifie.
	 */
	public boolean isModified() {
		return !oldSettings.equals(new HashMap<Integer, Object>());
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


	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Recupere les anciens parametres du fichiers.
	 * 
	 * @return HashMap<String, String>			Les parametres du fichier. 
	 */
	public HashMap<Integer, Object> getOldSettings() {
		return oldSettings;
	}
	
	//=======================================================================================================================
	//=======================================================================================================================
}
