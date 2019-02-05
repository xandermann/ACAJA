package conversion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public final class LibraryView extends JList implements Observer, MouseListener, KeyListener {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * Model conversion
	 */
	private ConversionModel model;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * Constructeur
	 * @param p_model Model conversion
	 * @param p_listmodel Liste par defaut
	 */
	public LibraryView(ConversionModel p_model, DefaultListModel p_listmodel) {
		super(p_listmodel);
		this.model = p_model;
		this.setFixedCellWidth(400);
		this.setVisible(true);
		this.addMouseListener(this);
		this.addKeyListener(this);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		this.setModel(this.model.getFilenames());
		this.repaint();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.model.setCurrentFile(this.getSelectedValue().toString());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_DELETE) this.model.remove(this.model.getCurrentFile());	 
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	
	//=======================================================================================================================
	//=======================================================================================================================
}
