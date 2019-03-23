package gui.conversion.views;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.conversion.ConversionModel;
import gui.style.StyleTheme;
import gui.style.StylizedJPanel;

/**
 * [ SUPERCLASSE ABSTRAITE DES VUES DES PARAMETRES. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public abstract class SettingsView extends StylizedJPanel implements Observer {
	/**
	 * Indique si un changement est en cours. 
	 */
	protected boolean isChange;
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES. ]
	 */
	public SettingsView() {
		super();
		isChange = true;
		setSize(new Dimension(320, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public abstract void update(Observable o, Object arg);
}
