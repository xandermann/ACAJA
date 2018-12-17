package files;

import java.io.File;
import java.util.HashMap;

public class ProcessingFile {

	/**
	 * Duree totale
	 */
	private long duration;

	/**
	 * Les processus acheves
	 */
	private HashMap<String, Object> performedProcessings;

	/**
	 * Constructeur
	 * 
	 * @param file
	 */
	public ProcessingFile(File file) {
		// TODO
		// Methode qui trouve la duree du fichier (methode dans le package ffmtools)
	}

	/**
	 * Recupere les processus OK
	 * @param string
	 * @param object
	 */
	public void performProcess(String string, Object object) {
		// TODO
	}

	/**
	 * Recupere le processus acheve
	 * @return le processus
	 */
	public HashMap<String, Object> getPerformedProcessings() {
		return this.performedProcessings;
	}

}
