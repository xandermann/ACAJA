package gui.conversion.views;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import files.files.SettingsFile;
import gui.style.StyleTheme;
import resources.ResourceConstants;

public final class RowView extends JPanel{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/** Constructeur d'une entree de liste avec du texte et une icone
	 * 
	 * @param text 		String texte a afficher dans la liste
	 * @param thumbail	ImageIcon icone a afficher dans la liste
	 */
	public RowView(SettingsFile file, boolean special) {
		if(file == null) throw new NullPointerException("Le text recu en parametre est null !");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(270, 150));
		JPanel content = new JPanel(new BorderLayout());
		content.setBackground(special ? StyleTheme.BACKGROUND_COLOR_SECONDARY : Color.WHITE);
		content.add(new JLabel(file.getSourceFileName().toUpperCase(), JLabel.CENTER), BorderLayout.NORTH);
		content.add(new JPanel() {
				public void paintComponent(Graphics g) {
					try {
						g.drawImage(ImageIO.read(file.getThumbail()), 35, 5, null);
					} catch (IOException e) {}
				}
		}, BorderLayout.CENTER);
		add(content, BorderLayout.CENTER);
	}


	//=======================================================================================================================
	//=======================================================================================================================
}
