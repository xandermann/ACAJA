package gui.conversion.views_controllers;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import resources.ResourceConstants;

public final class RowView extends JPanel{
	//=======================================================================================================================
	//=======================================================================================================================
	
	private String text;
	private ImageIcon icon;
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/** Constructeur d'une entree de liste avec du texte et une icone
	 * 
	 * @param text 		String texte a afficher dans la liste
	 * @param thumbail	ImageIcon icone a afficher dans la liste
	 */
	public RowView(String text, File icon) {
		if((this.text = text) == null)
			throw new NullPointerException("Le text recu en parametre est null !");
		if(icon == null)
			throw new NullPointerException("L'icon recu en parametre est null !");
		try {
			this.icon = new ImageIcon(ImageIO.read(icon));
		} catch (IOException e) {}
	}
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	public String getText() {
		return text;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public String toString() {
		return text;
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
}
