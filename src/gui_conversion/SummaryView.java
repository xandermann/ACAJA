package gui_conversion;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;
public class SummaryView extends JPanel implements Observer{

	private ConversionModel model;
	
	public SummaryView (ConversionModel p_model) {
		this.model = p_model;
		
	}
	
	public void paintComponent(Graphics g) {
		this.setSize(new Dimension(550, 300));
		this.setBackground(Color.BLUE);
	}
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
