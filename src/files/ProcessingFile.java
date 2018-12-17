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
		// this.performedProcessings.put(key, value)
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
	 * Recupere le processus courant
	 * @return le processus
	 */
	public HashMap<String, Object> getProcessings() {
		return performedProcessings;
	}

	/**
	 * Recupere le processus acheve
	 * @return le processus
	 */
	public HashMap<String, Object> getPerformedProcessings() {
		return this.performedProcessings;
	}

}
