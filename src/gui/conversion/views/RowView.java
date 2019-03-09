package gui.conversion.views;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
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
	
	
	/** 
	 * [ CONSTRUCTEUR D'UNE LIGNE DANS LA BIBLIOTHEQUE. ]
	 */
	public RowView(SettingsFile file, boolean special) {
		super(new BorderLayout());
		if(file == null) throw new NullPointerException("Le text recu en parametre est null !");
		setPreferredSize(new Dimension(270, 140));
		JPanel content = new JPanel(new BorderLayout());
		content.setBackground(special ? StyleTheme.BACKGROUND_COLOR_SECONDARY : Color.WHITE);
		content.add(new JLabel(file.getSourceFileName().toUpperCase(), JLabel.CENTER), BorderLayout.NORTH);
		content.add(new JPanel() {
				public void paintComponent(Graphics g) {
					try {
						g.drawImage(ImageIO.read(file.getThumbail()), 35, 5, null);
					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(null, ioe.getMessage());
					}
				}
		}, BorderLayout.CENTER);
		add(content, BorderLayout.CENTER);
	}


	//=======================================================================================================================
	//=======================================================================================================================
}
