package gui.processing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *Classe des listeners pour créer les formes
 */

public class DrawChange implements MouseMotionListener,MouseListener{
	
	private int nbClick;
	private int refx,refy,xx,yy;
	private ProcessingModel model;
	
	public DrawChange(ProcessingModel m) {
		this.model = m;
		nbClick = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		/*
		if(this.model.isfUp() || this.model.iscropUp()) {
			int x = e.getX();
			int y = e.getY();
			if(nbClick==0) {
				refx = x;
				refy = y;
			}else {
				if(this.model.iscropUp()) 
					this.model.addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'c');	
				
				else if(this.model.isfUp())
					this.model.addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'f');	
				
				nbClick = -1;
			}
			
			nbClick++;
		}*/
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
	public void mousePressed(MouseEvent e) {
		if(this.model.isfUp() || this.model.iscropUp()) {
			refx = e.getX();
			refy = e.getY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.model.isfUp() || this.model.iscropUp()) {
			
			if(this.model.iscropUp()) 
				this.model.addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'c');	
				
			else if(this.model.isfUp())
				this.model.addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'f');	
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
