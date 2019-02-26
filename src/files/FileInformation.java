package files;

import java.io.Serializable;

public class FileInformation implements Serializable {

	/**
	 * Nom du fichier
	 */
	private String fileName;

	/**
	 * Chemin du fichier (relatif ou absolu)
	 */
	private String path;

	/**
	 * Constructeur
	 * 
	 * @param p_name Nom du fichier
	 * @param p_path Chemin du fichier (relatif ou absolu)
	 */
	public FileInformation(String p_name, String p_path) {
		this.fileName = p_name;
		this.path = p_path;
	}

	/**
	 * Recupere le nom du fichier
	 * 
	 * @return Le nom du fichier
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Recupere le chemin du fichier
	 * 
	 * @return Le chemin du fichier
	 */
	public String getPath() {
		return path;
	}

}
