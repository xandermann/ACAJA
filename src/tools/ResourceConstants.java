package tools;

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
	
	/**
	 * FICHIERS DE SAUVEGARDES DES PRECEDENTS IMPORTS SUR LE LOGICIEL. 
	 */
	public final static String CONVERSION_OLD_IMPORTS = "saves/conversion_old_imports";
	public final static String PROCESSING_OLD_IMPORTS = "saves/processing_old_imports";
}
