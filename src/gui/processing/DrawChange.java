package gui.processing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import gui.general.Context;

/**
 * Classe des listeners pour créer les formes
 */

public class DrawChange implements MouseMotionListener, MouseListener {

	private int x, y;
	private File im;
	private boolean redimensionner;
	private int endX, endY;

	private final int MARGE = 50;

	public DrawChange() {
		redimensionner = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		actualiserCoordonnees(e);
		selectionnerForme(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		((ProcessingModel)Context.$M).sendChanges();
		System.out.println("Mouse released !");
		redimensionner = false;
		endX = e.getX();
		endY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		// 0 => Marge gauche
		// 1 => Marge haut
		// 2 => Largeur
		// 3 => Longueur
		if (((ProcessingModel) Context.$M).isModeBlur() || ((ProcessingModel) Context.$M).isModeCrop()) {
			if (((ProcessingModel) Context.$M).isModeCrop() && x != 0) 
				((ProcessingModel) Context.$M).addForm(x, y, (e.getX() - x), (e.getY() - y), 'c', null);
			else if (((ProcessingModel) Context.$M).isModeBlur() && x != 0)
				((ProcessingModel) Context.$M).addForm(x, y, (e.getX() - x), (e.getY() - y), 'f', null);
		}
		
		if (((ProcessingModel) Context.$M) != null) {
			Form f = ((ProcessingModel) Context.$M).getCurrentForm();
			int[] tab = f.getFormValues();
			int formx = tab[0];
			int formy = tab[1];
			int width = tab[2];
			int height = tab[3];
			int decalageBaseX = x - endX;
			// 0 < X < 0
			// 1 < Y < 0+2
			// Gauche
			if (formx - MARGE < e.getX() && e.getX() < formx + MARGE && formx < e.getY() && e.getY() < formy + height) {
				//((ProcessingModel)Context.$M).addForm(formx + (e.getX()-formx), formy, width-(e.getX()-formx), height, 'i', im);	
				//redimensionner = true;
			}

			// 0 < X < 0+2
			// 1 < Y < 1
			// Haut
			else if (tab[0] < x && x < tab[0] + tab[2] && tab[1] - MARGE < y && y < tab[1] + MARGE) {
				//((ProcessingModel)Context.$M).addForm(x, y - e.getY(), x, y, 'i', im);
				
			}

			// 0+2 < X < 0+2
			// 1 < Y < 1+3
			else if (tab[0] + tab[2] - MARGE < x && x < tab[0] + tab[2] + MARGE && tab[1] < y && y < tab[1] + tab[3]) {
				//System.out.println("Modification droit");
				//((ProcessingModel)Context.$M).addForm(x, y, e.getX(), y, 'i', im);
			}

			// 0 < x < 0+2
			// 1+3 < y < 1+3
			else if (tab[0] < x && x < tab[0] + tab[2] && tab[1] + tab[3] - MARGE < y && y < tab[1] + tab[3] + MARGE) {
				//System.out.println("Modification bas ");
				//((ProcessingModel)Context.$M).addForm(x, y, x, e.getY(), 'i', im);
			}
		}
if (((ProcessingModel) Context.$M).getCurrentForm() != null) {
	
	if(!redimensionner) {
		int[] formValues = ((ProcessingModel) Context.$M).getCurrentForm().getFormValues();
		int originXForm = formValues[0];
		int originYForm = formValues[1];
		int endXForm = originXForm + formValues[2];
		int endYForm = originYForm + formValues[3];
		// Condition ajoutée
		if ((e.getX() > originXForm + MARGE && e.getX() < endXForm - MARGE)
				&& (e.getY() > originYForm - MARGE && e.getY() < endYForm + MARGE)) {

			if (!(((ProcessingModel) Context.$M).isModeBlur() || ((ProcessingModel) Context.$M).isModeCrop())) {
	
				((ProcessingModel) Context.$M).getCurrentForm().setPosition(e.getX() - (formValues[2] / 2), e.getY() - (formValues[3] / 2));
				((ProcessingModel) Context.$M).sendChanges();
				}
			}

		}
		}
	endX = e.getX();
	endY = e.getY();
}	
		
		
	

	private void actualiserCoordonnees(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		System.out.println("Coordonnees actualisees : x=" + x + ", y=" + y);
	}
	
	private void selectionnerForme(MouseEvent e) {
		/* Actualise l'element selectionne selon la position de la souris */
		for (Form f : ((ProcessingModel) Context.$M).getForms()) {
			int[] formValues = f.getFormValues();
			int originXForm = formValues[0];
			int originYForm = formValues[1];
			int endXForm = originXForm + formValues[2];
			int endYForm = originYForm + formValues[3];
			if ((e.getX() > originXForm && e.getX() < endXForm) && (e.getY() > originYForm && e.getY() < endYForm)) {
				((ProcessingModel) Context.$M).setCurrentForm(f);
				if (f.getFormType() == 'i') {
					im = f.getFormImage();
				} else
					im = null;
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		selectionnerForme(e);
		
	}
}
/*
if (((ProcessingModel) Context.$M).getCurrentForm() != null) {
	int[] values = ((ProcessingModel) Context.$M).getCurrentForm().getFormValues();
	// Gauche
	if (values[0] - MARGE < x && x < values[0] + MARGE && values[0] < y && y < values[1] + values[3]) {
		System.out.println("Zone gauche");
	}
	// Haut
	else if (values[0] < x && x < values[0] + values[2] && values[1] - MARGE < y && y < values[1] + MARGE) {
		System.out.println("Zone haute");
	}
	// Droit
	else if (values[0] + values[2] - MARGE < x && x < values[0] + values[2] + MARGE && values[1] < y && y < values[1] + values[3]) {
		System.out.println("Zone droite");
	}
	// Bas
	else if (values[0] < x && x < values[0] + values[2] && values[1] + values[3] - MARGE < y && y < values[1] + values[3] + MARGE) {
		System.out.println("Zone basse");
	}
}*/
/*	int dragX = e.getX();
int dragY = e.getY();

if (form != null) {
	int[] tab = form.getFormValues();
	int formx = tab[0];
	int formy = tab[1];
	int width = tab[2];
	int height = tab[3];
	
	int decalageBaseX = x - dragX;
	int decalageBaseY = Math.abs(y - dragY);
	
	if(dragX < formx + MARGE && dragX > formx && dragY < formy+height && dragY > formy) {
		//((ProcessingModel)Context.$M).addForm(formx + (dragX-formx), formy, width+decalageBaseX, height, 'i', im);
		//System.out.println("OK");
		redimensionner = true;
	}
/*if (tab[0] - MARGE < x && x < tab[0] + MARGE && tab[0] < y && y < tab[1] + tab[3]) {
	((ProcessingModel)Context.$M).addForm(tab[0]-x, y, tab[3]+x, y, 'i', im);
}
}*/
/*if (form != null) {
	int[] tab = form.getTab();
	// 0 < X < 0
	// 1 < Y < 0+2
	// Gauche
	

	// 0 < X < 0+2
	// 1 < Y < 1
	// Haut
	else if (tab[0] < x && x < tab[0] + tab[2] && tab[1] - MARGE < y && y < tab[1] + MARGE) {
		((ProcessingModel)Context.$M).addForm(x, y - e.getY(), x, y, 'i', im);
	}

	// 0+2 < X < 0+2
	// 1 < Y < 1+3
	else if (tab[0] + tab[2] - MARGE < x && x < tab[0] + tab[2] + MARGE && tab[1] < y && y < tab[1] + tab[3]) {
		System.out.println("Modification droit");
		((ProcessingModel)Context.$M).addForm(x, y, e.getX(), y, 'i', im);
	}

	// 0 < x < 0+2
	// 1+3 < y < 1+3
	else if (tab[0] < x && x < tab[0] + tab[2] && tab[1] + tab[3] - MARGE < y && y < tab[1] + tab[3] + MARGE) {
		System.out.println("Modification bas ");
		((ProcessingModel)Context.$M).addForm(x, y, x, e.getY(), 'i', im);
	}
}
*/