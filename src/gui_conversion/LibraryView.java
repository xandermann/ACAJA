package gui_conversion;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class LibraryView extends JList implements Observer {

		private ConversionModel model;
		
		public LibraryView(ConversionModel p_model) {
			this.model = p_model;
			this.setSize(new Dimension(350, 600));
			this.setBackground(Color.red);
		}
		
		private void reevaluatePanel() { }
		
		// Methode redefinissant le setPreferredSize pour gere l'ascenceur
		@Override
		public void setPreferredSize(Dimension d) { }

		@Override
		public void update(Observable o, Object arg) { }
		
}
