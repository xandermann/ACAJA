package gui.conversion.views_controllers;

import javax.swing.ImageIcon;

public final class EntryView {
	//=======================================================================================================================
	//=======================================================================================================================
	
	private String text;
	private ImageIcon icon;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/** Constructeur d'une entree de liste avec du texte uniquement
	 * 
	 * @param text String texte a afficher dans la liste
	 */
	public EntryView(String text) {
		this.text = text;
	}
	
	/** Constructeur d'une entree de liste avec du texte et une icone
	 * 
	 * @param text String texte a afficher dans la liste
	 * @param icon ImageIcon icone a afficher dans la liste
	 */
	public EntryView(String text, ImageIcon icon) {
		this.text = text;
		this.icon = icon;
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public String getText() {
		return this.text;
	}
	
	public ImageIcon getIcon() {
		return this.icon;
	}
	
	public String toString() {
		return this.text;
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
}
