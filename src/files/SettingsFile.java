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

	
	public final static String CODEC_VIDEO= "codec video";
	

	/**
	 * Les anciens parametres du fichier
	 */
	private HashMap<String, Object> oldSettings;

	/**
	 * Les parametres courants du fichier
	 */
	private HashMap<String, Object> settings;

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
		oldSettings = new HashMap<String, Object>();
		settings = new HashMap<String, Object>();

		//Initialisation des autres parametres. 
	    SystemRequests.getSettings(this);
	}

	/**
	 * Methode pour modifier les parametres de la video.
	 * 
	 * @param setting  Le parametre a modifier.
	 * @param newValue La nouvelle valeur du parametre.
	 */
	public void modifySettings(String setting, String newValue) {
		if(setting.equals("codec video")) {
			String oldExtension =  
					sourceFile.getName().split("[.]")
					[sourceFile.getName().split("[.]").length-1];
			String fileName = sourceFile.getName();
			newValue = fileName.substring(0, fileName.lastIndexOf(oldExtension)-1)+newValue;
		}
		oldSettings.put(setting, settings.get(setting));
		settings.put(setting, newValue);
		
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
	public HashMap<String, Object> getSettings() {
		return settings;
	}


	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Recupere les anciens parametres du fichiers.
	 * 
	 * @return HashMap<String, String>			Les parametres du fichier. 
	 */
	public HashMap<String, Object> getOldSettings() {
		return oldSettings;
	}
	
	//=======================================================================================================================
	//=======================================================================================================================
}
