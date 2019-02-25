package files.files;

import java.io.File;

import exceptions.IncorrectFileException;
import files.enumerations.MediaFileType;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public class SelectableFile {
	//=======================================================================================================================
	//=======================================================================================================================

	
	
	/**
	 * [ ATTRIBUTS D'INSTANCE DE LA CLASSE. ]
	 */
	
	/**
	 * La duree de la video.
	 */
	protected String duration;

	/**
	 * Le type de fichier.
	 */
	protected MediaFileType typeFile;

	/**
	 * Le fihier source.
	 */
	protected File sourceFile;

	/**
	 * Cet attribut permet d'indiquer si this est selectionne.
	 */
	private boolean isSelected;

	/**
	 * Le nom complet (chemin+nom) du fichier de sortie.
	 */
	private String[] destinationFile;
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * Ce constructeur intialise les attributs.
	 * 
	 * @param sourceFile	Le fichier source.
	 * 
	 * @throws IncorrectFileException 
	 */
	public SelectableFile(File sourceFile) throws IncorrectFileException {
		if((this.sourceFile = sourceFile) == null)
			throw new NullPointerException("Le fichier source recu en parametre est null !");
		isSelected = false;
		destinationFile = new String[]{"", ""};
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
		return typeFile == MediaFileType.MEDIA_FILE_IMAGE;
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
		return typeFile == MediaFileType.MEDIA_FILE_VIDEO;
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
		return typeFile == MediaFileType.MEDIA_FILE_AUDIO;
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
	 * 3 valeurs possiblement accordable a typeFile : 
	 * - MEDIA_FILE_VIDEO ; 
	 * - MEDIA_FILE_AUDIO ; 
	 * - MEDIA_FILE_IMAGE.
	 * 
	 * @throws IncorrectFileException 	Exception sur les fichiers non conformes. 
	 */
	private void whoAmI() throws IncorrectFileException {
		String fileName = sourceFile.getName().toLowerCase();
		if (fileName.endsWith("mp4") || fileName.endsWith("avi") || fileName.endsWith("flv"))
			typeFile = MediaFileType.MEDIA_FILE_VIDEO;
		else if (fileName.endsWith("mp3") || fileName.endsWith("wav") || fileName.endsWith("ogg"))
				typeFile = MediaFileType.MEDIA_FILE_AUDIO;
			else if (fileName.endsWith("png") || fileName.endsWith("jpg") || fileName.endsWith("jpeg"))
					typeFile = MediaFileType.MEDIA_FILE_IMAGE;
				else
					throw new IncorrectFileException(IncorrectFileException.FORBIDDEN_FILE);
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
	 * @return  
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * [ METHODE ACCESSEUR - SETTER. ]
	 * 
	 * Methode pour modifier la duree d'une video.
	 * 
	 * @return
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * [ METHODE ACCESSEUR - SETTER. ]
	 * 
	 * Methode pour modifier le chemin du fichier de destination.
	 * 
	 * @return
	 */
	public void setDestinationPath(String destinationFolder) {
		this.destinationFile[0] = destinationFolder;
	}
	
	/**
	 * [ METHODE ACCESSEUR - SETTER. ]
	 * 
	 * Methode pour modifier le nom du fichier de destination.
	 * 
	 * @return
	 */
	public void setDestinationName(String destinationName) {
		this.destinationFile[1] = destinationName;
	}
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le type du fichier.
	 * 
	 * @return MediaFileType	 Le type du fichier.
	 */
	public MediaFileType getTypeFile() {
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
	 * Methode pour recuperer le nom du fichier.
	 * 
	 * @return String 	Le nom du fichier source.
	 */
	public String getSourceFileName() {
		return sourceFile.getName();
	}
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le nom complet du fichier.
	 * 
	 * @return String 	Le nom complet du fichier source.
	 */
	public String getSourceFileFullName() {
		return sourceFile.getPath();
	}
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le nom du fichier de destiation.
	 * 
	 * @return String 	Le nom du fichier source.
	 */
	public String getDestinationFileName() {
		return destinationFile[1];
	}	
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le nom complet (chemin+nom) du fichier.
	 * 
	 * @return String 	Le nom complet du fichier source.
	 */
	public String getDestinationFileFullName() {
		if(destinationFile[0]=="" && destinationFile[1]=="")
			return "";
		else
			return destinationFile[0]+File.separator+destinationFile[1];
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
