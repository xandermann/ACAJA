package wrapper.streams;

/**
 * [ CLASSE POUR LE PARCOURS DE STDOUT PAR UN ITERATEUR. ]
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class InputStreamIterator extends StreamIterator{
	/**
	 * [ CONSTRUCTEUR. ]
	 * 
	 * @param processToStudy	Process a etudier.
	 */
	public InputStreamIterator(Process processToStudy) {
		super(processToStudy.getInputStream());
	}
}