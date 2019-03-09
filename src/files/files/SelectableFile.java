package files.files;

import java.io.File;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.MediaFileType;
import resources.TimeTools;
import wrapper.runtime.global.SystemRequests;


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
	
	/**
	 * Miniature de la video. 
	 */
	protected File thumbail;
	
	
	
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
	 * 
	 * @throws UnfindableResourceException 
	 */
	public SelectableFile(File sourceFile) throws IncorrectFileException, UnfindableResourceException {
		if((this.sourceFile = sourceFile) == null)
			throw new NullPointerException("Le fichier source recu en parametre est null !");
		isSelected = false;
		destinationFile = new String[]{"", "", ""};
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
		
		String[] videoExtensions = {"3g2","3gp","asf","avi","flv","m4v","mov","mkv","mp2","mp4","mpeg","mpg","ogg","webm","wmv"};
		for(String vidExt : videoExtensions) {
			if(fileName.endsWith(vidExt)) {
				typeFile = MediaFileType.MEDIA_FILE_VIDEO;
				return;
			}
		}
		
		String[] audioExtensions = {"mp3","wav", "ogg", "flac", "aac"};
		for(String audExt : audioExtensions) {
			if(fileName.endsWith(audExt)) {
				typeFile = MediaFileType.MEDIA_FILE_AUDIO;
				return;
			}
		}
		
		String[] imageExtensions = {"png","jpg","jpeg","bmp"};
		for(String imgExt : imageExtensions) {
			if(fileName.endsWith(imgExt)) {
				typeFile = MediaFileType.MEDIA_FILE_IMAGE;
				return;
			}
		}
		
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
	
	public void setFileExtension(String fileExtension) {
		this.destinationFile[2] = fileExtension;
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

	private String removeExtension (String fileName) {
        if (fileName == "") return "";
        int pos = fileName.lastIndexOf(".");
        if (pos == -1) return fileName;
        return fileName.substring(0, pos);
    }
	
	public String getSourceFileNameWithoutExtension() {
		return removeExtension(sourceFile.getName());
	}
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer le nom complet du fichier.
	 * 
	 * @return String 	Le nom complet du fichier source.
	 */
	public String getSourceFileFullName() {
		return sourceFile.getAbsolutePath();
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
		return destinationFile[0].equals("") && destinationFile[1].equals("") && destinationFile[2].equals("") ? "" : destinationFile[0]+File.separator+destinationFile[1]+destinationFile[2];
	}



	public File getThumbail() {
		return thumbail;
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
}
