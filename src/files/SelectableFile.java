package files;

import java.io.File;

public class SelectableFile {
	//=======================================================================================================================
	//=======================================================================================================================
	

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


	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Definition a partir du fichier source
	 * 
	 * @param file Fichier source
	 */
	public SelectableFile(File file) {
		if(file == null)
			throw new NullPointerException("Erreur : fichier fourni en parametre null !");
		this.isSelected = false;
		this.sourceFile = file;
		this.getFileType();
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isGoodFile(SelectableFile file) {
		return ( isVideo(file) || isSound(file) );
	}
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isVideo(SelectableFile file) {
		return file.getTypeFile()==SelectableFile.FILE_TYPE_VIDEO;
	}
	
	/**
	 * [ METHODE DE CLASSE. ]
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isSound(SelectableFile file) {
		return file.getTypeFile()==SelectableFile.FILE_TYPE_AUDIO;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * Retourne l'extension du fichier courant (a partir des constantes de la classe)
	 * @return L'extension du fichier (0,1 ou 2) selon les constantes
	 */
	private void getFileType() {
		String fileName = this.sourceFile.getName();

		if(fileName.endsWith("mp4") || fileName.endsWith("avi")) 
			this.typeFile = FILE_TYPE_VIDEO;
		else if(fileName.endsWith("png") || fileName.endsWith("jpg") || fileName.endsWith("jpeg")) 
				this.typeFile = FILE_TYPE_IMAGE;
			else if(fileName.endsWith("mp3") || fileName.endsWith("wav") || fileName.endsWith("ogg"))
					this.typeFile = FILE_TYPE_AUDIO;
	}

	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
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
	
	/**
	 * 
	 * @return
	 */
	public File getSourceFile() {
		return sourceFile;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
