package gui.processing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawChange implements MouseMotionListener,MouseListener{
	
	private int nbClick;
	private int refx,refy;
	private Model model;
	
	public DrawChange(Model m) {
		this.model = m;
		nbClick = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(nbClick==0) {
			refx = x;
			refy = y;
		}else {
			this.model.addRect(refx, refy, e.getX(), e.getY(),'r');				
			nbClick = -1;
		}
		
		nbClick++;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
