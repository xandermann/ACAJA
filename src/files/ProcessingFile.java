package files;
import java.io.File;
import java.util.HashMap;

import exceptions.IncorrectFileException;
import wrapper.SystemRequests;
/**
 * TODO comentaire a faire. 
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ProcessingFile extends SelectableFile implements Modifiable{
	//=======================================================================================================================
	//=======================================================================================================================
	
	/**
	 * [ ATTRIBUT D'INSTANCE DE LA CLASSE. ]
	 */

	/**
	 * Les traitements en attente sur this. 
	 */
	private HashMap<ProcessType, Object> performedProcessings;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param sourceFile		Le fichier source.
	 * 
	 * @throws IncorrectFileException 
	 */
	public ProcessingFile(File sourceFile) throws IncorrectFileException {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(sourceFile);
		
		/**
		 * INITIALISATION DE TABLE DES TRAITEMENTS EN ATTENTE SUR THIS. 
		 */
		performedProcessings = new HashMap<ProcessType, Object>();
		
		/**
		 * INITIALISATION DE LA DUREE DU SON OU DE LA VIDEO. 
		 * 
		 * Si le fichier source est une image alors la duree prend -1, 
		 * car une image n'a pas de duree. 
		 */
		duration = containsAudio() ? SystemRequests.getDuration(sourceFile) : -1;			
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
	public void modify(OperationType typeProcess, Object process) { 
		performedProcessings.put((ProcessType) typeProcess, process);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE POUR SAVOIR SI DES TRAIEMENTS SONT EN ATTENTE SUR CE FICHIER. ]
	 */
	public boolean isModified() {
		return  !performedProcessings.equals(new HashMap<ProcessType, Object>());
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
	public HashMap<ProcessType, Object> getPerformedProcessings() {
		return performedProcessings;
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
