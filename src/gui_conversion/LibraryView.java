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
		
		// Methode redefinissant le setPreferredSize pour gere l'ascenceur
		@Override
		public void setPreferredSize(Dimension d) { }

		@Override
		public void update(Observable o, Object arg) { }
		
}
