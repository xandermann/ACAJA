package resources;

import java.io.File;

import exceptions.UnfindableResourceException;

/**
 * [ GESTIONNAIRE DES RESSOURCES. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ResourcesManager {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ VERIFIER L'EXISTENCE D'UN REPERTOIRE. ]
	 * 
	 * @param directory		Le repertoire.
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkDirectory(File directory) {
		return directory.exists() ? true : directory.mkdir();
	}
	
	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE PARENT DES REPERTOIRES D'IMPORTS. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkImports() {
		return checkDirectory(ResourceConstants.OLD_IMPORTS);
	}
	
	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE DES IMPORTS POUR LA CONVERSION. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkConversionImports() {
		return checkImports() && checkDirectory(ResourceConstants.CONVERSION_OLD_IMPORTS);
	}

	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE DES IMPORTS POUR LE TRAITEMENT. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkProcessingImports() {
		return checkImports() && checkDirectory(ResourceConstants.PROCESSING_OLD_IMPORTS);
	}
	
	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE DES FICHIERS TEMPORAIRES. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkTemporaryFiles() {
		return checkDirectory(ResourceConstants.TEMPORARY_FILES);
	}
	
	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE PARENT DES REPERTOIRES DE SAUVEGARDES DES FLUX. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkAnswers() {
		return checkDirectory(ResourceConstants.ANSWERS);
	}
	
	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE DES SAUVEGARDES DE STDERR. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkErr() {
		return checkAnswers() && checkDirectory(ResourceConstants.STDERR_ANSWERS);
	}

	/**
	 * [ VERIFIER L'EXISTENCE DU REPERTOIRE DES SAUVEGARDES DE STDOUT. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkOut() {
		return checkAnswers() && checkDirectory(ResourceConstants.STDOUT_ANSWERS);
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ NETTOYER UN REPERTOIRE. ]
	 * 
	 * @return boolean		Vaut true si le repertoire existe et que tous les fichiesr qu'il contient 
	 * 						ont ete supprimes. Et vaut false si le repertoire n'existe pas ou qu'au 
	 * 						moins un fichier n'a pas pu etre supprime. 
	 */
	public static boolean clearDirectory(File directory) {
		if(directory.exists() ) {
			boolean allDeleted = true;
			File[] files = directory.listFiles();
			for(File f : files) {
				if(allDeleted==true) allDeleted = f.delete(); else f.delete();
			}
			return allDeleted;
		}
		return false;
	}
	
	
	/**
	 * [ NETTOYER LE REPERTOIRE DES FICHIERS TEMPORAIRES. ]
	 * 
	 * @return boolean		Vaut true si le contenu du repertoire a pu etre supprime dans sa totalite. 
	 * 						Et vaut false si un seul element n'a pas pu etre supprime. 
	 */
	public static boolean clearTemporaryFiles() {
		return clearDirectory(ResourceConstants.TEMPORARY_FILES);
	}
	
	
	/**
	 * [ NETTOYER LE REPERTOIRE DES SAUVEGARDES DE STDERR. ]
	 * 
	 * @return boolean		Vaut true si le contenu du repertoire a pu etre supprime dans sa totalite. 
	 * 						Et vaut false si un seul element n'a pas pu etre supprime. 
	 */
	public static boolean clearErr() {
		return clearDirectory(ResourceConstants.STDOUT_ANSWERS);
	}
	
	
	/**
	 * [ NETTOYER LE REPERTOIRE DES SAUVEGARDES DE STDOUT ]
	 * 
	 * @return boolean		Vaut true si le contenu du repertoire a pu etre supprime dans sa totalite. 
	 * 						Et vaut false si un seul element n'a pas pu etre supprime. 
	 */
	public static boolean clearOut() {
		return clearDirectory(ResourceConstants.STDERR_ANSWERS);
	}
	
	
	/**
	 * [ NETTOYER TOUTES LES RESSOURCES. ]
	 * 
	 * @return boolean		Vaut true si le contenu des repertoires a pu etre supprime dans sa totalite. 
	 * 						Et vaut false si un seul element n'a pas pu etre supprime. 
	 */
	public static boolean clearResources() {
		boolean succeed = clearTemporaryFiles();
		boolean succeed2 = clearErr();
		boolean succeed3 = clearOut();
		return succeed && succeed2 && succeed3;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE IMPORTS\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureImports() throws UnfindableResourceException {
		if(!checkImports())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_IMPORTS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE IMPORTS\CONVERSION\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureConversionImports() throws UnfindableResourceException {
		if(!checkConversionImports())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_CONVERSION_IMPORTS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE IMPORTS\CONVERSION\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureProcessingImports() throws UnfindableResourceException {
		if(!checkConversionImports())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_PROCESSING_IMPORTS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE ANSWERS\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureAnswers() throws UnfindableResourceException {
		if(!checkAnswers())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_ANSWERS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE ANSWERS\STDERR\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureErr() throws UnfindableResourceException {
		if(!checkAnswers())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_STDERR_ANSWERS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE ANSWERS\STDOUT\. ]
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureOut() throws UnfindableResourceException {
		if(!checkAnswers())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_STDOUT_ANSWERS);
	}
	
	
	/**
	 * [ LEVE UNE EXCEPTION SI ABSENCE DU REPERTOIRE TEMPORARY_FILES\. ] 
	 * 
	 * @throws UnfindableResourceException		Exception sur les ressources introuvables. 
	 */
	public static void secureTemporaryFiles() throws UnfindableResourceException {
		if(!checkTemporaryFiles())
			throw new UnfindableResourceException(UnfindableResourceException.UAITR_TEMPORARY_FILES);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
