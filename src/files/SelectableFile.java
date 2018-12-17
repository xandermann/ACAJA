package files;

import java.io.File;

public class SelectableFile {

	/**
	 * Fichier VIDEO
	 */
	public final static int FILE_TYPE_VIDEO = 0;

	/**
	 * Fichier AUDIO
	 */
	public final static int FILE_TYPE_AUDIO = 1;

	/**
	 * FICHIER IMAGE
	 */
	public final static int FILE_TYPE_IMAGE = 2;

	/**
	 * Type du fichier
	 */
	protected int typeFile;

	/**
	 * Fichier source
	 */
	protected File sourceFile;

	/**
	 * Vrai s'il est selectionne
	 */
	private boolean isSelected;

	/**
	 * Definition a partir du fichier source
	 * 
	 * @param file Fichier source
	 */
	public void SelectionFile(File file) {

		this.isSelected = false;
		this.sourceFile = file;
		this.typeFile = this.getExtensionCurrentFile();

	}
	
	/**
	 * Retourne l'extension du fichier courant (a partir des constantes de la classe)
	 * @return L'extension du fichier (0,1 ou 2) selon les constantes
	 */
	private int getExtensionCurrentFile() {

		String extension = "";
		String fileName = this.sourceFile.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		// L'extension du fichier (via les constantes)
		int ret = 0;

		switch (extension) {

		case "mp4":
		case "avi":
			ret = FILE_TYPE_VIDEO;
			break;

		case "png":
		case "jpg":
		case "jpeg":
			ret = FILE_TYPE_IMAGE;
			break;

		case "mp3":
		case "wav":
		case "ogg":
			ret = FILE_TYPE_AUDIO;
			break;

		/*
		 * default: // Si inconnu ne fait rien break;
		 */
		}
		return ret;
	}

	/**
	 * Test si le fichier est selectionne
	 * 
	 * @return Vrai s'il est selectionne
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * Test si le fichier est selectionne
	 * 
	 * @param isSelected
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * Recupere le type du fichier
	 * 
	 * @return le type du fichier
	 */
	public int getTypeFile() {
		return this.typeFile;

	}
}
