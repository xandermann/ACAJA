package files;
import java.io.File;
import java.util.HashMap;
import ffmpeg_tools.SystemRequests;
/**
 * TODO comentaire a faire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ProcessingFile extends SelectableFile implements State{
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 * [ ATTRIBUTS D'INSTANCE. ]
	 */

	/**
	 * Les traitements en attente sur this. 
	 */
	private HashMap<Integer, Object> performedProcessings;

	
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
		this.performedProcessings = new HashMap<Integer, Object>();
		
		/**
		 * INITIALISATION DE LA DUREE DU SON OU DE LA VIDEO. 
		 * 
		 * Si le fichier source est une image alors la duree prend -1, 
		 * car une image n'a pas de duree. 
		 */
		this.duration = containsAudio() ? SystemRequests.getDuration(this.sourceFile) : -1;			
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
	public void modify(Integer typeProcess, Object process) { 
		performedProcessings.put(typeProcess, process);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR SAVOIR SI DES TRAIEMENTS SONT EN ATTENTE SUR CE FICHIER. ]
	 */
	public boolean isModified() {
		return  !performedProcessings.equals(new HashMap<Integer, Object>());
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer les traitements en attente. 
	 * 
	 * @return HashMap<String, Object>		Les traitements en attente.
	 */
	public HashMap<Integer, Object> getPerformedProcessings() {
		return performedProcessings;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
