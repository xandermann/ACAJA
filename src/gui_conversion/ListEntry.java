package gui_conversion;

import javax.swing.ImageIcon;

public class ListEntry {
	//=======================================================================================================================
	//=======================================================================================================================
	
	private String text;
	private ImageIcon icon;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/** Constructeur d'une entree de liste avec du texte uniquement
	 * 
	 * @param p_text String texte a afficher dans la liste
	 */
	public ListEntry(String p_text) {
		this.text = p_text;
	}
	
	/** Constructeur d'une entree de liste avec du texte et une icone
	 * 
	 * @param p_text String texte a afficher dans la liste
	 * @param p_icon ImageIcon icone a afficher dans la liste
	 */
	public ListEntry(String p_text, ImageIcon p_icon) {
		this.text = p_text;
		this.icon = p_icon;
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
