package resources;

import java.io.*;

/**
 * [ CLASSE POUR LE STOCKAGE DES RESSOURCES DU LOGICIEL. ]
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class ResourceConstants {
	/**
	 * LOGO DU LOGICIEL.
	 */
	public final static String ACAJA_LOGO_PATH = "img/LogoAcaja.png";
	public final static String ACAJA_LOGO_OPACITY_PATH = "img/LogoAcaja_opacity.png";
	public final static File ACAJA_LOGO = new File(ACAJA_LOGO_PATH);

	public final static String BUTTON_RECT = "img/formeButton.png";
	public final static String BLURRED = "img/flou.png";
	public final static String RIGHT_ARROW = "img/flecheDroite.png";
	public final static String LEFT_ARROW = "img/flecheGauche.png";

	/**
	 * FICHIERS TEMPORAIRES.
	 */
	public final static String TEMPORARY_FILES_PATH = "temporary_files/";
	public final static File TEMPORARY_FILES = new File(TEMPORARY_FILES_PATH);
	public final static String TEMPORARY_FILES_FULL_PATH = TEMPORARY_FILES.getAbsolutePath();

	/**
	 * FICHIERS DE SAUVEGARDES DES PRECEDENTS IMPORTS SUR LE LOGICIEL.
	 */
	public final static String ALL_OLD_IMPORTS_PATH = "old_imports/";
	public final static String CONVERSION_OLD_IMPORTS_PATH = ALL_OLD_IMPORTS_PATH + "conversion_old_imports/";
	public final static String PROCESSING_OLD_IMPORTS_PATH = ALL_OLD_IMPORTS_PATH + "processing_old_imports/";
	public final static File ALL_OLD_IMPORTS = new File(ALL_OLD_IMPORTS_PATH);
	public final static File CONVERSION_OLD_IMPORTS = new File(CONVERSION_OLD_IMPORTS_PATH);
	public final static File PROCESSING_OLD_IMPORTS = new File(ALL_OLD_IMPORTS_PATH);
	
	/**
	 * STOCKAGE DES FLUX. 
	 */
	public final static String ANSWERS_PATH = "last_answers/";
	public final static String STDOUT_ANSWERS_PATH = ANSWERS_PATH + "stdout.txt";
	public final static String STDERR_ANSWERS_PATH = ANSWERS_PATH + "stderr.txt";
	public final static File STDOUT_ANSWERS = new File(STDOUT_ANSWERS_PATH);
	public final static File STDERR_ANSWERS = new File(STDERR_ANSWERS_PATH);
}
