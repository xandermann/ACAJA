package files;

import java.io.File;
import java.util.HashMap;

import ffmpeg_tools.SystemRequests;

public class ProcessingFile extends SelectableFile{
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 * [ ATTRIBUTS D'INSTANCE. ]
	 */
	
	/**
	 * La duree du fichier source ( elle prendra -1 a l'initialisation,
	 * si le fichier source est une image ).
	 */
	private long duration;

	/**
	 * Les traitements en attente sur this. 
	 */
	private HashMap<String, Object> performedProcessings;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param file		Le fichier source.
	 */
	public ProcessingFile(File file) {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(file);
		
		/**
		 * INITIALISATION DE TABLE DES TRAITEMENTS EN ATTENTE SUR THIS. 
		 */
		performedProcessings = new HashMap<String, Object>();
		
		/**
		 * INITIALISATION DE LA DUREE DU SON OU DE LA VIDEO. 
		 * 
		 * Si le fichier source est une image alors la duree prend -1, 
		 * car une image n'a pas de duree. 
		 */
		duration = isGoodFile() ? SystemRequests.getDuration(sourceFile) : -1;			
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR AJOUTER UN TRAITEMENT EN ATTENTE A THIS. ]
	 * 
	 * @param string		Le type de taritement a ajouter ne attente. 
	 * @param object		Les arguments necessaires a connaitre pour executer 
	 * 						les traitements. 
	 */
	public void performProcess(String typeProcess, Object process) {
		//TODO provisoire a ameliorer. 
		performedProcessings.put(typeProcess, process);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR SAVOIR SI DES TRAIEMENTS SONT EN ATTENTE SUR CE FICHIER. ]
	 */
	public boolean isModified() {
		return  !performedProcessings.equals(new HashMap<String, Object>());
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * Recupere le processus acheve
	 * @return le processus
	 */
	public HashMap<String, Object> getPerformedProcessings() {
		return performedProcessings;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
