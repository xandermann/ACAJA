package resources;

import java.io.File;

public final class ResourcesManager {
	/**
	 *  [ METHODE DE CLASSE POUR CREER LES DOSSIERS DES IMPORTS SI ILS N'EXISTENT PAS ]
	 */
	public static boolean checkResources() {
		File dirSaves = new File(ResourceConstants.ALL_OLD_IMPORTS_PATH);
		File dirConversion = new File(ResourceConstants.CONVERSION_OLD_IMPORTS_PATH);
		File dirProcessing = new File(ResourceConstants.PROCESSING_OLD_IMPORTS_PATH);
		try {
			boolean res = true;
			if(!dirSaves.exists())
				 res = dirSaves.mkdir();
			if(!dirConversion.exists() && res != false)
				res = dirConversion.mkdir();
			if(!dirProcessing.exists() && res != false)
				res = dirProcessing.mkdir();
			return res;
		} catch (Exception e) {
			return false;
		}				
	}
}
