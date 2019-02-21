package gui.treatment;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ToolTreatment {
	
	public static void drawDeco(Graphics g,int h,int l) {
		int d = 10;
		int t = 30;
		
		g.drawLine(d, d, t, d);
		g.drawLine(d, d, d, t);
		
		g.drawLine(l-d, d, l-t,d);
		g.drawLine(l-d, d, l-d,t);
		
		g.drawLine(d, h-d, t, h-d);
		g.drawLine(d, h-d, d, h-t);
		
		g.drawLine(l-d, h-d, l-t, h-d);
		g.drawLine(l-d, h-d, l-d, h-t);
	}

}
