package resources;

import java.io.*;

/**
 * [ CLASSE POUR LE STOCKAGE DES RESSOURCES DU LOGICIEL. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ResourceConstants {
	/**
	 * LOGO DU LOGICIEL. 
	 */
	public final static String ACAJA_LOGO_PATH = "img/LogoAcaja.png";
	public final static String ACAJA_LOGO_PATH_Opacity = "img/LogoAcaja_opacity.png";
	public final static File ACAJA_LOGO = new File(ACAJA_LOGO_PATH);
	
	
	public final static String rectbutton = "img/formeButton.png";
	public final static String flou = "img/flou.png";
	public final static String arrowR = "img/flecheDroite.png";
	public final static String arrowL = "img/flecheGauche.png";
	
	
	/**
	 * FICHIERS TEMPORAIRES. 
	 */
	public final static String TEMPORARY_FILES_PATH = "temporary_files/";
	
	
	/**
	 * FICHIERS DE SAUVEGARDES DES PRECEDENTS IMPORTS SUR LE LOGICIEL. 
	 */
	public final static String ALL_OLD_IMPORTS_PATH = "old_imported_files/";
	public final static String CONVERSION_OLD_IMPORTS_PATH = ALL_OLD_IMPORTS_PATH+"conversion_old_imports/";
	public final static String PROCESSING_OLD_IMPORTS_PATH = ALL_OLD_IMPORTS_PATH+"processing_old_imports/";
}
