package resources;

import java.io.File;

import exceptions.UnfindableResourceException;

/**
 * [ GESTIONNAIRE DE L'ESPACE DE NOMS. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class NamesSpaceManager {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * EXTENSIONS DE FICHIERS. 
	 */
	private final static String TEXT = ".txt", JPG = ".jpg";
	
	
	//=======================================================================================================================
	
	
	/**
	 * [ GENERER UN NOM UNIQUE POUR UN FICHIER TEMPORAIRE HORODATE ]
	 * 
	 * @return	Le nom unique horodate.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static String _temporary() throws UnfindableResourceException {
		ResourcesManager.secureTemporaryFiles();
		return ResourceConstants.TEMPORARY_FILES_PREFIX+ResourceConstants.now()+JPG;
	}
	
	/**
	 * [ GENERER UN NOM UNIQUE POUR UN FICHIER DE SAUVEGARDE HORODATE DE STDERR. ]
	 * 
	 * @return	Le nom unique horodate.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static String _err() throws UnfindableResourceException {
		ResourcesManager.secureAnswers();
		ResourcesManager.secureErr();
		return ResourceConstants.STDERR_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	/**
	 * [ GENERER UN NOM UNIQUE POUR UN FICHIER DE SAUVEGARDE HORODATE DE STDOUT. ]
	 * 
	 * @return	Le nom unique horodate.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static String _out() throws UnfindableResourceException {
		ResourcesManager.secureAnswers();
		ResourcesManager.secureOut();
		return ResourceConstants.STDOUT_ANSWER_PREFIX+ResourceConstants.now()+TEXT;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ GENERER UN FICHIER STDERR. ]
	 * 
	 * @return	Le fichier.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static File err() throws UnfindableResourceException {
		return new File(_err());
	}
	
	/**
	 * [ GENERER UN FICHIER STDOUT. ]
	 * 
	 * @return	Le fichier.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static File out() throws UnfindableResourceException {
		return new File(_out());
	}
	
	/**
	 * [ GENERER UN FICHIER TEMPORAIRE. ]
	 * 
	 * @return	Le fichier.
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static File temporary() throws UnfindableResourceException {
		return new File(_temporary());
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
