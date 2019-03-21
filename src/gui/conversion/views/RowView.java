package gui.conversion.views;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import files.files.SelectableFile;
import files.files.SettingsFile;
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
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private SelectableFile file;
	private boolean special;

	
	
	/** 
	 * [ CONSTRUCTEUR D'UNE LIGNE DANS LA BIBLIOTHEQUE. ]
	 */
	public RowView(SettingsFile file, boolean special) {
		super(new BorderLayout());
		if((this.file=file) == null) throw new NullPointerException("Le text recu en parametre est null !");
		this.special=special;
		setPreferredSize(new Dimension(270, 140));
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(special ? StyleTheme.BACKGROUND_COLOR_SECONDARY : Color.WHITE);
		g.fillRect(0, 0, 270, 140);
		
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 250, 120); 
		g.drawRect(11, 11, 248, 118);
		g.drawRect(12, 12, 246, 116);
		
		g.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		g.drawString(file.getSourceFileName().toUpperCase(), (270-8*file.getSourceFileName().length())/2, 30);
		
		try {
			g.drawImage(ImageIO.read(file.getThumbail()), 40, 40, null);
		} catch (IOException ioe) {}
		
		g.drawRect(40, 40, 190, 80); 
		g.drawRect(41, 41, 188, 78);
		g.drawRect(42, 42, 186, 76);
	}


	//=======================================================================================================================
	//=======================================================================================================================
}
