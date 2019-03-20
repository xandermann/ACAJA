package files.files;

import java.io.File;
import java.util.HashMap;

import exceptions.IncorrectFileException;
import exceptions.UnfindableResourceException;
import files.enumerations.OperationType;
import files.enumerations.ProcessingType;
import wrapper.runtime.global.SystemRequests;

/**
 * TODO comentaire a faire.
 * 
 * Auteurs du projet :
 * 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et
 *         CHEVRIER Jean-christophe.
 */
public final class ProcessingFile extends SelectableFile implements Modifiable {
	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ ATTRIBUT D'INSTANCE DE LA CLASSE. ]
	 */

	/**
	 * Les traitements en attente sur this.
	 */
	private HashMap<ProcessingType, String> performedProcessings;

	private String resolution;

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param sourceFile Le fichier source.
	 * 
	 * @throws IncorrectFileException      L'exception sur les fichiers de type
	 *                                     incorrect.
	 * 
	 * @throws UnfindableResourceException
	 */
	public ProcessingFile(File sourceFile) throws IncorrectFileException, UnfindableResourceException {
		/**
		 * INITIALISATION DES ATTRIBUTS HETITES DE LA CLASSE SELECTABLEFILE.
		 */
		super(sourceFile);

		/**
		 * INITIALISATION DE LA TABLE DES TRAITEMENTS EN ATTENTE SUR THIS.
		 */
		performedProcessings = new HashMap<ProcessingType, String>();

		/**
		 * Initialisation de la miniature et de la resolution.
		 */
		if (isVideo()) {
			thumbail = SystemRequests.askFrame(this, "00:00:01.00");
			resolution = SystemRequests.askResolution(this);
		}
	}

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ METHODE POUR AJOUTER UN TRAITEMENT EN ATTENTE A THIS. ]
	 * 
	 * @param string Le type de taritement a ajouter ne attente.
	 * @param String Les arguments necessaires a connaitre pour executer les
	 *               traitements.
	 */
	public void modify(OperationType typeProcess, String process) {
		performedProcessings.put((ProcessingType) typeProcess, process);
	}

	/**
	 * [ METHODE POUR SAVOIR SI DES TRAIEMENTS SONT EN ATTENTE SUR CE FICHIER. ]
	 */
	public boolean isModified() {
		return !performedProcessings.equals(new HashMap<ProcessingType, String>());
	}

	// =======================================================================================================================
	// =======================================================================================================================

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * Methode pour recuperer les traitements en attente.
	 * 
	 * @return HashMap<String, String> Les traitements en attente.
	 */
	public HashMap<ProcessingType, String> getPerformedProcessings() {
		return performedProcessings;
	}

	/**
	 * [ METHODE ACCESSEUR - GETTER. ]
	 * 
	 * @return la resolution.
	 */
	public String getResolution() {
		return resolution;
	}

	// =======================================================================================================================
	// =======================================================================================================================
}
