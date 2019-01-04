package files;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import ffmpeg_tools.SystemRequests;

public class SettingsFile extends SelectableFile {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ ATTRIBUTS. ]
	 */
	/**
	 * Les anciennes options du fichier
	 */
	private HashMap<String, String> oldSettings;

	/**
	 * Les options courantes du fichier
	 */
	private HashMap<String, String> settings;

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param file    Fichier
	 * @param isVideo Si le fichier est une video
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public SettingsFile(File file) throws IOException, InterruptedException {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(file);
		
		/**
		 * SI TYPE DE FICHIER PAS ACCCPETE EXCPETION.
		 */
		if(isGoodFile())
			throw new IllegalArgumentException("Seuls les fichiers audio et video sont toleres."); 
		
		/**
		 * INITIALISATION DES PARAMETRES DE LA VIDEO. 
		 */
		//Intiliasation des tables. 
		oldSettings = new HashMap<String, String>();
		settings = new HashMap<String, String>();
		
		//On determine le codec de la video ou de l'audio. 
		String key;
		key = isVideo() ? "codec_video" : "codec_audio";
		String codec = sourceFile.getName().split(".")[sourceFile.getName().split(".").length-1];
		settings.put(key, codec);
		
		//Initialisation des autres parametres. 
		SystemRequests.getSettings(this);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Methode pour modifier les parametres de la video. 
	 * 
	 * @param setting 		Le parametre a modifier.
	 * @param newValue		La nouvelle valeur du parametre. 
	 */
	public void modifySettings(String setting, String newValue) {
		oldSettings.put(setting, settings.get(setting));
		settings.put(setting, newValue);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour tester si les parametres du fichier ont ete modifies.
	 * 
	 * @return booleen		True si le fichier a ete modifie. 
	 */
	public boolean isModified() {
		return !oldSettings.equals(new HashMap<String, String>());
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Recupere les parametres du fichiers.
	 * 
	 * @return HashMap<String, String>			Les parametres du fichier. 
	 */
	public HashMap<String, String> getSettings() {
		return settings;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
