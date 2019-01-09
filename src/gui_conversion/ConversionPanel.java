package gui_conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConversionPanel extends JFrame{
	 //private JFrame window;
	 private ConversionModel model;
	 
	 public ConversionPanel(ConversionModel m) {
		this.model = m;
		//this.generateConversionWindow();
	 }
	 
	 public void generateConversionWindow() {
		 this.setVisible(true);
		 this.setTitle("Acaja Conversion");
		 this.setSize(new Dimension(1000,600));
		 try {
			 this.setIconImage(ImageIO.read(new File("img/LogoAcaja.png")));
		 } catch (IOException e1) {
			 e1.printStackTrace();
			}
		 this.setLocation(100, 100);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 SummaryView sv = new SummaryView(this.model);
		 LibraryView lv = new LibraryView(this.model);
		 TabsView tv = new TabsView(this.model);
		 
		 JPanel p = new JPanel();
		 p.setLayout(new BorderLayout());
		 p.add(sv,BorderLayout.NORTH);
		 p.add(tv,BorderLayout.CENTER);
		 
		 this.setLayout(new BorderLayout());
		 this.add(lv,BorderLayout.WEST);
		 this.add(p,BorderLayout.EAST);
		 
		 
		 this.repaint();
	 }
	 
	 
}
