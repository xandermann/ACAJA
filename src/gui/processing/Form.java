package gui.processing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Form {

	/**
	 * formValuesleau
	 * 
	 * 	// 0 => Marge gauche
	 *	// 1 => Marge haut
	 *	// 2 => Largeur
	 *	// 3 => Longueur
	 */
	private int[] formValues;
	private char formType;
	private File formImage;
	private Image formImageGenerated;
	


	/**
	 * Form
	 * @param t
	 * @param tyC
	 * @param i
	 */
	public Form(int[] t, char tyC, File i) {
		this.formValues = t;
		this.formType = tyC;
		this.formImage = i;
		if(tyC == 'i') {
			try {
				formImageGenerated = new ImageIcon(ImageIO.read(i)).getImage();
			} catch (IOException e) {
				// TODO REMONTER L'ERREUR
				e.printStackTrace();
			}
		} else {
				formImageGenerated = new ImageIcon("img/test.png").getImage();
		}
		
	}

	/**
	 * SetForm
	 * @param t
	 * @param tyC
	 * @param i
	 */
	public void setForm(int[] tabValues, char tyC, File i) {
		formValues = tabValues;
		formType = tyC;
		formImage = i;
	}

	/**
	 * Recupere le formValuesleau
	 * @return Le formValuesleau
	 */
	public int[] getFormValues() {
		return formValues;
	}

	/**
	 * Recuperer le type de commande
	 * @return Le type de commande
	 */
	public char getFormType() {
		return formType;
	}

	/**
	 * Recupere l'formImage 
	 * @return L'formImage 
	 */
	public File getFormImage() {
		return formImage;
	}
	
	public Image getGeneratedFormImage() {
		return formImageGenerated;
	}
	
	public void setSize(int width, int height){
		formValues[2] = width;
		formValues[3] = height;
	}
	
	public void setPosition(int x, int y) {
		formValues[0] = x;
		formValues[1] = y;
	}

}
