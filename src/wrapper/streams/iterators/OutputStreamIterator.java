package wrapper.streams.iterators;

/**
 * [ CLASSE POUR LE PARCOURS DE STDOUT PAR UN ITERATEUR. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class OutputStreamIterator extends StreamIterator{
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param processToStudy	Process a etudier.
	 */
	public OutputStreamIterator(Process processToStudy) {
		super(processToStudy.getInputStream());
	}
}