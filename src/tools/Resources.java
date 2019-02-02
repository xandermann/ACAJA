package tools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
public final class Resources {
	/**
	 * LOGO DU LOGICIEL. 
	 */
	private final static String ACAJA_LOGO_PATH = "img/LogoAcaja.png";
	public final static File ACAJA_LOGO = new File(ACAJA_LOGO_PATH);
	
	/**
	 * FICHIERS DE SAUVEGARDES DES PRECEDENTS IMPORTS SUR LE LOGICIEL. 
	 */
	public final static String CONVERSION_OLD_IMPORTS = "saves/conversion_old_imports";
	public final static String PROCESSING_OLD_IMPORTS = "saves/processing_old_imports";
}
