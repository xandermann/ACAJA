package resources;

import java.io.File;

public final class ResourcesManager {
	/**
	 * [ VERIFIER L'EXISTENCE D'UN REPERTOIRE. ]
	 * 
	 * @param directory		Le repertoire.
	 * @return boolean		Vaut true si le repertoire existe ou a reussi a etre recreer. 
	 */
	public static boolean checkDirectory(File directory) {
		if(!directory.exists())
			return directory.mkdir();
		return true;
	}
	
	public static boolean checkImports() {
		return checkDirectory(ResourceConstants.ALL_OLD_IMPORTS);
	}
	
	public static boolean checkConversionImports() {
		return checkDirectory(ResourceConstants.CONVERSION_OLD_IMPORTS);
	}
	
	public static boolean checkProcessingImports() {
		return checkDirectory(ResourceConstants.PROCESSING_OLD_IMPORTS);
	}
	
	/**
	 *  [ METHODE DE CLASSE POUR CREER LES DOSSIERS DES IMPORTS SI ILS N'EXISTENT PAS ]
	 */
	public static boolean checkResources() {
		return checkImports() && checkConversionImports() && checkProcessingImports();
	}
}
