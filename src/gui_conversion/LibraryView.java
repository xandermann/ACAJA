package gui_conversion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class LibraryView extends JList implements Observer, MouseListener, KeyListener {

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
		this.addMouseListener(this);
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
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.model.setCurrentFile(this.getSelectedValue().toString());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_DELETE){
			 this.model.remove(this.model.getCurrentFile());
		 }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
