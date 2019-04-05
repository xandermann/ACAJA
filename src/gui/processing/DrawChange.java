package gui.processing;

import java.awt.Cursor;
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
	private int pressX, pressY;
	private File im;
	private static boolean redimensionner;
	private static boolean redimensionnerProportions;
	//private boolean resize;
	private Form f;
	private PictureVisualView pvv;

	public static boolean isRedimensionner() {
		return redimensionner;
	}

	public static void setRedimensionner(boolean redimensionner) {
		DrawChange.redimensionner = redimensionner;
	}

	public DrawChange(PictureVisualView setPvv) {
		redimensionner = false;
		redimensionnerProportions = false;
		pvv = setPvv;
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
		((ProcessingModel) Context.$M).sendChanges();
	}
	
	
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!redimensionner && !redimensionnerProportions)
			bougerForm(e);
		else if (redimensionner) {
			System.out.println("redim");
				if (((ProcessingModel) Context.$M) != null) {
					f = ((ProcessingModel) Context.$M).getCurrentForm();
					int[] tab = f.getFormValues();
					int formx = tab[0];
					int formy = tab[1];
					int width = tab[2];
					int height = tab[3];
					int deplacementX = (e.getX() - x) ;
					int deplacementY = (e.getY() - y) ;
					width = tab[2] + deplacementX;
					height = tab[3]   + deplacementY;
					((ProcessingModel) Context.$M).addForm(formx, formy, width, height, f.getFormType(), f.getFormImage());	;
					
					/*
					boolean zoneDeplacementVertical = (e.getX() > formx + (int)(formy+width)*0.3 && e.getX() < (formx + width) - (int)(formy+width)*0.3 && e.getY() > (formy+height) - (int)(formy+height)*0.3 && e.getY() < (formy+height)+(int)(formy+height)*0.3);
					boolean zoneDeplacementHorizontal = (e.getY() > formy + (int)(formy+height)*0.3 && e.getY() < (formy + height) - (int)(formy+height)*0.3 && e.getX() > (formx+width) - (int)(formx+width)*0.3 && e.getX() < (formx+width)+(int)(formx+width)*0.3);
					int deplacementX = e.getX() - x;
					int deplacementY = e.getY() - y;
					
					if(zoneDeplacementVertical && zoneDeplacementHorizontal) { // Forcer le deplacement horizontal si les points se chevauchent
						zoneDeplacementVertical = false;
						pvv.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					}
					
					if(zoneDeplacementHorizontal) { // Si zone pour deplacement horizontal
						if( deplacementX > 0)  // La souris va a droite
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1], width + (e.getX()-width), tab[3], f.getFormType(), f.getFormImage());		
						else if ( deplacementX < 0 )  // La souris va a gauche
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1], width - (width-e.getX()) , tab[3], f.getFormType(), f.getFormImage());		
					} else if (zoneDeplacementVertical) { // Si zone pour deplacement vertical
						if(deplacementY > 0) // La souris descend	
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1],tab[2], height + (e.getY()-height), f.getFormType(), f.getFormImage());		
						 else if (deplacementY < 0 ) // La souris monte
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1],tab[2], height - (height-e.getY()), f.getFormType(), f.getFormImage());
					}
					*/
				}
		} else if (redimensionnerProportions) {
			System.out.println("redim proper");
			if (((ProcessingModel) Context.$M) != null) {
				f = ((ProcessingModel) Context.$M).getCurrentForm();
				int[] tab = f.getFormValues();
				int formx = tab[0];
				int formy = tab[1];
				int width = tab[2];
				int height = tab[3];
				int moyenneGet = (e.getX() + e.getY())/2;
				int moyenneSrc = (x + y ) / 2;
				int deplacement = (moyenneGet - moyenneSrc) ;
				
				width = tab[2] + deplacement;
				height = tab[3] + deplacement;
				((ProcessingModel) Context.$M).addForm(formx, formy, width, height, f.getFormType(), f.getFormImage());	;
			}
		}
		x = e.getX();
		y = e.getY();
	}

	private void actualiserCoordonnees(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		pressX = e.getX();
		pressY = e.getX();
		System.out.println("Coordonnees : x:"+x+" y:"+y);
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
		
		if (((ProcessingModel) Context.$M) != null) {
			f = ((ProcessingModel) Context.$M).getCurrentForm();
			if(f != null) {
				int[] tab = f.getFormValues();
				int formx = tab[0];
				int formy = tab[1];
				int width = tab[2];
				int height = tab[3];
				
			
				boolean zoneDeplacementVertical = (e.getX() > formx + (int)(formy+width)*0.3 && e.getX() < (formx + width) - (int)(formy+width)*0.3 && e.getY() > (formy+height) - (int)(formy+height)*0.3 && e.getY() < (formy+height)+(int)(formy+height)*0.3);
				boolean zoneDeplacementHorizontal = (e.getY() > formy + (int)(formy+height)*0.3 && e.getY() < (formy + height) - (int)(formy+height)*0.3 && e.getX() > (formx+width) - (int)(formx+width)*0.3 && e.getX() < (formx+width)+(int)(formx+width)*0.3);
				if(redimensionner) {
					if(zoneDeplacementVertical) {
						pvv.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					} else if(zoneDeplacementHorizontal){
						pvv.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					} else {
						pvv.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}
											
						
			  }
			}
			
			

	}
	
	public void bougerForm(MouseEvent e) {
		// 0 => Marge gauche
				// 1 => Marge haut
				// 2 => Largeur
				// 3 => Longueur
				if (((ProcessingModel) Context.$M).isModeBlur() || ((ProcessingModel) Context.$M).isModeCrop()) {
					if (((ProcessingModel) Context.$M).isModeCrop() && pressX != 0)
						((ProcessingModel) Context.$M).addForm(pressX, pressY, (e.getX() - pressX), (e.getY() - pressY), 'c', null);
					else if (((ProcessingModel) Context.$M).isModeBlur() && pressX != 0)
						((ProcessingModel) Context.$M).addForm(pressX, pressY, (e.getX() - pressX), (e.getY() - pressY), 'f', null);
				}

				if (((ProcessingModel) Context.$M).getCurrentForm() != null) {

					int[] formValues = ((ProcessingModel) Context.$M).getCurrentForm().getFormValues();
					int originXForm = formValues[0];
					int originYForm = formValues[1];
					int endXForm = originXForm + formValues[2];
					int endYForm = originYForm + formValues[3];
					// Condition ajoutée
					if ((e.getX() > originXForm && e.getX() < endXForm)
							&& (e.getY() > originYForm && e.getY() < endYForm )) {

						if (!(((ProcessingModel) Context.$M).isModeBlur() || ((ProcessingModel) Context.$M).isModeCrop())) {

							((ProcessingModel) Context.$M).getCurrentForm().setPosition(e.getX() - (formValues[2] / 2),
									e.getY() - (formValues[3] / 2));
							((ProcessingModel) Context.$M).sendChanges();
						}
					}

				}
				
	}

	public static boolean isRedimensionnerProportions() {
		return redimensionnerProportions;
	}

	public static void setRedimensionnerProportions(boolean redimensionnerProportions) {
		DrawChange.redimensionnerProportions = redimensionnerProportions;
	}
}
/*	anciennement dans mousereleased
if(redimensionner)
			resizeFormDrag(e);
public void resizeFormDrag(MouseEvent e) {
if(((ProcessingModel) Context.$M) != null) {
		if(f != null) {
			int[] tab = f.getFormValues();
			int deplacementX = e.getX() - x;
			if(deplacementX > 0) { // La souris va a droite
				((ProcessingModel) Context.$M).addForm(e.getX(), tab[1],tab[2]-deplacementX, tab[3], f.getFormType(), f.getFormImage());
				System.out.println("DEPLACEMENT DROITE");
				
			} else if (deplacementX < 0 ) { // La souris va a gauche
				((ProcessingModel) Context.$M).addForm(tab[0]+deplacementX, tab[1],tab[2]-deplacementX, tab[3], f.getFormType(), f.getFormImage());
				System.out.println("DEPLACEMENT GAUCHE");
				
			}
						
			xresize = 0;
			yresize = 0;
		}
	
}
*/


/*	
 anciennement dans mousedragged
if(formy < e.getY() && e.getY() < formy + height) {
int deplacementX = e.getX() - x;
System.out.println("DEPLACEMENT X :" + deplacementX);
if(deplacementX > 0) { // La souris va a droite
	
	if(e.getX() < (formx+width+100) && e.getX() > (formx+width-50) && (tab[2]+deplacementX) > 15) {	
		((ProcessingModel) Context.$M).addForm(tab[0], tab[1],e.getX(), tab[3], f.getFormType(), f.getFormImage());
	} 
		
		
} else if (deplacementX < 0 ) { // La souris va a gauche
	
	if( (tab[2]+deplacementX) > 15) {
			//tab[2]+deplacementX
		((ProcessingModel) Context.$M).addForm(tab[0], tab[1],e.getX(), tab[3], f.getFormType(), f.getFormImage());
	} 
}
}	
}else {

if(formx < e.getX() && e.getX() < formx + width) {
int deplacementY = e.getY() - y;
System.out.println("DEPLACEMENT Y :" + deplacementY);
if(deplacementY > 0) { // La souris va a droite

	if(e.getY() < (formy+height+100) && e.getY() > (formy+height-50) && (tab[3]+deplacementY) > 15) {	
		((ProcessingModel) Context.$M).addForm(tab[0], tab[1],tab[2], e.getY(), f.getFormType(), f.getFormImage());
	} 
		
		
} else if (deplacementY < 0 ) { // La souris va a gauche
		//System.out.println("DEPLACEMENT GAUCHE");
	if( (tab[3]+deplacementY) > 15) {
			//tab[2]+deplacementX
		((ProcessingModel) Context.$M).addForm(tab[0], tab[1],tab[2], e.getY(), f.getFormType(), f.getFormImage());
	} 
}
}
if(resize) {
				
				
			}
*/
/*
Ancienne methode
public void resizeFormclick(MouseEvent e) {
if (((ProcessingModel) Context.$M) != null) {
	f = ((ProcessingModel) Context.$M).getCurrentForm();
	int[] tab = f.getFormValues();
	int formx = tab[0];
	int formy = tab[1];
	int width = tab[2];
	int height = tab[3];
	
	if (formx - MARGE < e.getX()  && e.getX() < formx + MARGE && formx - MARGE < e.getY() && e.getY() < formy + MARGE + height) {
		resize = true;
		System.out.println("A");
	}
	
	if (formx - MARGE < e.getX() && e.getX() < formx + width + MARGE && formy - MARGE + height < e.getY() && e.getY() < formy + height + MARGE) {
		System.out.println("D");
		resize = false;
	}
}
}

anciennement dans mousepressed :
		
		if(redimensionner)
			resizeFormclick(e);
			
*/
