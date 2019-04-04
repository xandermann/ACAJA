package resources;

import java.io.*;
import java.time.Instant;

/**
 * [ INTERFACE POUR LE STOCKAGE DES RESSOURCES DU LOGICIEL. ]
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public interface ResourceConstants {
	/**
	 * LOGO DU LOGICIEL.
	 */
	public final static String ACAJA_LOGO_PATH = "img" + File.separator + "logoAcaja.png";
	public final static String ACAJA_LOGO_OPACITY_PATH = "img" + File.separator + "logoAcaja_opacity.png";
	public final static File ACAJA_LOGO = new File(ACAJA_LOGO_PATH);

	
	/**
	 * IMAGES UTILES.
	 */
	public final static String BUTTON_RECT = "img" + File.separator + "formeButton.png";
	public final static String BUTTON_RECT_SELECTED = "img" + File.separator + "formeButtonSelected.png";
	public final static String BLURRED = "img" + File.separator + "flou.png";
	public final static String BUTTON_BLUR_SELECTED = "img" + File.separator + "flouSelected.png";
	public final static String RIGHT_ARROW = "img" + File.separator + "flecheDroite.png";
	public final static String LEFT_ARROW = "img" + File.separator + "flecheGauche.png";
	public final static String REFRESH_PATH = "img" + File.separator + "refresh.jpg";
	public final static File REFRESH = new File(REFRESH_PATH);

	
	/**
	 * FICHIERS TEMPORAIRES.
	 */
	public final static String TEMPORARY_FILES_PATH = "temporary_files" + File.separator;
	public final static File TEMPORARY_FILES = new File(TEMPORARY_FILES_PATH);
	public final static String TEMPORARY_FILES_FULL_PATH = TEMPORARY_FILES.getAbsolutePath() + File.separator;
	public final static String TEMPORARY_FILES_PREFIX = TEMPORARY_FILES_FULL_PATH+"temporary_";

	
	/**
	 * ANCIENS IMPORTS. 
	 */
	public final static String OLD_IMPORTS_PATH = "imports" + File.separator;
	public final static String CONVERSION_OLD_IMPORTS_PATH = OLD_IMPORTS_PATH + "conversion" + File.separator;
	public final static String PROCESSING_OLD_IMPORTS_PATH = OLD_IMPORTS_PATH + "processing" + File.separator;
	public final static File OLD_IMPORTS = new File(OLD_IMPORTS_PATH);
	public final static File CONVERSION_OLD_IMPORTS = new File(CONVERSION_OLD_IMPORTS_PATH);
	public final static File PROCESSING_OLD_IMPORTS = new File(PROCESSING_OLD_IMPORTS_PATH);
	
	
	/**
	 * FICHIERS DE SAUVEGARDES DES FLUX. 
	 */
	public final static String ANSWERS_PATH = "answers" + File.separator;
	public final static String STDOUT_ANSWERS_PATH = ANSWERS_PATH + "stdout" + File.separator;
	public final static String STDERR_ANSWERS_PATH = ANSWERS_PATH + "stderr" + File.separator;
	public final static File ANSWERS = new File(ANSWERS_PATH);
	public final static File STDOUT_ANSWERS = new File(STDOUT_ANSWERS_PATH);
	public final static File STDERR_ANSWERS = new File(STDERR_ANSWERS_PATH);
	public final static String STDOUT_ANSWER_PREFIX = STDOUT_ANSWERS_PATH + "out_";
	public final static String STDERR_ANSWER_PREFIX = STDERR_ANSWERS_PATH + "err_";
	
	
	/**
	 * @return String 		DATE COURANTE (YYYY-MM-DD). 
	 */
	public static String day() {
		String now = Instant.now().toString();
		return now.substring(0, now.indexOf("T"));
	}
	/**
	 * @return String 		DATE COUTRANTE (YYYY-MM-DD) + MOMENT COURANT (EN MILLISECONDES).
	 */
	public static String now() {
		return day()+"_"+(System.currentTimeMillis()+60*60*10*10*10*2);
	}
	
	
	/**
	 * FICHIERS DE CONFIGURATION.
	 */
	public final static String SETTINGS_PATH = "settings" + File.separator;
	public final static File SETTINGS = new File(SETTINGS_PATH);
	public final static String NOTIFICATION_SETTINGS_PATH = SETTINGS_PATH + "notification_settings.ini";
	public final static File NOTIFICATION_SETTINGS = new File(NOTIFICATION_SETTINGS_PATH);
}
