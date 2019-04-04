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
	private File im;
	private static boolean redimensionner;
	private static int MARGE = 5;
	private int xresize, yresize;
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
<<<<<<< HEAD
		
		if(redimensionner)
			resizeFormclick(e);
=======
		//faireResizeMousePressed(e);
>>>>>>> 23fac799f52fd02b8f207e36969970ec0d70f13b
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(redimensionner)
			resizeFormDrag(e);
		((ProcessingModel) Context.$M).sendChanges();
	}
	
	public void resizeFormclick(MouseEvent e) {
		if (((ProcessingModel) Context.$M) != null) {
			f = ((ProcessingModel) Context.$M).getCurrentForm();
			int[] tab = f.getFormValues();
			int formx = tab[0];
			int formy = tab[1];
			int width = tab[2];
			int height = tab[3];
			
			if (formx - MARGE < e.getX()  && e.getX() < formx + MARGE && formx - MARGE < e.getY() && e.getY() < formy + MARGE + height) {
				xresize = formx;
				yresize = 0;
				System.out.println("A");
			}
			if (formx - MARGE < e.getX() && e.getX() < formx + MARGE + width && formy - MARGE < e.getY() && e.getY() < formy + MARGE) {
				System.out.println("B");
				xresize = 0;
				yresize = formy;
			}
			if (formx + width - MARGE < e.getX() && e.getX() < formx + width + MARGE && formy - MARGE< e.getY() && e.getY() < formy + height + MARGE) {
				System.out.println("C");
				xresize = formx;
				yresize = 0;
			}
			if (formx - MARGE < e.getX() && e.getX() < formx + width + MARGE && formy - MARGE + height < e.getY() && e.getY() < formy + height + MARGE) {
				System.out.println("D");
				xresize = 0;
				yresize = formy;
			}
		}
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!redimensionner)
			bougerForm(e);
		else {
			if (((ProcessingModel) Context.$M) != null) {
				f = ((ProcessingModel) Context.$M).getCurrentForm();
				int[] tab = f.getFormValues();
				int formx = tab[0];
				int formy = tab[1];
				int width = tab[2];
				int height = tab[3];
				//if (formx - MARGE < e.getX()  && e.getX() < formx + MARGE && formx - MARGE < e.getY() && e.getY() < formy + MARGE + height) {
					if(formy < e.getY() && e.getY() < formy + height) {
						int deplacementX = e.getX() - x;
						System.out.println("DEPLACEMENT X :" + deplacementX);
						if(deplacementX > 0) { // La souris va a droite
							//System.out.println("DEPLACEMENT DROITE");
							if(e.getX() < (formx+width+100) && e.getX() > (formx+width-100) && (tab[2]+deplacementX) > 15) {
								
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1],e.getX(), tab[3], f.getFormType(), f.getFormImage());
							} 
							
							
						} else if (deplacementX < 0 ) { // La souris va a gauche
							//System.out.println("DEPLACEMENT GAUCHE");
							if( (tab[2]+deplacementX) > 15) {
								//tab[2]+deplacementX
								((ProcessingModel) Context.$M).addForm(tab[0], tab[1],e.getX(), tab[3], f.getFormType(), f.getFormImage());
							} 
						}
					}
				}	
			//}
		}
		
	}

	private void actualiserCoordonnees(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void resizeFormDrag(MouseEvent e) {
	/*	if(((ProcessingModel) Context.$M) != null) {
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
		}*/
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
			int[] tab = f.getFormValues();
			int formx = tab[0];
			int formy = tab[1];
			int width = tab[2];
			int height = tab[3];
		
						if(formy < e.getY() && e.getY() < formy + height && e.getX() < (formx+width+100) && e.getX() > (formx+width-100)) {
							if(pvv != null)
								pvv.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
						}  else {
							pvv.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						
					
				}
			

	}
	
	public void bougerForm(MouseEvent e) {
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
}
