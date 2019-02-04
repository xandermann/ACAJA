package files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import exceptions.IncorrectFileException;
import wrapper.SystemRequests;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class SettingsFile extends SelectableFile implements Modifiable {
	// =======================================================================================================================
	// =======================================================================================================================

	public enum Settings {
		/**
		 * Constante qui definit le codec video dans la HashMap
		 */
		VIDEO_CODEC,

		/**
		 * Constante qui definit le bitrate video dans la HashMap
		 */
		VIDEO_BITRATE,

		/**
		 * Constante qui definit les FPS de la video dans la HashMap
		 */
		FPS,

		/**
		 * Constante qui definit le codec audio dans la HashMap
		 */
		AUDIO_CODEC,
		
		/**
		 * Constante qui definit le sampling rate dans la HashMap
		 */
		SAMPLING_RATE,
		
		/**
		 * Constante qui definit le nombre de pistes audios dans la HashMap
		 */
		NUMBER_AUDIO_CHANNELS,

		/**
		 * Constante qui definit le bitrate audio dans la HashMap
		 */
		AUDIO_BITRATE,

		/**
		 * Constante qui definit la résolution de la video dans la HashMap
		 */
		VIDEO_RESOLUTION,
	}



	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * Les requetes soumises par l'utilisateur.
	 */
	private HashMap<Settings, Object> requests;

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
	 * @throws IncorrectFileException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public SettingsFile(File file) throws IncorrectFileException {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(file);

		/**
		 * Si le type de fichier n'est pas accepté, alors on renvoie une exception.
		 */
		if (!this.containsAudio())
			throw new IncorrectFileException(IncorrectFileException.BAD_TYPE_FILE);

		/**
		 * INITIALISATION DES PARAMETRES DE LA VIDEO.
		 */
		// Intiliasation des tables.
		requests = new HashMap<Settings, Object>();
		settings = new HashMap<Integer, Object>();

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
	public void modify(Settings videoCodec, Object setting) {
		requests.put(videoCodec, setting);
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
		return !requests.equals(new HashMap<Integer, Object>());
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer les parametres du fichier.
	 * 
	 * @return HashMap<String, String> Les parametres du fichier.
	 */
	public HashMap<Integer, Object> getSettings() {
		return settings;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Cette methode pemet de recuperer les requetes.
	 * 
	 * @return HashMap<String, String> Les requetes.
	 */
	public HashMap<Settings, Object> getRequests() {
		return requests;
	}

	@Override
	public void modify(Integer typeChange, Object change) {
		// TODO Auto-generated method stub
		
	}

	// =======================================================================================================================
	// =======================================================================================================================
}
