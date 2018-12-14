package mvc;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class VueGraphique extends JLabel implements Observer {

	public VueGraphique(Modele modele) {

		modele.addObserver(this);

		this.setText("" + modele.getNumero());

	}

	@Override
	public void update(Observable o, Object arg) {

		int compte = ((Modele) o).getNumero();

		this.setText("" + compte);

	}

}
