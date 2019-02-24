package gui.conversion.views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import gui.conversion.model.ConversionModel;

public final class LibraryViewController extends JList implements Observer, MouseListener, KeyListener {
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private ConversionModel model;

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public LibraryViewController(ConversionModel model, DefaultListModel listModel) {
		super(listModel);
		this.model = model;
		setFixedCellWidth(250);
		setVisible(true);
		addMouseListener(this);
		addKeyListener(this);
	}

	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		setModel(model.getFilenames());
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		model.setCurrentFile(getSelectedValue().toString());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_DELETE) model.remove(model.getCurrentFile());	 
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	

	//=======================================================================================================================
	//=======================================================================================================================
}
