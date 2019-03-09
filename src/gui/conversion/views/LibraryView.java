package gui.conversion.views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import files.files.SettingsFile;
import gui.conversion.model.ConversionModel;
import gui.style.StylizedJPanel;

public final class LibraryView extends StylizedJPanel implements Observer{
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	private ConversionModel model;
	
	
	private void drawLibrary() {
		removeAll();
		
		ArrayList<SettingsFile> files = model.getFiles();
		JPanel all = new JPanel();
		all.setPreferredSize(new Dimension(270, files.size()*150<=540 ? 540 : files.size()*140));
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(files.size(), 1));
		content.setPreferredSize(new Dimension(270, files.size()*140));
		for(SettingsFile file : files) {
			RowView row = new RowView(file, file==model.getCurrentFile());
			row.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {
					model.setCurrentFile(file);
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			content.add(row);
		}
		setLayout(new BorderLayout());
		all.add(content, BorderLayout.CENTER);
		
		if(files.size()*140 < 540) {
			JPanel nothing = new JPanel();
			nothing.setPreferredSize(new Dimension(270, 540-files.size()*140));
			all.add(nothing, BorderLayout.SOUTH);
		}
		
		add(new JScrollPane(all, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		repaint();
		revalidate();
	}
	
	
	public LibraryView(ConversionModel model) {
		if((this.model = model) == null) throw new NullPointerException("ConversionModel null !");
		drawLibrary();
	}
	
	
	//=======================================================================================================================
	//=======================================================================================================================
	
	
	@Override
	public void update(Observable o, Object arg) {
		drawLibrary();
	}
	

	//=======================================================================================================================
	//=======================================================================================================================
}
