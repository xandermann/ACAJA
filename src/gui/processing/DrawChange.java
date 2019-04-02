package gui.processing;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gui.general.Context;

/**
 *Classe des listeners pour créer les formes
 */

public class DrawChange implements MouseMotionListener,MouseListener{
	
	private int refx,refy;
	private ProcessingModel model;
	private Form form;
	private Image im;
	
	public DrawChange(ProcessingModel m) {
		this.model = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) {	}

	@Override
	public void mousePressed(MouseEvent e) {
		refx = e.getX();
		refy = e.getY();
		
		int indice = 0;
		for (int i = 0; i< model.getListRect().size();i++) {
			if(model.getListRect().get(i).getTypeCommande() == 'i') {
				indice = i;
				form = model.getListRect().get(indice);
				im = form.getImageA();
			}
		}
		
		
		
		// 0 => Marge gauche
		// 1 => Marge haut
		// 2 => Largeur
		// 3 => Longueur
		if(
				e.getX() > form.getTab()[0]-3 && 
				e.getX() < form.getTab()[0]+3 && 
				e.getY() > form.getTab()[1] && 
				e.getY() < form.getTab()[0]+form.getTab()[3])
		{
			System.out.println("click gauche");
		}
		
		
		// TODO
		if(
				e.getY() > form.getTab()[1]-3 &&
				e.getY() < form.getTab()[1]+3				
		) {
			System.out.println("click haut");
		}
		
		
		// TODO
		if(
				e.getX() > form.getTab()[2]-3 && 
				e.getX() < form.getTab()[2]+3 && 
				e.getY() > form.getTab()[3] && 
				e.getY() < form.getTab()[2]+form.getTab()[0]		
		) {
			System.out.println("Click droit");
		}
		
		/*
		// TODO
		if(e.getX() > form.getTab()[0]) {
			System.out.println("Clique bas");
		}
		*/
		
		/*
		if(e.getY() > form.getTab()[1]-3 && e.getY() < form.getTab()[1]+3) {//marche pas
			System.out.println("click");
		}*/
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(((ProcessingModel)Context.$M).isfUp() || ((ProcessingModel)Context.$M).iscropUp()) {
			if(((ProcessingModel)Context.$M).iscropUp() && refx != 0) 
				((ProcessingModel)Context.$M).addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'c',null);	
				
			else if(((ProcessingModel)Context.$M).isfUp() && refx != 0)
				((ProcessingModel)Context.$M).addForm(refx, refy, (e.getX()-refx), (e.getY()-refy),'f',null);
			}
		
		if(form != null)
			if(e.getX() > form.getTab()[0] && e.getX() < form.getTab()[0]+form.getTab()[2] && e.getY() > form.getTab()[1] && e.getY() < form.getTab()[0]+form.getTab()[3]) {
				model.addForm(e.getX()-(form.getTab()[2]/2),e.getY()-(form.getTab()[3]/2), form.getTab()[2],form.getTab()[3], 'i', im);
			}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {	}

}
