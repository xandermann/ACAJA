package gui_conversion;
import javax.swing.*;

import java.awt.Graphics;
import java.util.*;
public class SummaryView extends JPanel implements Observer{

	private ConversionModel model;
	
	public SummaryView (ConversionModel p_model) {
		this.model = p_model;
	}
	
	public void paintComponent(Graphics g) {
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
