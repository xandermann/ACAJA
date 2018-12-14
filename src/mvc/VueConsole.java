package mvc;
import java.util.Observable;
import java.util.Observer;

public class VueConsole implements Observer {

	public VueConsole(Modele modele) {

		modele.addObserver(this);

		System.out.println(modele.getNumero());

	}

	@Override
	public void update(Observable o, Object arg) {

		int compte = ((Modele) o).getNumero();

		System.out.println(compte);

	}

}
