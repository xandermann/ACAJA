package gui.processing;

import java.awt.Image;

public class Form {
	
	private int[] tab;
	private char typeCommande;
	private Image image;
	
	public Form(int[] t, char tyC, Image i) {
		this.tab = t;
		this.typeCommande = tyC;
		this.image = i;
	}

	public void setForm(int[] t, char tyC , Image i) {
		tab = t;
		typeCommande = tyC;
		image = i;
	}
	public int[] getTab() {
		return tab;
	}
	public char getTypeCommande() {
		return typeCommande;
	}
	
	public Image getImageA() {
		return image;
	}

}
