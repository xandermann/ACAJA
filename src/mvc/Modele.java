package mvc;
import java.util.Observable;

public class Modele extends Observable {

	/**
	 * Numero courant
	 */
	private int numero;

	/**
	 * Incremente de 1 le numero puis notifie la vue
	 */
	public void compter() {
		this.numero++;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Recupere le numero
	 * 
	 * @return Le numero
	 */
	public int getNumero() {
		return this.numero;
	}

}
