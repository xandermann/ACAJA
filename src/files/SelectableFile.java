package files;

import java.io.File;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public class SelectableFile{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTANTES DE CLASSE. ]
	 * 
	 * Ces constantes permettent d'indiquer le type du fichier source d'une instance
	 * de la classe.
	 */

	/**
	 * Constante indiquant que le fichier est un fichier video.
	 */
	public final static int FILE_TYPE_VIDEO = 0;

	/**
	 * Constante indiquant que le fichier est un fichier audio.
	 */
	public final static int FILE_TYPE_AUDIO = 1;

	/**
	 * Constante indiquant que le fichier est un fichier image.
	 */
	public final static int FILE_TYPE_IMAGE = 2;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 * [ ATTRIBUTS D'INSTANCE DE LA CLASSE. ]
	 */
	
	/**
	 * La duree de la video.
	 */
	protected long duration;

	/**
	 * Le type de fichier.
	 */
	protected int typeFile;

	/**
	 * Le fihier source.
	 */
	protected File sourceFile;

	/**
	 * Cet attribut permet d'indiquer si this est selectionne.
	 */
	private boolean isSelected;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * Ce constructeur intialise les attributs.
	 * 
	 * @param sourceFile	Le fichier source.
	 */
	public SelectableFile(File sourceFile) {
		isSelected = false;
		this.sourceFile = sourceFile;
		whoAmI();
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE - ROLE PRIMITIF. ]
	 * 
	 * Cette methode a un role primitif, elle permet de verifier si le fichier
	 * source est une image.
	 * 
	 * @return booleen Vaut true si le fichier source est une image.
	 */
	public boolean isImage() {
		return typeFile == SelectableFile.FILE_TYPE_IMAGE;
	}

	/**
	 * [ METHODE - ROLE PRIMITIF. ]
	 * 
	 * Cette methode a un role primitif, elle permet de verifier si le fichier
	 * source est une video.
	 * 
	 * @return booleen Vaut true si le fichier source est une video.
	 */
	public boolean isVideo() {
		return typeFile == SelectableFile.FILE_TYPE_VIDEO;
	}

	/**
	 * [ METHODE - ROLE PRIMITIF. ]
	 * 
	 * Cette methode a un role primitif, elle permet de verifier si le fichier
	 * source est un son.
	 * 
	 * @return booleen Vaut true si le fichier source est un son.
	 */
	public boolean isSound() {
		return typeFile == SelectableFile.FILE_TYPE_AUDIO;
	}

	/**
	 * [ METHODE - ROLE PRIMITIF. ]
	 * 
	 * Cette methode a un role primitif, elle permet se verifier si le fichier
	 * source est un son ou une video.
	 * 
	 * @return booleen Vaut true si le fichier source est une video ou un son.
	 */
	public boolean containsAudio() {
		return (isVideo() || isSound());
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================

	
	/**
	 * [ METHODE POUR RENSEIGNER LE TYPE DE FICHIER. ]
	 * 
	 * Cette methode permet de savoir si le fichier source est une video, ou un son,
	 * ou meme une image.
	 * 
	 * 3 valeurs possiblement accordable a typeFile : - FILE_TYPE_VIDEO ( = 0 ) ; -
	 * FILE_TYPE_AUDIO ( = 1 ) ; - FILE_TYPE_IMAGE ( = 2 ).
	 */
	private void whoAmI() {
		String fileName = this.sourceFile.getName().toLowerCase();
		if (fileName.endsWith("mp4") || fileName.endsWith("avi") || fileName.endsWith("flv"))
			typeFile = FILE_TYPE_VIDEO;
		else if (fileName.endsWith("mp3") || fileName.endsWith("wav") || fileName.endsWith("ogg"))
				typeFile = FILE_TYPE_AUDIO;
			else if (fileName.endsWith("png") || fileName.endsWith("jpg") || fileName.endsWith("jpeg"))
					typeFile = FILE_TYPE_IMAGE;
				else
					throw new IllegalArgumentException("Extension du fichier non conforme !");
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour tester si this est selectionne.
	 * 
	 * @return booleen True si this est selectionne.
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * [ METHODE ACCESSEUR - SELECTIONNER THIS - SETTER. ]
	 * 
	 * Methode pour selectionner this.
	 */
	public void select() {
		isSelected = true;
	}

	/**
	 * [ METHODE ACCESSEUR - DESELECTIONNER THIS - SETTER. ]
	 * 
	 * Methode pour deselectionner this.
	 */
	public void deselect() {
		isSelected = false;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer la duree d'une video.
	 * 
	 * @return int Le type du fichier.
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * [ METHODE ACCESSEUR - SETTER. ]
	 * 
	 * Methode pour modifier la duree d'une video.
	 * 
	 * @return int Le type du fichier.
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le type du fichier.
	 * 
	 * @return int Le type du fichier.
	 */
	public int getTypeFile() {
		return typeFile;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le fichier source.
	 * 
	 * @return File Le fichier source.
	 */
	public File getSourceFile() {
		return sourceFile;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le nom du fichier
	 * 
	 * @return String Le nom du fichier source
	 */
	public String getSourceFilename() {
		return sourceFile.getName();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
