package files;

import java.io.File;
import java.util.HashMap;

public class SettingsFile {

	/**
	 * Determine si c'est une video ou non
	 */
	private boolean isVideo;
	
	/**
	 * Les anciennes options du fichier
	 */
	private HashMap<String, Object> oldSettings;
	
	/**
	 * Les options courantes du fichier
	 */
	private HashMap<String, Object> settings;

	/**
	 * Constructeur
	 * @param file Fichier
	 * @param isVideo Si le fichier est une video
	 */
	public SettingsFile(File file, boolean isVideo) {
		// this.file
		this.isVideo = isVideo;
	}

	/**
	 * Modifie les parametres
	 * @param s
	 * @param o
	 */
	public void modifySettings(String key, Object value) {
		this.settings.put(key, value);
	}

	/**
	 * Test si le fichier est une video
	 * @return si est une video
	 */
	public boolean isVideo() {
		return this.isVideo;
	}

	/**
	 * Test si le fichier a ete modifie
	 * @return si le fichier a ete modifie
	 */
	public boolean isModified() {
		return !this.oldSettings.equals(this.settings);
	}

	/**
	 * Recupere les options courantes
	 * @return Les options courantes
	 */
	public HashMap<String, Object> getSettings() {
		return this.settings;
	}

}
