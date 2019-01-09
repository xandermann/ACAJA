package gui_conversion;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import files.SettingsFile;

public class LibraryView extends JList implements Observer {

		private ConversionModel model;
		private DefaultListModel listmodel;
		
		public LibraryView(ConversionModel p_model, DefaultListModel p_listmodel) {
			super(p_listmodel);
			this.listmodel = p_listmodel;
			this.model = p_model;
			this.reevaluatePanel();
			this.setVisible(true);
		}
		
		private void reevaluatePanel() { 
			this.setFixedCellWidth(400);
		}
		
		public void addFile(String filename) {
			this.listmodel.addElement(new ListEntry(filename));
		}
		

		@Override
		public void update(Observable o, Object arg) {
			this.setModel(this.listmodel);
			this.repaint();
		}
		
}
