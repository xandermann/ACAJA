package gui.processing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Form {

	/**
	 * Tableau
	 * 
	 * 	// 0 => Marge gauche
	 *	// 1 => Marge haut
	 *	// 2 => Largeur
	 *	// 3 => Longueur
	 */
	private int[] tab;
	private char typeCommande;
	private File image;
	


	/**
	 * Form
	 * @param t
	 * @param tyC
	 * @param i
	 */
	public Form(int[] t, char tyC, File i) {
		this.tab = t;
		this.typeCommande = tyC;
		this.image = i;
		
	}

	/**
	 * SetForm
	 * @param t
	 * @param tyC
	 * @param i
	 */
	public void setForm(int[] t, char tyC, File i) {
		tab = t;
		typeCommande = tyC;
		image = i;
	}

	/**
	 * Recupere le tableau
	 * @return Le tableau
	 */
	public int[] getTab() {
		return tab;
	}

	/**
	 * Recuperer le type de commande
	 * @return Le type de commande
	 */
	public char getTypeCommande() {
		return typeCommande;
	}

	/**
	 * Recupere l'image A
	 * @return L'image A
	 */
	public File getImageA() {
		
		return image;
	}

}
