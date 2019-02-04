package wrapper;

/**
 * [ CLASSE POUR LE PARCOURS DE STDERR PAR UN ITERATEUR. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class ErrorStreamIterator extends StreamIterator{
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param processToStudy	Process a etudier.
	 */
	public ErrorStreamIterator(Process processToStudy) {
		super(processToStudy.getErrorStream());
	}
}
