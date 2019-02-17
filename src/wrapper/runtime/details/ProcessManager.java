package wrapper.runtime.details;

import java.util.Iterator;
import wrapper.streams.*;

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
		if((this.processToManage = processToManage) == null)
			throw new NullPointerException("Le Process recu en parametre est null !");
	}
	
	
	/**
	 * [ ITERATEUR PAR DEFAUT. ]
	 * 
	 * Par defaut ffmpeg envoie l'ensemble de presque
	 * tous ses messages par le flux d'erreur ( oui c'est 
	 * assez etonnant a savoir. ).
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
	 * @return OutputStreamIterator	L'iterateur.
	 */
	public OutputStreamIterator outputStreamIterator() {
		return new OutputStreamIterator(processToManage);
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
