package gui_conversion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class LibraryView extends JList implements Observer, ActionListener {

	/**
	 * Model conversion
	 */
	private ConversionModel model;

	/**
	 * Constructeur
	 * @param p_model Model conversion
	 * @param p_listmodel Liste par defaut
	 */
	public LibraryView(ConversionModel p_model, DefaultListModel p_listmodel) {
		super(p_listmodel);
		this.model = p_model;
		this.reevaluatePanel();
		this.setVisible(true);
	}

	/**
	 * Réévalue le Panel
	 */
	private void reevaluatePanel() {
		this.setFixedCellWidth(400);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setModel(this.model.getFilenames());
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setModel(this.model.getFilenames());

	}

}
