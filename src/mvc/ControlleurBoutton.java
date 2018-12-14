package mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlleurBoutton implements ActionListener {

	private Modele modele;

	public ControlleurBoutton(Modele modele) {
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.modele.compter();
	}

}
