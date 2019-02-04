package wrapper;

import java.util.Iterator;

/**
 * [ CLASSE POUR LA GESTION DU PROCESS. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ProcessManager implements Iterable<String> {
	/**
	 * [ ATTRIBUT D'INSTANCE. ]
	 * 
	 * Le process a gerer.
	 */
	private Process processToManage;

	
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param processToManage		Le process a gerer.
	 */
	public ProcessManager(Process processToManage) {
		this.processToManage = processToManage;
	}
	
	
	/**
	 * [ ITERATEUR PAR DEFAUT. ]
	 * 
	 * Par defaut ffmpeg envoie l'ensemble de presque
	 * tous ses messages par le flux d'erreur ( oui c'est 
	 * plus etonnant a savoir. ).
	 */
	@Override
	public Iterator<String> iterator() {
		return new ErrorStreamIterator(processToManage);
	}
	
	
	/**
	 * [ ITERATEUR SUR LE FLUX STDERR. ]
	 * 
	 * @return ErrorStreamIterator	L'iterateur.
	 */
	public ErrorStreamIterator errorStreamIterator() {
		return new ErrorStreamIterator(processToManage);
	}
	
	
	/**
	 * [ ITERATEUR SUR LE FLUX STDOUT. ]
	 * 
	 * @return InputStreamIterator	L'iterateur.
	 */
	public InputStreamIterator inputStreamIterator() {
		return new InputStreamIterator(processToManage);
	}
	
	
	/**
	 * [ GETTER - METHODE ACCESSEUR SUR LE FLUX. ]
	 * 
	 * @return Process		Le process.
	 */
	public Process getManagedProcess() {
		return processToManage;
	}
}
