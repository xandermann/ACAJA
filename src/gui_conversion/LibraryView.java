package gui_conversion;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import files.SettingsFile;

public class LibraryView extends JList implements Observer {

		private ConversionModel model;
		
		public LibraryView(ConversionModel p_model, DefaultListModel p_listmodel) {
			super(p_listmodel);
			this.model = p_model;
			this.reevaluatePanel();
			this.setVisible(true);
		}
		
		private void reevaluatePanel() { 
			this.setFixedCellWidth(400);
		}
		

		@Override
		public void update(Observable o, Object arg) {
			this.setModel(this.model.getFilenames());
			this.repaint();
		}
		
}
