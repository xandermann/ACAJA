package gui.conversion.views;

import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import files.files.*;
import gui.general.Context;
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
public final class RowView extends JPanel  implements Observer{
	private static final long serialVersionUID = -2390609841482246872L;
	/**
	 * FICHIER CONCERNE.
	 */
	private SettingsFile file;
	
	
	
	/** 
	 * [ CONSTRUCTEUR D'UNE LIGNE DANS LA BIBLIOTHEQUE. ]
	 */
	public RowView(SettingsFile file) {
		super(new BorderLayout());
		if((this.file=file) == null) 
			throw new NullPointerException("Le text recu en parametre est null !");
		setPreferredSize(new Dimension(270, 140));
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				 Context.$M.setCurrentFile(file);
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		 Context.$M.addObserver(this);
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int w = 270;
		int h = 140;
				
		g.setColor((file==Context.$M.getCurrentFile()) ? StyleTheme.BACKGROUND_COLOR_SECONDARY : Color.WHITE);
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



	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
