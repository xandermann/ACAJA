package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import files.files.SelectableFile;
import files.files.SettingsFile;
import gui.general.Context;
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
	private static final long serialVersionUID = 3415076024928133155L;



	/**
	 * [ METHODE INTERNE POUR (RE-)DESSINER LA BIBLIOTHEQUE. ]
	 */
	private void displayLibrary() {
		removeAll();
		
		
		List<SelectableFile> files = Context.$M.getFiles();
		
		
		int width = files.size() < 4 ? 320 : 300;
		for(int i=1; i<=5; i++){
			Component view = Context.$C(i);
			view.setPreferredSize(new Dimension(width, view.getHeight()));
			view.revalidate();
			view.repaint();
		}
	  	
		
		JPanel all = new JPanel(new BorderLayout());
		width = files.size() >= 4 ? 290 : 270;
		int height = files.size()*140;
		all.setPreferredSize(new Dimension(width, ((files.size()*140)<=540) ? 540 : height));
		
		
		JPanel content = new JPanel(new GridLayout(files.size(), 1));
		content.setPreferredSize(new Dimension(width, height));
		for(SelectableFile file : files) {
			RowView row = new RowView((SettingsFile) file, file==Context.$M.getCurrentFile());
			row.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {
					 Context.$M.setCurrentFile(file);
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			content.add(row);
		}
		all.add(content, BorderLayout.CENTER);
		
		
		if(height < 540) {
			JPanel nothing = new JPanel();
			nothing.setPreferredSize(new Dimension(width, 540-height));
			all.add(nothing, BorderLayout.SOUTH);
		}
		
		
		add(new JScrollPane(all, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), 
			BorderLayout.CENTER);
		
		
		repaint();
		revalidate();
		Context.$C(1).repaint();
		Context.$C(1).revalidate();
		Context.$W.repaint();
		Context.$W.revalidate();
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
