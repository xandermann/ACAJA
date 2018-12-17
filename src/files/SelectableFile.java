package files;

import java.io.File;

public class SelectableFile {

	/**
	 * Fichier VIDEO
	 */
	public final static int TYPE_FILE_VIDEO = 0;

	/**
	 * Fichier AUDIO
	 */
	public final static int TYPE_FILE_AUDIO = 1;

	/**
	 * FICHIER IMAGE
	 */
	public final static int TYPE_FILE_IMAGE = 2;

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

		String extension = "";
		String fileName = file.getName();

		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		switch (extension) {

		case "mp4":
		case "avi":
			this.typeFile = TYPE_FILE_VIDEO;
			break;

		case "png":
		case "jpg":
		case "jpeg":
			this.typeFile = TYPE_FILE_IMAGE;
			break;

		case "mp3":
		case "wav":
		case "ogg":
			this.typeFile = TYPE_FILE_AUDIO;
			break;

		/*
		 * default: // Si inconnu ne fait rien break;
		 */
		}

		this.sourceFile = file;
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

}
