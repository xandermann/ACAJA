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
		return directory.exists() ? true : directory.mkdir();
	}
	
	public static boolean checkImports() {
		return checkDirectory(ResourceConstants.OLD_IMPORTS);
	}
	
	public static boolean checkConversionImports() {
		return checkImports() && checkDirectory(ResourceConstants.CONVERSION_OLD_IMPORTS);
	}
	
	public static boolean checkProcessingImports() {
		return checkImports() && checkDirectory(ResourceConstants.PROCESSING_OLD_IMPORTS);
	}
	
	public static boolean checkTemporaryFiles() {
		return checkDirectory(ResourceConstants.TEMPORARY_FILES);
	}
	
	public static boolean checkAnswsers() {
		return checkDirectory(ResourceConstants.ANSWERS);
	}
	
	public static boolean checkErr() {
		return checkAnswsers() && checkDirectory(ResourceConstants.STDERR_ANSWERS);
	}
	
	public static boolean checkOut() {
		return checkAnswsers() && checkDirectory(ResourceConstants.STDOUT_ANSWERS);
	}
	
	
	
	
	public static void clearDirectory(File directory) {
		if(checkDirectory(directory)) {
			File[] files = directory.listFiles();
			for(File f : files) f.delete();
		}
	}
	
	public static void clearTemporaryFiles() {
		clearDirectory(ResourceConstants.TEMPORARY_FILES);
	}
	
	public static void clearErr() {
		clearDirectory(ResourceConstants.STDOUT_ANSWERS);
	}
	
	public static void clearOut() {
		clearDirectory(ResourceConstants.STDERR_ANSWERS);
	}
	
	/**
	 * [ METHODE DE CLASSE POUR NETTOYER LES RESSOURCES A LA FERLETURE DU LOGICIEL. ]
	 */
	public static void clearResources() {
		clearTemporaryFiles();
		clearErr();
		clearOut();
	}
}
