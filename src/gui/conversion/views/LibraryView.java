package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import files.files.SelectableFile;
import files.files.SettingsFile;
import gui.conversion.ConversionContext;
import gui.conversion.ConversionModel;
import gui.general.GeneralContext;
import gui.style.StylizedJPanel;

/**
 * [ CLASSE POUR L'AFFICHAGE DE LA BIBLIOTHEQUE. ]
 * 
 * @author Jean-christophe
 *
 */
public final class LibraryView extends StylizedJPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	/**
	 * [ METHODE INTERNE POUR (RE-)DESSINER LA BIBLIOTHEQUE. ]
	 */
	private void displayLibrary() {
		removeAll();
		
		List<SelectableFile> files = ConversionContext.$M.getFiles();
		JPanel all = new JPanel(new BorderLayout());
		int size = files.size()*140;
		all.setPreferredSize(new Dimension(270, files.size()*150<=540 ? 540 : size));
		
		JPanel content = new JPanel(new GridLayout(files.size(), 1));
		content.setPreferredSize(new Dimension(270, size));
		for(SelectableFile file : files) {
			RowView row = new RowView((SettingsFile) file, file==ConversionContext.$M.getCurrentFile());
			row.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {
					 ConversionContext.$M.setCurrentFile(file);
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			content.add(row);
		}
		all.add(content, BorderLayout.CENTER);
		
		if(size < 540) {
			JPanel nothing = new JPanel();
			nothing.setPreferredSize(new Dimension(270, 540-size));
			all.add(nothing, BorderLayout.SOUTH);
		}
		
		add(new JScrollPane(all, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), 
			BorderLayout.CENTER);
		
		repaint();
		revalidate();
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	/**
	 * [ CONSTRUCTEUR AVEC PARAMETRES.]
	 */
	public LibraryView() {
		super(new BorderLayout());
		displayLibrary();
	}
	
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		displayLibrary();
	}
	
	

	//=======================================================================================================================
	//=======================================================================================================================
}
