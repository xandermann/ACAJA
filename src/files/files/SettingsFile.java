package files.files;

import java.io.File;
import java.util.HashMap;
import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.SettingType;
import wrapper.runtime.global.SystemRequests;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class SettingsFile extends SelectableFile implements Modifiable {
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * Les requetes soumises par l'utilisateur.
	 */
	private HashMap<SettingType, String> requests;

	/**
	 * Les parametres courants du fichier
	 */
	private HashMap<SettingType, String> settings;

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param file Le fichier source.
	 * 
	 * @throws IncorrectFileException      L'exception sur les fichiers de type
	 *                                     incorrect.
	 * 
	 * @throws UnfindableResourceException Exception sur les ressources
	 *                                     introuvables.
	 */
	public SettingsFile(File file) throws IncorrectFileException, UnfindableResourceException {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(file);

		/**
		 * Si le type de fichier n'est pas accepe, alors on renvoie une exception.
		 */
		if (!isVideo())
			throw new IncorrectFileException(IncorrectFileException.BAD_TYPE_FILE);

		/**
		 * INITIALISATION DES PARAMETRES DE LA VIDEO.
		 */
		// Initialisation des tables.
		requests = new HashMap<SettingType, String>();
		settings = new HashMap<SettingType, String>();

		// Initialisation des autres parametres.
		SystemRequests.askMetadata(this);

		thumbnail = SystemRequests.askFrame(this, "00:00:01.00", 190, 90);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	@Override
	public void modify(OperationType typeSetting, String setting) {
		requests.put((SettingType) typeSetting, setting);
	}
	
	

	@Override
	public boolean isModified() {
		return !requests.equals(new HashMap<SettingType, String>());
	}

	public String recent(OperationType typeSetting) {
		return requests.containsKey(typeSetting) ? requests.get(typeSetting) : settings.get(typeSetting);
	}

	public String old(OperationType typeSetting) {
		return settings.get(typeSetting);
	}

	

	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ] * Methode pour recuperer les parametres du
	 * fichier.
	 * 
	 * @return HashMap<String, String> Les parametres du fichier.
	 */
	public HashMap<SettingType, String> getSettings() {
		return settings;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Cette methode pemet de recuperer les requetes.
	 * 
	 * @return HashMap<String, String> Les requetes.
	 */
	public HashMap<SettingType, String> getRequests() {
		return requests;
	}



	@Override
	public String getValue(OperationType typeChange) {
		return requests.get(typeChange);
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
