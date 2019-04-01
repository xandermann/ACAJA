package gui.conversion.views;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import files.files.*;
import gui.style.StyleTheme;

/**
 * [ CLASSE VUE D'UNE LIGNE DANS LA BIBLIOTHEQUE. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class RowView extends JPanel{
	/**
	 * FICHIER CONCERNE.
	 */
	private SelectableFile file;
	/**
	 * EST-CE LE FICHIER COURANT ?
	 */
	private boolean isCurrentFile;

	
	
	/** 
	 * [ CONSTRUCTEUR D'UNE LIGNE DANS LA BIBLIOTHEQUE. ]
	 */
	public RowView(SettingsFile file, boolean isCurrentFile) {
		super(new BorderLayout());
		
		if((this.file=file) == null) throw new NullPointerException("Le text recu en parametre est null !");
		this.isCurrentFile=isCurrentFile;
		
		setPreferredSize(new Dimension(270, 140));
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		int w = 270;
		int h = 140;
				
		g.setColor(isCurrentFile ? StyleTheme.BACKGROUND_COLOR_SECONDARY : Color.WHITE);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.BLACK);
		
		
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		g.drawString(file.getSourceFileNameWithoutExtension(), (w-14*file.getSourceFileNameWithoutExtension().length())/2, 28);
		
		try {
			g.drawImage(ImageIO.read(file.getThumbnail()), 40, 40, null);
		} catch (IOException ioe) {}
		
		g.drawRect(40, 40, w-80, h-50); 
		g.drawRect(41, 41, w-82, h-52);
		g.drawRect(42, 42, w-84, h-54);
	}
}
