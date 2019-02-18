package gui.traitement;

import java.awt.Color;
import javax.swing.JFrame;

public class FrameTraitement extends JFrame{
	
	public FrameTraitement() {
		this.setVisible(true);
		this.setBackground(Color.lightGray);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelTraitement p = new PanelTraitement();
		
		this.setSize(1000, 600);
		this.setResizable(false);
		this.add(p);
	}
	
	public static void main(String[] args) {
		FrameTraitement f = new FrameTraitement();
	}
}
